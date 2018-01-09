package com.velocity.coin.service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velocity.coin.constant.RepoConstants;
import com.velocity.coin.exchange.CurrencyExchangeService;
import com.velocity.coin.model.Coin;
import com.velocity.coin.model.Market;
import com.velocity.coin.repository.es.EsRepository;

@Service
public class DiffPriceService {
	
	private static Logger logger = LoggerFactory.getLogger(DiffPriceService.class);
	
	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	private EsRepository esRepository;
	
 	
	final static private Currency DEFAULT_CURRENCY = Currency.getInstance("KRW");

	final static private ConcurrentHashMap<String, ConcurrentHashMap<String, Price>> mapByCoin = new ConcurrentHashMap<>();
	

	public void update(Coin coin, Market market, Currency currency, Double amount){
		
		String coinKey = coin.name();
		String marketKey = market.name();
		
		ConcurrentHashMap<String, Price> mapByMarket = mapByCoin.get(coinKey);
		if(mapByMarket == null){
			mapByMarket = new ConcurrentHashMap<>();
			mapByCoin.put(coinKey, mapByMarket);
		}
		
		Double amountStandard;
		if(DEFAULT_CURRENCY != currency){
			double exchangeRate = currencyExchangeService.getExchageRate(currency, DEFAULT_CURRENCY);
			amountStandard = amount*exchangeRate;
			logger.debug(exchangeRate+" * "+amount+" = "+amountStandard);

		}else{
			amountStandard = amount;
		}
		
		mapByMarket.put(marketKey, new Price(currency, amount, amountStandard));
	}
	
	public void upload(){
		
		logger.debug("upload : "+mapByCoin.size());
		for(Entry<String, ConcurrentHashMap<String, Price>> entry : mapByCoin.entrySet()){
			
			String coineName = entry.getKey();
			ConcurrentHashMap<String, Price> mapByMarkentBin = entry.getValue();

			Map<String, Object> priceInfo = new HashMap<String, Object>();
			priceInfo.put(RepoConstants.FieldName.COIN, coineName);
			priceInfo.put(RepoConstants.FieldName.CURRENCY, DEFAULT_CURRENCY);
			priceInfo.put(RepoConstants.FieldName.DATE, new Date());
			
			List<String> keys = new ArrayList<String>(mapByMarkentBin.keySet());
			for(int i=0; i<keys.size(); i++){
				String key1 = keys.get(i);
				Price price1 = mapByMarkentBin.get(keys.get(i));
				priceInfo.put(RepoConstants.FieldName.PRICE_PREFIX+key1, price1.amountStandard);
				
				for(int j=i+1 ; j<keys.size() && i<keys.size()-1 ; j++){
					String key2 = keys.get(j);
					Price price2 = mapByMarkentBin.get(key2);

					double diffBasedOnPrice1 = calculPriceDiffPercentage(price1.amountStandard, price2.amountStandard);
					double diffBasedOnPrice2 = calculPriceDiffPercentage(price2.amountStandard, price1.amountStandard);

					priceInfo.put(RepoConstants.FieldName.PRICE_DIFF_PREFIX+"_"+key2+"_basedOn_"+key1, diffBasedOnPrice1);
					priceInfo.put(RepoConstants.FieldName.PRICE_DIFF_PREFIX+"_"+key1+"_basedOn_"+key2, diffBasedOnPrice2);
				}
			}
			
			esRepository.set(RepoConstants.IndexName.PRICE, RepoConstants.Type.DIFF, priceInfo);
		}
	}

	private double calculPriceDiffPercentage(double reference, double measurement ){
		return (measurement-reference)/reference*100;
	}
	
	
	class Price{
		Currency currency;
		Double amount;
		Double amountStandard;
		
		public Price(Currency currency, Double amount, Double amountStandard) {
			super();
			this.currency = currency;
			this.amount = amount;
			this.amountStandard = amountStandard;
		}
		
		
	}
	
	

}
