package com.lcl.galaxy.pattern.creative.factory.factorypattern.factorymethod.facpizza;


public class BJGreekPizza extends Pizza {

    @Override
    public void prepare() {

        setName("北京的希腊披萨");
        System.out.println("北京的希腊披萨正在准备原材料");
    }
}
