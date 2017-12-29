package com.velocity.coin.repository.es;

import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.velocity.coin.model.Market;

@Repository
public class EsRepository {
	
	@Autowired
	private TransportClient client;
	
	@Deprecated
	public IndexResponse set(String index, Market type, String jsonDoc){
		IndexResponse response = client.prepareIndex(index, type.name())
				.setSource(jsonDoc, XContentType.JSON).get();
		return response;
	}

	public IndexResponse set(String index, Market type, Map<String, ?> map){
		IndexResponse response = client.prepareIndex(index, type.name())
				.setSource(map).get();
		return response;
	}
	
}
