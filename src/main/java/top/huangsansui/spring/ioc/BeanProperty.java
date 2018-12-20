package top.huangsansui.spring.ioc;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class BeanProperty {

    private String name;

    private Object value;

    public BeanProperty(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
