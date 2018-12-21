package top.huangsansui.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {


    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMatcher methodMatcher = advisedSupport.getMethodMatcher();
        if (methodMatcher != null && methodMatcher.matchers(method, advisedSupport.getTargetSource().getTargetClass())) {
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(
                    new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),
                            method,
                            args));
        }
        // 不符合
        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                advisedSupport.getTargetSource().getInterfaces(),
                this);
    }
}
