package top.huangsansui.spring.aop;

/**
 * Function:
 *
 * @author: Huangqing
 * @Date: 2018/12/22
 * @since: JDK 1.8
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {


    @Override
    public Object getProxy() {
        return createProxy().getProxy();
    }

    private AopProxy createProxy() {
        return new JdkDynamicAopProxy(this);
    }
}
