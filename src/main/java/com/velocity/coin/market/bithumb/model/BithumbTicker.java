package com.velocity.coin.market.bithumb.model;

import java.util.Currency;
import java.util.Date;

import com.velocity.coin.model.BasicTicker;
import com.velocity.coin.model.Coin;
import com.velocity.coin.model.Market;

public class BithumbTicker extends BasicTicker{
	
	private static Currency CURRENCY = Currency.getInstance("KRW");

	private static Market MARKET = Market.Bithumb;
	
	public BithumbTicker(Coin symbol, Date date, Integer opening_price,
			Integer closing_price, Integer min_price, Integer max_price, Double average_price, Double units_traded,
			Double volume_1day, Double volume_7day, Integer buy_price, Integer sell_price) {
		
		super(MARKET, symbol, CURRENCY, closing_price.doubleValue(), date);
		
		this.opening_price = opening_price;
		this.closing_price = closing_price;
		this.min_price = min_price;
		this.max_price = max_price;
		this.average_price = average_price;
		this.units_traded = units_traded;
		this.volume_1day = volume_1day;
		this.volume_7day = volume_7day;
		this.buy_price = buy_price;
		this.sell_price = sell_price;
	}

	public Integer opening_price;
	
	public Integer closing_price;
	
	public Integer min_price;
	
	public Integer max_price;
	
	public Double average_price;
	
	public Double units_traded;
	
	public Double volume_1day;
	
	public Double volume_7day;
	
	public Integer buy_price;
	
	public Integer sell_price;

}
