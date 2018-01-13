package com.velocity.coin.market.binance.model;

import com.velocity.coin.model.BasicTicker;
import com.velocity.coin.model.Coin;
import com.velocity.coin.model.Market;

import java.util.Currency;
import java.util.Date;

/**
 * Created by 지호 on 2018-01-13.
 */
public class BinanceTicker extends BasicTicker {

    private final static Market MARKET = Market.Binance;

    private final static Currency CURRENCY = Currency.getInstance("USD");

    public BinanceTicker(Coin coin, Double price, Date date) {
        super(MARKET, coin, CURRENCY, price, date);
    }
}
