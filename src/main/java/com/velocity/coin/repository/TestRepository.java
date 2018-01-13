package com.velocity.coin.repository;

import com.sun.org.apache.bcel.internal.generic.IREM;
import com.velocity.coin.exchange.CurrencyExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by jhspi on 2018-01-14.
 */
@Repository
public class TestRepository implements IRepository{

    private static Logger logger = LoggerFactory.getLogger(TestRepository.class);

    @Override
    public void set(String index, String type, Map<String, ?> map) {
        logger.info("set"+ index+","+type);
        logger.info(map.toString());
    }
}
