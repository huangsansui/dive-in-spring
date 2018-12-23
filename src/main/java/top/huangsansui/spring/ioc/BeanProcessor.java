package top.huangsansui.spring.ioc;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public interface BeanProcessor {

    Object processBeforeInitialization(Object bean, String beanName);

    Object processAfterInitialization(Object object, String beanName) throws Exception;
}
