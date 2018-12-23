package top.huangsansui.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import top.huangsansui.spring.ioc.BeanFactory;
import top.huangsansui.spring.ioc.BeanFactoryAware;
import top.huangsansui.spring.ioc.BeanProcessor;
import top.huangsansui.spring.ioc.XmlBeanFactory;

import java.util.List;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public class AspectJAwareAdvisorAutoCreator implements BeanProcessor, BeanFactoryAware{

    private XmlBeanFactory xmlBeanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        xmlBeanFactory = (XmlBeanFactory) beanFactory;
    }

    @Override
    public Object processBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object processAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        List<AspectJExpressionPointcutAdvisor> advisors = xmlBeanFactory
                .getBeanForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if (advisor.getPointcut().getClassFilter().matchers(bean.getClass())) {
                ProxyFactory factory = new ProxyFactory();
                factory.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                factory.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                factory.setTargetSource(targetSource);
                return factory.getProxy();
            }
        }
        return bean;
    }
}
