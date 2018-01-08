package com.velocity.coin.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.velocity.coin.ExculdeFromTest;
import com.velocity.coin.service.DiffPriceService;


@ExculdeFromTest
@Component
public class DiffPriceScheduler {


	private static Logger logger = LoggerFactory.getLogger(DiffPriceScheduler.class);
	
	@Autowired
	private DiffPriceService diffPriceService;

	@Scheduled(fixedDelay=1000*5)
	public void update() throws JsonProcessingException{
		
		logger.info("update");
		diffPriceService.upload();
		
	}
	
}
