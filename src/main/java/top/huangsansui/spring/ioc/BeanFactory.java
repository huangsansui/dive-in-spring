package top.huangsansui.spring.ioc;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public interface BeanFactory {

    Object getBean(String beanId) throws Exception;
}
