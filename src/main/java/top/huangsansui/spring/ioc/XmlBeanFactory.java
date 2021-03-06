package top.huangsansui.spring.ioc;

import top.huangsansui.spring.aop.AspectJExpressionPointcutAdvisor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class XmlBeanFactory implements BeanFactory {

    private XmlBeanDefinitionReader definitionReader;

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanProcessor> beanProcessors = new ArrayList<>();

    /**
     * 读取xml配置
     * 将bean加载注册
     * @param location
     * @throws Exception
     */
    public XmlBeanFactory(String location) throws Exception {
        definitionReader = new XmlBeanDefinitionReader();
        locationBeanDefinitions(location);
    }

    private void locationBeanDefinitions(String location) throws Exception {
        // 读取xml配置
        definitionReader.locationBeanDefinitions(location);
        // 注册bean
        registerBeanDefinition();
        // 织入切面方法
        registerBeanProcessors();
    }

    private void registerBeanProcessors() throws Exception {
        List beans = getBeanForType(BeanProcessor.class);
        for (Object bean : beans) {
            beanProcessors.add((BeanProcessor) bean);
        }
    }

    private void registerBeanDefinition() {
        for (Map.Entry entry : definitionReader.getRegister().entrySet()) {
            String name = (String) entry.getKey();
            BeanDefinition beanDefinition = (BeanDefinition) entry.getValue();
            beanDefinitionMap.put(name, beanDefinition);
            beanDefinitionNames.add(name);
        }
    }

    /**
     * 获取bean
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("no this bean with name : " + name);
        }
        // 延迟加载
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = createBean(beanDefinition);
            bean = initialization(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    private Object initialization(Object bean, String name) throws Exception {
        for (BeanProcessor beanProcessor : beanProcessors) {
            bean = beanProcessor.processBeforeInitialization(bean, name);
        }
        for (BeanProcessor beanProcessor : beanProcessors) {
            bean = beanProcessor.processAfterInitialization(bean, name);
        }
        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = beanDefinition.getBeanClass().newInstance();
        if (bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        List<BeanProperty> propertyList = beanDefinition.getBeanProperties().getPropertyList();
        for (BeanProperty property : propertyList) {
            setPropertyValue(bean, property);
        }
        return bean;
    }

    private void setPropertyValue(Object bean, BeanProperty property) throws Exception {
        // 反射
        Object value = property.getValue();
        // 引用
        if (value instanceof BeanReference) {
            BeanReference reference = (BeanReference) value;
            // 递归
            value = getBean(reference.getName());
        }
        // ps: 这里只做了字符串和引用类型,因为xml读取的值为字符串类型,转换判断不在这里详细写出
        try {
            Method setMethod = bean.getClass().getDeclaredMethod("set" +
                            property.getName().substring(0, 1).toUpperCase() +
                            property.getName().substring(1),
                    property.getValue().getClass());
            setMethod.setAccessible(true);
            setMethod.invoke(bean, value);
        } catch (NoSuchMethodException e) {
            Field field = bean.getClass().getDeclaredField(property.getName());
            field.setAccessible(true);
            field.set(bean, value);
        }

    }

    public List getBeanForType(Class<?> type) throws Exception {
        List beans = new ArrayList();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
