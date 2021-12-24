package com.abc.spi;

import org.apache.dubbo.common.extension.Activate;

// order属性用于指定当前类激活的优先级，数字越小优先级越高，默认值为0
@Activate(group = {"online", "offline"}, order = 3)
public class CardOrder implements Order {
    @Override
    public String way() {
        System.out.println("---  使用银联卡支付  ---");
        return "银联卡支付";
    }
}
