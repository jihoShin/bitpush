package test;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;

/**
 * Created by jhspi on 2018-01-14.
 */
public class Java8Test {

    @Test
    public void test(){

        System.out.println("test");

        List<Car> carList = new LinkedList<>();
        carList.add(new Car("jiho1", 1));
        carList.add(new Car("jiho2", 2));
        carList.add(new Car("jiho3", 3));

        Map result = carList.stream()
                .collect(
                        Collectors.toMap(
                                a->a.name,
                                a->a,
                                (oldValue, newValue) -> oldValue,
                                HashMap::new
                        ));
        for(Object key : result.keySet()){
            String keyStr = (String) key;
            System.out.println("key : "+key);

            Car car = (Car) result.get(key);

            System.out.println("car : "+car);



        }


    }

    class Car{
        String name;
        int price;

        public Car(String name, int price) {
            this.name = name;
            this.price = price;
        }


        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
