package com.velocity.coin.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

	@Value("${es.cluster.name}")
	private String clusterName;
	
	@Value("${es.hosts:#{null}}")
	private String hosts;

	@Value("${es.transportPort:9300}")
	private int transportPort;
	

	@Bean
	TransportClient transportClient() throws UnknownHostException {
		
		
		System.out.println("clustername  : "+clusterName);
		System.out.println("hosts        : "+hosts);
		System.out.println("transportPort: "+transportPort);
		
		Settings settings = Settings.builder()
				.put("cluster.name", clusterName)
				.put("client.transport.sniff", false)
				.build();
		
		PreBuiltTransportClient client = new PreBuiltTransportClient(settings);
		for(String host: hosts.split(",")) {
			System.out.println("host : " + host);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), transportPort));
		}
		return client;
	}
	
	
}
