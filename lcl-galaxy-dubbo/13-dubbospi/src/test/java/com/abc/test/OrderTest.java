package com.abc.test;

import com.abc.spi.Order;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

public class OrderTest {

    @Test
    public void test01() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        Order alipay = loader.getExtension("alipay");
        System.out.println(alipay.way());

        Order wechat = loader.getExtension("wechat");
        System.out.println(wechat.way());
        Order xxx = loader.getExtension("xxx");
        System.out.println(xxx.way());
    }

    @Test
    public void test02() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        // Order alipay = loader.getExtension("");
        // Order alipay = loader.getExtension(null);
        Order alipay = loader.getExtension("true");

        System.out.println(alipay.way());

    }

}
