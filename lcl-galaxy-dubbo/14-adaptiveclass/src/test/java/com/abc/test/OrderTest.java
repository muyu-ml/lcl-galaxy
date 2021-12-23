package com.abc.test;

import com.abc.spi.AdaptiveOrder;
import com.abc.spi.Order;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.Set;

public class OrderTest {

    @Test
    public void test01() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        // 获取Order的自适应扩展类实例
        Order order = loader.getAdaptiveExtension();
        System.out.println(order.way());
    }

    @Test
    public void test02() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        // 获取Order的自适应扩展类实例
        Order order = loader.getAdaptiveExtension();
        ((AdaptiveOrder)order).setDefaultName("alipay");
        System.out.println(order.way());
    }

    // adaptive类不属于直接扩展类，其不能独立使用，其是对其它扩展类的选择方式
    @Test
    public void test03() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        // 获取该SPI的所有支持的扩展类类名。支持的扩展类也称为直接扩展类
        // 可以独立使用的扩展类就是直接扩展类
        Set<String> extensions = loader.getSupportedExtensions();
        System.out.println(extensions);
    }

}
