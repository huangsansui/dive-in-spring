package top.huangsansui.spring.aop;

import java.lang.reflect.Method;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public interface MethodMatcher {

    boolean matchers(Method method, Class beanName);
}
