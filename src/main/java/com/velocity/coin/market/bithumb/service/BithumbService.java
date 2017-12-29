package com.velocity.coin.market.bithumb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.velocity.coin.market.bithumb.model.BithumbRespVO;
import com.velocity.coin.model.Coin;

@Service
public class BithumbService {

	@Value("${partner.bithumb.domain}")
	private String domain;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public BithumbRespVO ticker(Coin symbol){
		
		String url = domain + "/public/ticker/"+symbol;
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
	
		BithumbRespVO resp = restTemplate.exchange(url, HttpMethod.GET, requestEntity, BithumbRespVO.class).getBody();
		return resp;
	}
	
}
