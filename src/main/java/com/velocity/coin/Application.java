package com.velocity.coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan(value="com.velocity.coin")
@SpringBootApplication
@EnableCaching
@EnableScheduling
@ExculdeFromTest
public class Application {

	public static void main(String[] args){
		System.out.println("main");
		SpringApplication.run(Application.class, args);
	}
	
}

