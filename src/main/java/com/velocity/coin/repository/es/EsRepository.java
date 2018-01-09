package com.velocity.coin.repository.es;

import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.velocity.coin.model.Market;

@Repository
public class EsRepository {
	
	@Autowired
	private TransportClient client;


	public IndexResponse set(String index, String type, Map<String, ?> map){
		IndexResponse response = client.prepareIndex(index, type).setSource(map).get();
		return response;
	}
	
	
}
