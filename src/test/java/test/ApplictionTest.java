package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.velocity.coin.Application;
import com.velocity.coin.ExculdeFromTest;

@ComponentScan(value="com.velocity.coin", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExculdeFromTest.class))
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ApplictionTest {

	public static void main(String[] args){
		System.out.println("test");
		SpringApplication.run(Application.class, args);
	}
	
}

