package top.huangsansui.spring.aop;

import org.junit.Test;
import top.huangsansui.spring.ioc.XmlBeanFactory;
import top.huangsansui.spring.simple.HelloService;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/23
 * @since: JDK 1.8
 */
public class IocCombineAopTest {

    @Test
    public void test() throws Exception {
        String location = getClass().getClassLoader().getResource("ioc.xml").getFile();
        XmlBeanFactory factory = new XmlBeanFactory(location);
        HelloService helloService = (HelloService) factory.getBean("helloService");
        helloService.sayHello();
    }
}
