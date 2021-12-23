package com.abc.test;

import com.abc.spi.Order;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.Set;

public class OrderTest {


/*  输出结果
    before-OrderWrapper对pay()的增强
    before-OrderWrapper222对pay()的增强
    ---  pay 使用微信支付  ---
    after-OrderWrapper222对pay()的增强
    after-OrderWrapper对pay()的增强
    pay 微信支付*/
    @Test
    public void test01() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        Order order = loader.getAdaptiveExtension();
        URL url = URL.valueOf("xxx://localhost:8080/ooo/jjj");
        System.out.println(order.pay(url));
        // System.out.println(order.way());
    }

    @Test
    public void test02() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        Order order = loader.getAdaptiveExtension();
        URL url = URL.valueOf("xxx://localhost:8080/ooo/jjj?order=alipay");
        System.out.println(order.pay(url));
    }

    // wrapper类不属于直接扩展类，其不能独立使用，其是对其它扩展类的增强
/*
    输出结果：[alipay, wechat]
*/
    @Test
    public void test03() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        // 获取该SPI的所有支持的扩展类类名。支持的扩展类也称为直接扩展类
        // 可以独立使用的扩展类就是直接扩展类
        Set<String> extensions = loader.getSupportedExtensions();
        System.out.println(extensions);
    }


}
