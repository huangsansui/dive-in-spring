package top.huangsansui.spring;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/19
 * @since: JDK 1.8
 */
public class Car {

    private String name;

    private String price;

    private Wheel wheel;

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", wheel=" + wheel +
                '}';
    }
}
