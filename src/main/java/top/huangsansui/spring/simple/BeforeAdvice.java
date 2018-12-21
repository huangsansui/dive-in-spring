package top.huangsansui.spring.simple;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class BeforeAdvice implements Advice {

    private Object bean;

    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.doSomething();
        return method.invoke(bean, args);
    }
}
