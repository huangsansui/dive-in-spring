package top.huangsansui.spring.simple;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/19
 * @since: JDK 1.8
 */
public class SimpleIOC {

    private HashMap<String, Object> map = new HashMap();

    public SimpleIOC(String location) throws Exception {
        loadBean(location);
    }

    private void loadBean(String location) throws Exception {
        // 读取xml配置
        InputStream is = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("bean");

        // 遍历bean标签
        for (int i=0;i<nodes.getLength();i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");
                // 反射生成bean
                Class beanClass = null;
                try {
                    // 加载该类
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    System.out.println("bean class config error");
                    e.printStackTrace();
                    return;
                }
                Object bean = beanClass.newInstance();

                // 遍历property标签
                NodeList childNodes = node.getChildNodes();
                for (int j=0;j<childNodes.getLength();j++) {
                    Node propertyNode = childNodes.item(j);
                    if (propertyNode instanceof Element) {
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");
                        // 获取bean属性字段
                        Field declaredField = bean.getClass().getDeclaredField(name);
                        // 修改访问权限
                        declaredField.setAccessible(true);
                        // 判断是属性还是引用
                        if (value != null && value.length() > 0) {
                            Class<?> type = declaredField.getType();
                            Object trueValue = getPropertyValue(type, value);
                            declaredField.set(bean, trueValue);
                        } else {
                            // 引用
                            // 递归调用
                            String ref = propertyElement.getAttribute("ref");
                            if (ref == null || ref.length() == 0) {
                                throw new IllegalAccessException("ref config error");
                            }
                            declaredField.set(bean, getBean(name));
                        }
                    }
                }
                registerBean(id, bean);
            }
        }
    }

    private Object getPropertyValue(Class<?> type, String value) {
        String typeStr = type.toString();
        Object object = null;
        if (typeStr.endsWith("String")) {
            object = value;
        } else if (typeStr.endsWith("int") || typeStr.endsWith("Integer")) {
            object = Integer.parseInt(value);
        } else if (typeStr.endsWith("long") || typeStr.endsWith("Long")) {
            object = Long.parseLong(value);
        } else if (typeStr.endsWith("byte") || typeStr.endsWith("Byte")) {
            object = Byte.parseByte(value);
        } else if (typeStr.endsWith("boolean") || typeStr.endsWith("Boolean")) {
            object = Boolean.parseBoolean(value);
        } else if (typeStr.endsWith("float") || typeStr.endsWith("Float")) {
            object = Float.parseFloat(value);
        } else if (typeStr.endsWith("double") || typeStr.endsWith("Double")) {
            object = Double.parseDouble(value);
        }
        return object;
    }


    public void registerBean(String id, Object bean) {
        map.put(id, bean);
    }

    public Object getBean(String name) {
        return map.get(name);
    }
}
