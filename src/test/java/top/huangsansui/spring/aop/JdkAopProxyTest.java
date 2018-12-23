package top.huangsansui.spring.aop;

import org.junit.Test;
import top.huangsansui.spring.aop.AdvisedSupport;
import top.huangsansui.spring.aop.LogInterceptor;
import top.huangsansui.spring.aop.TargetSource;
import top.huangsansui.spring.simple.HelloService;
import top.huangsansui.spring.simple.HelloServiceImpl;

import java.lang.reflect.Method;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public class JdkAopProxyTest {

    @Test
    public void test() {
        System.out.println("------- no proxy ------");
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello();

        System.out.println("------- jdk proxy ------");
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloService,
                HelloServiceImpl.class, HelloServiceImpl.class.getInterfaces());
        advisedSupport.setMethodInterceptor(new LogInterceptor());
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher((Method method, Class className) -> true);
        JdkDynamicAopProxy proxy = new JdkDynamicAopProxy(advisedSupport);
        helloService = (HelloService) proxy.getProxy();
        helloService.sayHello();
    }
}
