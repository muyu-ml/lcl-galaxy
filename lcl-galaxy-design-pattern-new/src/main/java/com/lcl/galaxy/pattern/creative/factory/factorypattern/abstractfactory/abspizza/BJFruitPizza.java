package com.lcl.galaxy.pattern.creative.factory.factorypattern.abstractfactory.abspizza;


public class BJFruitPizza extends Pizza {

    @Override
    public void prepare() {

        setName("北京的水果披萨");
        System.out.println("北京的水果披萨正在准备原材料");
    }
}
