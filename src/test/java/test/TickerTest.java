package test;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.velocity.coin.market.bithumb.model.BithumbTicker;
import com.velocity.coin.model.Coin;

public class TickerTest {

	
	@Test
	public void test(){
		
		
		
		Double d = new Double("1");
		Integer i = new Integer(11);
		
//		public BithumbTicker(Coin symbol, Date date, Integer opening_price,
//				Integer closing_price, Integer min_price, Integer max_price, Double average_price, Double units_traded,
//				Double volume_1day, Double volume_7day, Integer buy_price, Integer sell_price) {
		
		BithumbTicker bt = new BithumbTicker(Coin.BTC, 
				new Date(), 
				i, 
				i, 
				i,
				i, 
				d,
				d,
				d,
				d,
				i,
				i);
		
		;
		System.out.println("currency : "+bt.currency);
		
		Map<String, ?> map = bt.toMap();
		for(Entry<String, ?> entry : map.entrySet()){
			
			System.out.println("key : "+entry.getKey());
			System.out.println("val : "+entry.getValue());
			System.out.println();
			
		}
		
		
	}
}
