package top.huangsansui.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public class LogInterceptor implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("=====> proxy before");
        Object object = invocation.proceed();
        System.out.println("=====> proxy after");
        return object;
    }
}
