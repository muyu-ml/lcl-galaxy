package com.lcl.galaxy.springtest.async.business;

public class DemoServiceImpl implements DemoService{
    @Override
    public String perform() throws InterruptedException {
        System.out.println(String.format("Request Thread：%s", Thread.currentThread().getName()));

        //Thread.sleep(5000);
        return "demo";
    }
}
