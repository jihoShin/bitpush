package bitpush;

import java.util.Currency;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.velocity.coin.Application;
import com.velocity.coin.exchange.CurrencyExchangeService;
import com.velocity.coin.exchange.model.HistoricalRespVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (classes = Application.class)
public class Test1 {

	@Autowired
	CurrencyExchangeService currencyExchangeService;

	public void test(){

		for(Currency c : Currency.getAvailableCurrencies()){
			System.out.println(c.toString());
		}

	}

	public void test2(){

		HistoricalRespVO historicalRespVO = currencyExchangeService.historical(Currency.getInstance("KRW"), new Date());
		System.out.println("historicalRespVO : "+historicalRespVO);
	}

	@Test
	public void test3(){
		Double result;
		System.out.println("test3");

		for(int i=0 ; i<100 ; i++){

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			result = currencyExchangeService.getExchageRateForUSD("KRW");
			System.out.println("result : "+result);
		}

	}

}
