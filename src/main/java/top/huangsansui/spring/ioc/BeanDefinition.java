package top.huangsansui.spring.ioc;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class BeanDefinition {

    private String bean;

    private Class beanClass;

    private String beanClassName;

    private BeanProperties beanProperties = new BeanProperties();

    public BeanProperties getBeanProperties() {
        return beanProperties;
    }

    public void setBeanProperties(BeanProperties beanProperties) {
        this.beanProperties = beanProperties;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}
