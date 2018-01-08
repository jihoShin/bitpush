package test;

import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.velocity.coin.Application;
import com.velocity.coin.market.poloniex.model.PoloniexData;
import com.velocity.coin.market.poloniex.service.PoloniexService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (classes = ApplictionTest.class)
public class PoloniexTest {

	@Autowired
	PoloniexService PoloniexService;
	
	@Test
	public void test2(){
		System.out.println("test2");
		HashMap<String, PoloniexData> result = PoloniexService.ticker();
		
		System.out.println("-------------------------");
		for(Entry<String, PoloniexData> entry : result.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		
		System.out.println("-------------------------");
		PoloniexData data = result.get("USDT_BTC");
		System.out.println("data : "+data);
		
	}
	
}
