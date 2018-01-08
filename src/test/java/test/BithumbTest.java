package test;

import com.velocity.coin.market.bithumb.model.BithumbRespVO;
import com.velocity.coin.market.bithumb.service.BithumbService;
import com.velocity.coin.model.Coin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplictionTest.class)
public class BithumbTest {

    @Autowired
    private BithumbService bithumbService;

    @Test
    public void test(){

        BithumbRespVO respVO = bithumbService.ticker(Coin.BTC);
        System.out.println("respVO : "+respVO);

    }
}
