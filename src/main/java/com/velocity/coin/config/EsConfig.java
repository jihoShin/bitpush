package com.velocity.coin.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.velocity.coin.market.poloniex.service.PoloniexService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

	private static Logger logger = LoggerFactory.getLogger(EsConfig.class);


	@Value("${es.cluster.name}")
	private String clusterName;
	
	@Value("${es.hosts:#{null}}")
	private String hosts;

	@Value("${es.transportPort:9300}")
	private int transportPort;


	@Bean
	TransportClient transportClient() throws UnknownHostException {

		logger.info("clustername  : "+clusterName);
		logger.info("hosts        : "+hosts);
		logger.info("transportPort: "+transportPort);
		
		Settings settings = Settings.builder()
				.put("cluster.name", clusterName)
				.put("client.transport.sniff", false)
				.build();
		
		PreBuiltTransportClient client = new PreBuiltTransportClient(settings);
		for(String host: hosts.split(",")) {
			logger.info("host : " + host);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), transportPort));
		}
		return client;
	}
	
	
}
