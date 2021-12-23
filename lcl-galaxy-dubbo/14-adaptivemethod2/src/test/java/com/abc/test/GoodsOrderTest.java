package com.abc.test;

import com.abc.spi.GoodsOrder;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

public class GoodsOrderTest {

    @Test
    public void test01() {
        ExtensionLoader<GoodsOrder> loader = ExtensionLoader.getExtensionLoader(GoodsOrder.class);
        GoodsOrder goodsOrder = loader.getAdaptiveExtension();
        URL url = URL.valueOf("xxx://localhost:8080/ooo/jjj?goods.order=alipay");
        System.out.println(goodsOrder.pay(url));
    }
}
