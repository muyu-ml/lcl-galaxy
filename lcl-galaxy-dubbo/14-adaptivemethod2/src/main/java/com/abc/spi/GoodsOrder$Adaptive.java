package com.abc.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

public class GoodsOrder$Adaptive implements com.abc.spi.GoodsOrder {
    public java.lang.String pay(org.apache.dubbo.common.URL arg0) {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        org.apache.dubbo.common.URL url = arg0;
        String extName = url.getParameter("goods.order", "wechat");
        if (extName == null)
            throw new IllegalStateException("Failed to get extension (com.abc.spi.GoodsOrder) name from url (" + url.toString() + ") use keys([goods.order])");
        com.abc.spi.GoodsOrder extension = (com.abc.spi.GoodsOrder) ExtensionLoader.getExtensionLoader(com.abc.spi.GoodsOrder.class).getExtension(extName);
        return extension.pay(arg0);
    }

    public java.lang.String way() {
        throw new UnsupportedOperationException("The method public abstract java.lang.String com.abc.spi.GoodsOrder.way() of interface com.abc.spi.GoodsOrder is not adaptive method!");
    }
}