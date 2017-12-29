package com.velocity.coin.market.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.velocity.coin.market.bithumb.model.BithumbRespVO;
import com.velocity.coin.market.bithumb.model.BithumbTicker;
import com.velocity.coin.market.bithumb.service.BithumbService;
import com.velocity.coin.model.Coin;
import com.velocity.coin.model.Market;
import com.velocity.coin.repository.es.EsRepository;

@Component
public class BithumbScheduler {

	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private BithumbService bithumbService;
	
	@Autowired
	private EsRepository esRepository;
	
	@Scheduled(fixedDelay=1000*5)
	public void test() throws JsonProcessingException{
		
		System.out.println("BithumbScheduler test");
		BithumbRespVO respVO  = bithumbService.ticker(Coin.BTC);
		
		BithumbTicker ticker = new BithumbTicker(Coin.BTC, 
				respVO.data.date,
				respVO.data.opening_price,
				respVO.data.closing_price,
				respVO.data.min_price,
				respVO.data.max_price,
				respVO.data.average_price,
				respVO.data.units_traded,
				respVO.data.volume_1day,
				respVO.data.volume_7day,
				respVO.data.buy_price, 
				respVO.data.sell_price
				);
		
		esRepository.set("jiho", Market.Bithumb, ticker.toMap());
	}
	
}
