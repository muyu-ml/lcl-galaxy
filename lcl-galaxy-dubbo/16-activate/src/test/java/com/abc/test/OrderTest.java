package com.abc.test;

import com.abc.spi.Order;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class OrderTest {

/*    输出结果：
            ---  使用微信支付  ---
            微信支付
            ---  使用银联卡支付  ---
            银联卡支付*/
    @Test
    public void test01() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        URL url = URL.valueOf("xxx://localhost:8080/ooo/jjj");
        // 激活所有group为online线上支付的扩展类
        List<Order> online = loader.getActivateExtension(url, "", "online");
        for (Order order : online) {
            System.out.println(order.way());
        }
    }


/*    ---  使用银联卡支付  ---
    银联卡支付
    ---  使用现金支付  ---
    现金支付
    ---  使用购物券支付  ---
    购物券支付*/
    @Test
    public void test02() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        URL url = URL.valueOf("xxx://localhost:8080/ooo/jjj");
        // 激活所有group为offline线下支付的扩展类
        List<Order> online = loader.getActivateExtension(url, "", "offline");
        for (Order order : online) {
            System.out.println(order.way());
        }
    }

    @Test
    public void test03() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        URL url = URL.valueOf("xxx://localhost:8080/ooo/jjj?xxx=alipay");
        // 激活所有group为online线上支付的扩展类
        // 该方法中的参数二与参数三的关系是 或
        List<Order> online = loader.getActivateExtension(url, "xxx", "online");
        for (Order order : online) {
            System.out.println(order.way());
        }
    }

    @Test
    public void test04() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        URL url = URL.valueOf("xxx://localhost:8080/ooo/jjj?pay=wechat");
        // 激活所有group为online线上支付的扩展类
        // 该方法中的参数二与参数三的关系是 或
        List<Order> online = loader.getActivateExtension(url, "pay", "online");
        for (Order order : online) {
            System.out.println(order.way());
        }
    }

    // activate类属于直接扩展类
    @Test
    public void test05() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        Set<String> extensions = loader.getSupportedExtensions();
        System.out.println(extensions);
    }
}
