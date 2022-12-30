package com.lcl.galaxy.pattern.creative.factory.factorypattern.simplefactory.order;

/**
 * @Classname PizzaStore
 * @Created 
 * @Description 客户端
 */
public class PizzaStore {

    public static void main(String[] args) {

//        new OrderPizza();

//        使用简单工厂模式
        new OrderPizza(new SimpleFactory());
    }
}
