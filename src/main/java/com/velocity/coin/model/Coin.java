package com.velocity.coin.model;

public enum Coin {
	
	BTC("Bitcoin"),
	ETH("Ethereum"),
	XRP("Ripple"),;
	
	public String coinName;
	
	Coin(String coinName){
		this.coinName = coinName;
	}

}
