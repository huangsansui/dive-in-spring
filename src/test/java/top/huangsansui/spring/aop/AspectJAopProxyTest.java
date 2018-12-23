package top.huangsansui.spring.aop;

import org.junit.Assert;
import org.junit.Test;
import top.huangsansui.spring.simple.HelloService;

import static org.junit.Assert.assertTrue;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public class AspectJAopProxyTest {

    @Test
    public void testClassFilter() {
        String expression = "execution(* top.huangsansui.spring.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.matchers(HelloService.class);
        assertTrue(matches);
    }
}
