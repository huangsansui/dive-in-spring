package top.huangsansui.spring.simple;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/21
 * @since: JDK 1.8
 */
public class LogMethodInvocation extends MethodInvocation{

    public void doSomething() {
        System.out.println("====> before");
    }
}
