package com.velocity.coin.repository;

import java.util.Map;

import com.velocity.coin.constant.RepoConstants;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.velocity.coin.model.Market;

//@Repository(RepoConstants.Repository.ELASTICSEARCH)
public class EsRepository implements IRepository{
	
	@Autowired
	private TransportClient client;

	public void set(String index, String type, Map<String, ?> map){
		IndexResponse response = client.prepareIndex(index, type).setSource(map).get();
	}
	
	
}
