package com.velocity.coin.repository;

import org.elasticsearch.action.index.IndexResponse;

import java.util.Map;

/**
 * Created by jhspi on 2018-01-14.
 */
public interface IRepository {

    public void set(String index, String type, Map<String, ?> map);

}
