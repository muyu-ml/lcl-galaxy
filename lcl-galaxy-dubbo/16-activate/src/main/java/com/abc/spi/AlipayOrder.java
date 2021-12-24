package com.abc.spi;

import org.apache.dubbo.common.extension.Activate;

// 激活标签是group属性与value属性与的结果
@Activate(group = "online", value = "alipay")
//@Activate(group = "online")
public class AlipayOrder implements Order {
    @Override
    public String way() {
        System.out.println("---  使用支付宝支付  ---");
        return "支付宝支付";
    }
}
