package com.lcl.galaxy.pattern.factorypattern.simplefactory.pizza;

/**
 * @Classname GreekPizza
 * @Created 
 * @Description 希腊披萨
 */
public class GreekPizza extends Pizza {

    @Override
    public void prepare() {

        System.out.println("准备希腊披萨的原材料");

    }
}
