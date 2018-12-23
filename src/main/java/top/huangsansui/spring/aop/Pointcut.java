package top.huangsansui.spring.aop;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
