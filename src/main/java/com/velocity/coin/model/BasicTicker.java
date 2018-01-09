package com.velocity.coin.model;

import java.lang.reflect.Field;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class BasicTicker {

	public Market market;

	public Coin coin;
	
	public Currency currency;
	
	public Double price;
	
	public Date date;
	
	public BasicTicker(Market market, Coin coin, Currency currency, Double price, Date date) {
		super();
		this.market = market;
		this.coin = coin;
		this.currency = currency;
		this.price = price;
		this.date = date;
	}

	public Map<String, ?> toMap(){
		Map<String, Object> map = new HashMap<>();
		
		for(Field field : this.getClass().getFields()){
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
