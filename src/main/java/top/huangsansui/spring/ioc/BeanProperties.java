package top.huangsansui.spring.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class BeanProperties {

    private List<BeanProperty> propertyList = new ArrayList<BeanProperty>();

    public void addProperty(BeanProperty property) {
        propertyList.add(property);
    }
}
