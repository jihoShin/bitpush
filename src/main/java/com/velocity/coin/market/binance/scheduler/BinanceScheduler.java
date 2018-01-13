package com.velocity.coin.market.binance.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.velocity.coin.ExculdeFromTest;
import com.velocity.coin.constant.RepoConstants;
import com.velocity.coin.market.binance.model.BinanceData;
import com.velocity.coin.market.binance.model.BinanceTicker;
import com.velocity.coin.market.binance.service.BinanceService;
import com.velocity.coin.market.bithumb.service.BithumbService;
import com.velocity.coin.model.Coin;
import com.velocity.coin.model.Market;
import com.velocity.coin.repository.EsRepository;
import com.velocity.coin.repository.IRepository;
import com.velocity.coin.service.DiffPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Date;
import java.util.Map;

/**
 * Created by 지호 on 2018-01-13.
 */
@ExculdeFromTest
@Component
public class BinanceScheduler {

    private static Logger logger = LoggerFactory.getLogger(BinanceScheduler.class);

    @Autowired
    private BinanceService binanceService;

    @Autowired
    private BithumbService bithumbService;

    @Autowired
    private IRepository repository;

    @Autowired
    private DiffPriceService diffPriceService;

    private static Coin[] coins = {Coin.BTC, Coin.ETH, Coin.XRP, Coin.QTUM, Coin.EOS};

    @Scheduled(fixedDelay=1000*5)
    public void updateCoin() throws JsonProcessingException {
        logger.info("updateCoin");

        Map<String, BinanceData> result = binanceService.ticker();
        BinanceData btcusdt = result.get("BTCUSDT");
        logger.info("btcusdt: "+btcusdt);

        Double btcusdtPrice = btcusdt.price;

        for(Coin coin : coins){
            try {
                logger.info("coin : "+coin);
                BinanceData binanceData = result.get(coin+"BTC");

                if(binanceData != null){
                    double priceUSD = binanceData.price * btcusdtPrice;
                    BinanceTicker ticker = new BinanceTicker(coin, priceUSD, new Date());
                    repository.set(RepoConstants.IndexName.DEFAULT, RepoConstants.Type.TICKER, ticker.toMap());
                    diffPriceService.update(coin, Market.Bithumb, Currency.getInstance("KRW"), priceUSD);
                }
            }catch (Exception e){
                logger.error("updateCoin error : "+coin, e);
            }
        }
    }

}
