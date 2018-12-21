package top.huangsansui.spring.simple;

import org.junit.Test;
import top.huangsansui.spring.simple.Car;
import top.huangsansui.spring.simple.SimpleIOC;
import top.huangsansui.spring.simple.Wheel;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/19
 * @since: JDK 1.8
 */
public class SimpleIOCTest {

    @Test
    public void test() throws Exception {
        SimpleIOC simpleIOC = new SimpleIOC("src/main/resources/simple-ioc.xml");
        Car car = (Car) simpleIOC.getBean("car");
        System.out.println(car);
        Wheel wheel = (Wheel) simpleIOC.getBean("wheel");
        System.out.println(wheel);
    }

}
