<beans>
    <bean id="wheel" class="top.huangsansui.spring.simple.Wheel">
        <property name="name" value="Michelin"/>
        <property name="size" value="15mm"/>
    </bean>
    <bean id="car" class="top.huangsansui.spring.simple.Car">
        <property name="name" value="Audi"></property>
        <property name="price" value="1000元"></property>
        <property name="wheel" ref="wheel"></property>
    </bean>
</beans>