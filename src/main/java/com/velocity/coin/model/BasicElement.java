package com.velocity.coin.model;


import java.io.IOException;
import java.lang.reflect.Array;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicElement {

	@JsonIgnore
	public String id;
	
	@JsonIgnore
	public String type;
	
	@JsonIgnore
	public float score;
	
	public static ObjectMapper om = new ObjectMapper();

	public static <T extends BasicElement> T parseSearchHit(SearchHit hit, Class<T> valueType) {
		T value = null;
		if(hit != null) {
			try {
				value = om.readValue(hit.getSourceAsString(), valueType);
				if(value != null) {
					value.type = hit.getType();
					value.id = hit.getId();
					value.score = hit.getScore();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends BasicElement> T[] parseSearchHitArray(SearchHit[] hits, Class<T> valueType) {
		T[] values = null;
		if(hits != null) {
			values = (T[]) Array.newInstance(valueType, hits.length);
			for(int i=0; i<hits.length; i++) {
				values[i] = parseSearchHit(hits[i], valueType);
			}
		}
		return values;
	}

	public static <T extends BasicElement> T parseGetResponse(GetResponse resp, Class<T> valueType) {
		T value = null;
		if(resp != null && resp.isExists()) {
			try {
				value = om.readValue(resp.getSourceAsString(), valueType);
				if(value != null) {
					value.type = resp.getType();
					value.id = resp.getId();
					value.score = 1;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public String toJson() {
		String json = null;
		try {
			json = om.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
