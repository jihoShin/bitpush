package com.velocity.coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.velocity.coin")
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class Application {

	public static void main(String[] args){
		System.out.println("test");
		SpringApplication.run(Application.class, args);
	}
	
}

