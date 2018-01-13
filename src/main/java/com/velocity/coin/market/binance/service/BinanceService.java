package com.velocity.coin.market.binance.service;

import com.velocity.coin.market.binance.model.BinanceData;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BinanceService {

    private static Logger logger = LoggerFactory.getLogger(BinanceService.class);

    @Value("${partner.binance.domain}")
    private String domain;

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, BinanceData> ticker(){
        logger.debug("ticker");

        String url = domain + "/api/v1/ticker/allPrices";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<BinanceData>> responseType = new ParameterizedTypeReference<List<BinanceData>>() {};
        List<BinanceData> resp = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, requestEntity, responseType).getBody();
        logger.debug("resp : "+resp);


        return convertMap(resp);
    }

    public Map<String, BinanceData> convertMap(List<BinanceData> list){
        Map result = list.stream()
                .collect(
                        Collectors.toMap(
                                a->a.symbol,
                                a->a,
                                (oldValue, newValue) -> oldValue,
                                HashMap::new
                        ));
        return result;
    }



}
