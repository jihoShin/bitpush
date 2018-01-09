package com.velocity.coin.model;

public enum Coin {
	
	BTC("Bitcoin"),
	ETH("Ethereum"),
	XRP("Ripple"),
	BCH("BitcoinCash"),
	LTC("LiteCoin"),
	DASH("Dash"),
	QTUM("Quantum")

	;
	
	public String coinName;
	
	Coin(String coinName){
		this.coinName = coinName;
	}

}
