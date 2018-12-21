package top.huangsansui.spring.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class XmlBeanDefinitionReader {

    private HashMap<String, BeanDefinition> register;

    public XmlBeanDefinitionReader() {
        register = new HashMap<String, BeanDefinition>();
    }

    /**
     * 读取xml配置
     * @param location
     */
    public void locationBeanDefinitions(String location) throws Exception {
        InputStream is = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document parse = documentBuilder.parse(is);
        // 获取根节点
        Element root = parse.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) throws Exception {
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node bean = nodes.item(i);
            if (bean instanceof Element) {
                parseBeanDefinition((Element)bean);
            }
        }
    }

    private void parseBeanDefinition(Element bean) throws Exception {
        String id = bean.getAttribute("id");
        String className = bean.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        processProperty(bean, beanDefinition);
        register.put(id, beanDefinition);
    }

    private void processProperty(Element bean, BeanDefinition beanDefinition) throws Exception {
        NodeList nodeList = bean.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element property = (Element) node;
                String name = property.getAttribute("name");
                String value = property.getAttribute("value");
                // value属性
                if (value != null && value.length() > 0) {
                    beanDefinition.getBeanProperties().addProperty(new BeanProperty(name, value));
                } else {
                    // ref属性
                    String ref = property.getAttribute("ref");
                    if (ref == null || ref.length() <= 0) {
                        throw new IllegalAccessException("ref config error");
                    }
                    BeanReference reference = new BeanReference(ref);
                    beanDefinition.getBeanProperties().addProperty(new BeanProperty(name, reference));
                }
            }
        }
    }

    public HashMap<String, BeanDefinition> getRegister() {
        return register;
    }
}
