package com.velocity.coin.model;

import java.lang.reflect.Field;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.velocity.coin.market.bithumb.model.BithumbTicker;

public abstract class BasicTicker {

	public Market market;

	public Coin symbol;
	
	public Currency currency;
	
	public Double price;
	
	public Date date;
	
	public BasicTicker(Market market, Coin symbol, Currency currency, Double price, Date date) {
		super();
		this.market = market;
		this.symbol = symbol;
		this.price = price;
		this.date = date;
	}

	public Map<String, ?> toMap(){
		Map<String, Object> map = new HashMap<>();
		for(Field field : BithumbTicker.class.getFields()){
			String key = field.getName();
			Object value = null;
			try {
				value = field.get(this);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		}
		return map;
	}
	
}
