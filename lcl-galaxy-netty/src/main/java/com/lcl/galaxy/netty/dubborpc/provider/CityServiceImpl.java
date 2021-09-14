package com.lcl.galaxy.netty.dubborpc.provider;

import com.lcl.galaxy.netty.dubborpc.api.CityService;

public class CityServiceImpl implements CityService {

    private  int count = 0;
    @Override
    public String getName(String mes) {
        System.out.println("收到客户端消息=" + mes);
        //根据mes 返回不同的结果
        if (mes != null) {
            return "你好客户端, 我已经收到你的消息 [" + mes + "] 第" + (++count) + " 次";
        } else {
            return "你好客户端, 我已经收到你的消息 ";
        }
    }
}
