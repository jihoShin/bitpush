package com.velocity.coin.market.poloniex.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.velocity.coin.market.poloniex.model.PoloniexData;

@Service
public class PoloniexService {

	private static Logger logger = LoggerFactory.getLogger(PoloniexService.class);
	
	@Value("${partner.poloniex.domain}")
	private String domain;

	@Autowired
	private RestTemplate restTemplate;

	public HashMap<String, PoloniexData> ticker(){
		logger.debug("ticker");
		
		String url = domain + "/public";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("command", "returnTicker");

				HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		ParameterizedTypeReference<HashMap<String, PoloniexData>> responseType = new ParameterizedTypeReference<HashMap<String, PoloniexData>>() {};
	    HashMap<String, PoloniexData> resp = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, requestEntity, responseType).getBody();
	    logger.debug("resp : "+resp);
		return resp;
	}

}
