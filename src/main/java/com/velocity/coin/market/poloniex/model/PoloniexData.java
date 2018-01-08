package com.velocity.coin.market.poloniex.model;

public class PoloniexData {
	
	public String id;
	public Double last;
	public Double lowestAsk;
	public Double highestBid;
	public Double percentChange;
	public Double quoteVolume;
	public Double baseVolume;
	public Double high24hr;
	public Double low24hr;
	public int isFrozen;
	
	@Override
	public String toString() {
		return "Data [id=" + id + ", last=" + last + "]";
	}
	
	
}
