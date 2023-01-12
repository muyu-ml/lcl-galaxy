package com.lcl.galaxy.springtest.async.main;

import com.lcl.galaxy.springtest.async.api.AsyncProxy;
import com.lcl.galaxy.springtest.async.business.DemoService;
import com.lcl.galaxy.springtest.async.business.DemoServiceImpl;
import com.lcl.galaxy.springtest.async.core.DynamicProxy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("Main Thread：%s", Thread.currentThread().getName()));

        DemoService demoService = new DemoServiceImpl();

        AsyncProxy dynamicProxy = new DynamicProxy(demoService);
        DemoService target = (DemoService)dynamicProxy.getProxy();
        String perform1 = target.perform();
        System.out.println(perform1);
    }
}




