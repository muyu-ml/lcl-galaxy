package com.lcl.galaxy.pattern.factorypattern.simplefactory.pizza;

/**
 * @Classname CheesePizza
 * @Created 
 * @Description 芝士披萨
 */
public class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备芝士披萨的原材料");
    }
}
