package com.velocity.coin.exchange;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.velocity.coin.exchange.model.HistoricalRespVO;

@Service
@CacheConfig(cacheNames = {"coin"})
public class CurrencyExchangeService {
	
	public static Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);
	
	@Value("${partner.apilayer.domain}")
	private String domain;
	
	@Value("${partner.apilayer.key}")
	private String key;
	
	@Autowired
	private RestTemplate restTemplate;

	private String historicalUrl;
	
	private static final DateFormat df = new SimpleDateFormat("YYYY-MM-dd");

	private static final String USDKRW = "USDKRW";
	
	@PostConstruct
	void init(){
		historicalUrl =  domain + "/api/historical";
	}
	
	@Cacheable
	public Double getExchageRateForUSD(String toCurrency){
		logger.info("## getExchageRateForUSD");
		logger.info("toCurrency : "+toCurrency);
		
		Currency currency= Currency.getInstance(toCurrency);
		HistoricalRespVO respVO= historical(currency, new Date());
		
		if(respVO.quotes != null && respVO.quotes.containsKey(USDKRW)){
			return respVO.quotes.get(USDKRW);
		}
		return null;
	}
	
	
	public HistoricalRespVO historical(Currency currency, Date date){
		
		logger.info("## historical");
		logger.info("currency : "+currency);
		logger.info("date : "+date);
		
		String dateStr = df.format(date);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(historicalUrl)
				.queryParam("access_key", key)
				.queryParam("date", dateStr)
				.queryParam("currencies", currency)
				.queryParam("format", 1);
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
	
		HistoricalRespVO resp = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, requestEntity, HistoricalRespVO.class).getBody();
		return resp;
	}
	
	
	

}
