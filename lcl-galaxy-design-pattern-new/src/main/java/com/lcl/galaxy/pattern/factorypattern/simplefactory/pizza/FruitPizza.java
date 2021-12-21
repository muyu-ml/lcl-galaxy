package com.lcl.galaxy.pattern.factorypattern.simplefactory.pizza;

/**
 * @Classname FruitPizza
 * @Created 
 * @Description 水果披萨基类
 */
public class FruitPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("准备水果披萨的原材料");
    }
}
