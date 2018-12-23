package top.huangsansui.spring.aop;


import org.aopalliance.aop.Advice;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public interface Advisor {

    Advice getAdvice();
}
