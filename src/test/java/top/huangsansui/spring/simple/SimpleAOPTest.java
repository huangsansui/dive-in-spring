package top.huangsansui.spring.simple;

import org.junit.Test;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class SimpleAOPTest {

    @Test
    public void test() {
        HelloService service = new HelloServiceImpl();
        MethodInvocation methodInvocation = new LogMethodInvocation();
        Advice before = new BeforeAdvice(service, methodInvocation);
        HelloService proxy = (HelloService) SimpleAOP.getProxy(service, before);
        proxy.sayHello();
    }
}
