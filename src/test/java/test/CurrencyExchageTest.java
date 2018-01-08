package test;

import java.util.Currency;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.velocity.coin.exchange.CurrencyExchangeService;
import com.velocity.coin.exchange.model.HistoricalRespVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (classes = ApplictionTest.class)
public class CurrencyExchageTest {

	@Autowired
	CurrencyExchangeService currencyExchangeService;
	
	@Test
	public void test(){
		
		
		Double result = currencyExchangeService.getExchageRate(Currency.getInstance("USD"), Currency.getInstance("KRW"));
		System.out.println("result : "+result);

	}

	public void test2(){

		HistoricalRespVO historicalRespVO = currencyExchangeService.historical(Currency.getInstance("KRW"), new Date());
		System.out.println("historicalRespVO : "+historicalRespVO);
	}


	public void test3(){
		Double result;
		System.out.println("test3");

		for(int i=0 ; i<100 ; i++){

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

//			result = currencyExchangeService.getExchageRateForUSD("KRW");
//			System.out.println("result : "+result);
		}

	}

}
