package com.lcl.galaxy.pattern.factorypattern.factorymethod.facorder;

import com.lcl.galaxy.pattern.factorypattern.factorymethod.facpizza.BJFruitPizza;
import com.lcl.galaxy.pattern.factorypattern.factorymethod.facpizza.BJGreekPizza;
import com.lcl.galaxy.pattern.factorypattern.factorymethod.facpizza.Pizza;

/**
 * @Classname BJOrderFactory
 * @Created 
 * @Description 北京的工厂子类
 */
public class BJOrderFactory extends OrderPizza {
    @Override
    public Pizza createPizza(String orderType) {

        Pizza pizza = null;

        if (orderType.equals("GreekPizza")){

            pizza = new BJGreekPizza();
        } else if (orderType.equals("FruitPizza")){

            pizza = new BJFruitPizza();
        }

        return pizza;
    }
}
