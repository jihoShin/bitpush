package com.velocity.coin.market.bithumb.scheduler;

import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.velocity.coin.ExculdeFromTest;
import com.velocity.coin.constant.RepoConstants;
import com.velocity.coin.market.bithumb.model.BithumbRespVO;
import com.velocity.coin.market.bithumb.model.BithumbTicker;
import com.velocity.coin.market.bithumb.service.BithumbService;
import com.velocity.coin.model.Coin;
import com.velocity.coin.model.Market;
import com.velocity.coin.repository.es.EsRepository;
import com.velocity.coin.service.DiffPriceService;

@ExculdeFromTest
@Component
public class BithumbScheduler {

	private static Logger logger = LoggerFactory.getLogger(BithumbScheduler.class);

	@Autowired
	private BithumbService bithumbService;

	@Autowired
	private EsRepository esRepository;

	@Autowired
	private DiffPriceService diffPriceService; 

	private static Coin[] coins = {Coin.BTC, Coin.ETH, Coin.XRP};

	@Scheduled(fixedDelay=1000*5)
	public void updateCoin() throws JsonProcessingException{
		logger.info("updateCoin");
		for(Coin coin : coins){
			logger.info("coin : "+coin);
			
			BithumbRespVO respVO  = bithumbService.ticker(coin);
			BithumbTicker ticker = new BithumbTicker(coin, 
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

			esRepository.set(RepoConstants.IndexName.DEFAULT, Market.Bithumb, ticker.toMap());
			diffPriceService.update(coin, Market.Bithumb, Currency.getInstance("KRW"), respVO.data.closing_price.doubleValue());

		}

	}

}