package com.velocity.coin.exchange;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

import com.velocity.coin.constant.RepoConstants;
import com.velocity.coin.exchange.model.HistoricalRespVO;
import com.velocity.coin.repository.es.EsRepository;

import javax.annotation.PostConstruct;

@Service
@CacheConfig(cacheNames = {"coin"})
public class CurrencyExchangeService {

	private static Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);

	
	@Value("${partner.apilayer.domain}")
	private String domain;

	@Value("${partner.apilayer.key}")
	private String key;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EsRepository esRepository;

	private DateFormat df;

	@Cacheable
	public Double getExchageRate(Currency from, Currency to){
		return getExchageRate(from, to, true);
	}


	@PostConstruct
	public void init(){
		df = new SimpleDateFormat("YYYY-MM-dd");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	@Cacheable
	public Double getExchageRate(Currency from, Currency to, boolean isStoreInDB){
		logger.info("## getExchageRateForUSD");
		logger.info("from : "+from);
		logger.info("to   : "+to);

		HistoricalRespVO respVO= historical(to, new Date());
		String key = getKey(from, to);
		if(respVO.quotes != null && respVO.quotes.containsKey(key)){
			Double exchageRate = respVO.quotes.get(key);
			if(isStoreInDB){
				Map<String, Object> map = new HashMap<>();
				map.put(RepoConstants.FieldName.DATE, new Date());
				map.put(RepoConstants.FieldName.EXCHANGE_RATE+"_"+from+"_"+to, exchageRate);
				
				esRepository.set(RepoConstants.IndexName.THIRD_PARTY, RepoConstants.Type.EXCHANGE, map);
			}
			return exchageRate;
		}
		return null;
	}

	public HistoricalRespVO historical(Currency currency, Date date){

		logger.info("## historical");
		logger.info("currency : "+currency);
		logger.info("date : "+date);

		String url = domain + "/api/historical";
		String dateStr = df.format(date);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("access_key", key)
				.queryParam("date", dateStr)
				.queryParam("currencies", currency)
				.queryParam("format", 1);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		HistoricalRespVO resp = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, requestEntity, HistoricalRespVO.class).getBody();

		logger.debug(resp.toString());
		return resp;
	}

	private String getKey(Currency from, Currency to){
		if(from == null || to == null){
			return null;
		}

		return from.getCurrencyCode() + to.getCurrencyCode();
	}




}
