package com.velocity.coin.exchange.model;

import java.util.Date;
import java.util.Map;

public class HistoricalRespVO {
	
	public String success;
	
	public String terms;
	
	public String privacy;
	
	public String date;
	
	public Date timestamp;
	
	public String source;
	
	public Map<String, Double> quotes;

	@Override
	public String toString() {
		return "RespVO [success=" + success + ", terms=" + terms + ", privacy=" + privacy + ", date=" + date
				+ ", timestamp=" + timestamp + ", source=" + source + ", quotes=" + quotes + "]";
	}

}
