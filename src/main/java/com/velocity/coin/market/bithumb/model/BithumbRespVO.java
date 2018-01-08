package com.velocity.coin.market.bithumb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BithumbRespVO {

	public String status;

	public BithumbData data;

	@Override
	public String toString() {
		return "BithumbRespVO [status=" + status + ", data=" + data + "]";
	}
	
}
