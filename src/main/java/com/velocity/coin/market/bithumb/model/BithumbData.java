package com.velocity.coin.market.bithumb.model;

import java.util.Date;

public class BithumbData { 

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
	public Date date;
	
	
	@Override
	public String toString() {
		return "Data [opening_price=" + opening_price + ", closing_price=" + closing_price + ", min_price=" + min_price
				+ ", max_price=" + max_price + ", average_price=" + average_price + ", units_traded=" + units_traded
				+ ", volume_1day=" + volume_1day + ", volume_7day=" + volume_7day + ", buy_price=" + buy_price
				+ ", sell_price=" + sell_price + ", date=" + date + "]";
	}
	
	
}
