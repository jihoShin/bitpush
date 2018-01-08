package com.velocity.coin.market.poloniex.model;

import java.util.Currency;
import java.util.Date;

import com.velocity.coin.model.BasicTicker;
import com.velocity.coin.model.Coin;
import com.velocity.coin.model.Market;

public class PoloniexTicker extends BasicTicker{
	
	private final static Market MARKET = Market.Poloniex;

	private final static Currency CURRENCY = Currency.getInstance("USD");
	
	public String id;
	public Double last;
	public Double lowestAsk;
	public Double highestBid;
	public Double percentChange;
	public Double quoteVolume;
	public Double baseVolume;
	public Double high24hr;
	public Double low24hr;
	public Integer isFrozen;
	
	public PoloniexTicker(Coin symbol, Date date, String id,
			Double last, Double lowestAsk, Double highestBid, Double percentChange, Double quoteVolume,
			Double baseVolume, Double high24hr, Double low24hr, int isFrozen) {
		super(MARKET, symbol, CURRENCY, last, date);
		this.id = id;
		this.last = last;
		this.lowestAsk = lowestAsk;
		this.highestBid = highestBid;
		this.percentChange = percentChange;
		this.quoteVolume = quoteVolume;
		this.baseVolume = baseVolume;
		this.high24hr = high24hr;
		this.low24hr = low24hr;
		this.isFrozen = isFrozen;
	}
	

}
