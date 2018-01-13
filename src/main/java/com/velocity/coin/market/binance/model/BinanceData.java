package com.velocity.coin.market.binance.model;

/**
 * Created by 지호 on 2018-01-13.
 */
public class BinanceData {

    public String symbol;
    public Double price;

    @Override
    public String toString() {
        return "BinanceData{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
