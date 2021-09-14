package com.lcl.galaxy.netty.dubborpc.customer;

import com.lcl.galaxy.netty.dubborpc.api.CityService;
import com.lcl.galaxy.netty.dubborpc.api.Constant;
import com.lcl.galaxy.netty.dubborpc.netty.NettyClient;

public class ClientBootstrap {
    //这里定义协议头

    public static void main(String[] args) throws Exception {

        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        CityService service = (CityService) customer.getBean(CityService.class, Constant.protocolName);

        for(int i=0;i<10;i++){

            //通过代理对象调用服务提供者的方法(服务)
            String res = service.getName("hello bejing-"+i);

            System.out.println("调用的结果 res= " + res);
            Thread.sleep(5 * 1000);
        }
    }
}
