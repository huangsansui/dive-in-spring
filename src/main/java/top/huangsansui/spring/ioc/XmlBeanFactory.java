package top.huangsansui.spring.ioc;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class XmlBeanFactory implements BeanFactory {

    private XmlBeanDefinitionReader definitionReader;

    public XmlBeanFactory(String location) {
        definitionReader = new XmlBeanDefinitionReader();
        // 读取配置
        locationBeanDefinitions(location);
    }

    private void locationBeanDefinitions(String location) {
        definitionReader.locationBeanDefinitions(location);
    }

    public Object getBean(String beanId) {
        return null;
    }
}
