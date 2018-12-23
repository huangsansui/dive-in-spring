package top.huangsansui.spring.aop;


import org.aopalliance.aop.Advice;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{

    private AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setExpression(String expression) {
        aspectJExpressionPointcut.setExpression(expression);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return aspectJExpressionPointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }
}
