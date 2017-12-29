package com.velocity.coin.config;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {


	@Value("${restTemplate.proxy.enable:false}")
	private boolean proxyEnable;
	
	@Value("${restTemplate.proxy.host:#{null}}")
	private String proxyHost;

	@Value("${restTemplate.proxy.port:0}")
	private int proxyPort;
	
	@Value("${restTemplate.config.maxTotalConnections:1000}")
	private int maxTotal = 1000;
	
	@Value("${restTemplate.config.maxPerRoute:1000}")
	private int maxPerRoute = 500;

	@Value("${restTemplate.config.connectionTimeout:5000}")
	private int connectionTimeout = 5 * 1000;

	@Value("${restTemplate.config.readTimeout:5000}")
	private int readTimeout = 5 * 1000;
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {

		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder(){
			public <T extends RestTemplate> T configure(T restTemplate) {
				HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
				clientHttpRequestFactory.setConnectTimeout(connectionTimeout);
				clientHttpRequestFactory.setReadTimeout(readTimeout);
				clientHttpRequestFactory.setHttpClient(httpClient());
				
				restTemplate.setRequestFactory(clientHttpRequestFactory);

				return restTemplate;
			};
		};

		return restTemplateBuilder.build();
	}
	
	private HttpClient httpClient() {
		return HttpClients.custom()
				.setConnectionManager(getHttpClientConnectionManager())
				.setDefaultRequestConfig(getRequestConfig())
				.build();
	}
	
	private HttpClientConnectionManager getHttpClientConnectionManager(){
		
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(maxTotal);
		connectionManager.setDefaultMaxPerRoute(maxPerRoute);
		
		return connectionManager;
	}
	
	private RequestConfig getRequestConfig(){
		
		RequestConfig.Builder requestConfig = RequestConfig.custom();
		if(proxyEnable){
			requestConfig.setProxy(new HttpHost(proxyHost, proxyPort, "http"));
		}
		
		return requestConfig.build();
		
		
	}
	
	
}
