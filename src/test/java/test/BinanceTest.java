package test;

import com.velocity.coin.market.binance.model.BinanceData;
import com.velocity.coin.market.binance.service.BinanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by 지호 on 2018-01-13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplictionTest.class)
public class BinanceTest {


    @Autowired
    BinanceService binanceService;

    @Test
    public void test(){

        Map<String, BinanceData> result = binanceService.ticker();

        for(Map.Entry<String, BinanceData> entry: result.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }
}
