package top.huangsansui.spring.ioc;

import org.junit.Test;
import top.huangsansui.spring.ioc.XmlBeanFactory;
import top.huangsansui.spring.simple.Car;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class IOCTest {

    @Test
    public void test() throws Exception {
        String location = getClass().getClassLoader().getResource("simple-ioc.xml").getFile();
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(location);
        Car car = (Car) xmlBeanFactory.getBean("car");
        System.out.println(car.toString());
    }
}
