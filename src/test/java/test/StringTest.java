package test;

import org.junit.Test;

public class StringTest {
	
	
	
	@Test
	public void test(){
		
		String bin = "jiho,jiho1";
		
		String[] args = bin.split(",");

		for(String str : args){
			System.out.println(str);
		}
		
		
	}

}
