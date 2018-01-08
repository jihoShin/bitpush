package com.velocity.coin.model;

public enum Market {
	Bithumb("KR"),
	Poloniex("US"),
	;
	
	public String counrtyCode;
	
	Market(String counrtyCode){
		this.counrtyCode = counrtyCode;
	}
	
}
