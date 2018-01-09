package test;

import org.junit.Test;

/**
 * Created by 지호 on 2018-01-10.
 */
public class PriceTest {


    @Test
    public void test(){

        double a = 10000;
        double b = 11000;


        double result1 = calculPriceDiffPercentage(a, b);
        double result2 = calculPriceDiffPercentage(b, a);


        System.out.println("result1 : "+result1);

        System.out.println("result2 : "+result2);
    }

    private double calculPriceDiffPercentage(double a, double b){
        return (b-a)/a*100;
    }

}
