package com.velocity.coin.market.bithumb.model;

import java.util.Date;

public class Data { 

	public int opening_price;
	public int closing_price;
	public int min_price;
	public int max_price;
	public double average_price;
	public double units_traded;
	public double volume_1day;
	public double volume_7day;
	public int buy_price;
	public int sell_price;
	public Date date;
	
	
	@Override
	public String toString() {
		return "Data [opening_price=" + opening_price + ", closing_price=" + closing_price + ", min_price=" + min_price
				+ ", max_price=" + max_price + ", average_price=" + average_price + ", units_traded=" + units_traded
				+ ", volume_1day=" + volume_1day + ", volume_7day=" + volume_7day + ", buy_price=" + buy_price
				+ ", sell_price=" + sell_price + ", date=" + date + "]";
	}
	
	
}
