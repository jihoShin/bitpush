package com.velocity.coin.market.poloniex.scheduler;

import java.util.Currency;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.velocity.coin.ExculdeFromTest;
import com.velocity.coin.constant.RepoConstants;
import com.velocity.coin.market.poloniex.model.PoloniexData;
import com.velocity.coin.market.poloniex.model.PoloniexTicker;
import com.velocity.coin.market.poloniex.service.PoloniexService;
import com.velocity.coin.model.Coin;
import com.velocity.coin.model.Market;
import com.velocity.coin.repository.es.EsRepository;
import com.velocity.coin.service.DiffPriceService;


@ExculdeFromTest
@Component
public class PoloniexScheduler {

	private static Logger logger = LoggerFactory.getLogger(PoloniexScheduler.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private PoloniexService poloniexService;
	
	@Autowired
	private EsRepository esRepository;
	
	@Autowired
	private DiffPriceService diffPriceService;

	private static Coin[] coins = {Coin.BTC, Coin.ETH, Coin.XRP, Coin.BCH, Coin.DASH, Coin.LTC};

	@Scheduled(fixedDelay=1000*5)
	public void updateCoin() throws JsonProcessingException{
		
		logger.info("updateCoin");
		
		HashMap<String, PoloniexData> resp  = poloniexService.ticker();
		
		for(Coin coin : coins){
			try {
				logger.info("coin : "+coin);
				PoloniexData data = resp.get("USDT_"+coin);

				PoloniexTicker ticker = new PoloniexTicker(coin,
						new Date(),
						data.id,
						data.last,
						data.lowestAsk,
						data.highestBid,
						data.percentChange,
						data.quoteVolume,
						data.baseVolume,
						data.high24hr,
						data.low24hr,
						data.isFrozen);

				esRepository.set(RepoConstants.IndexName.DEFAULT, RepoConstants.Type.TICKER, ticker.toMap());
				diffPriceService.update(coin, Market.Poloniex, Currency.getInstance("USD"), data.last);

			}catch (Exception e){
				logger.error("updateCoin error : "+coin, e);
			}
		}
	}
	
}
