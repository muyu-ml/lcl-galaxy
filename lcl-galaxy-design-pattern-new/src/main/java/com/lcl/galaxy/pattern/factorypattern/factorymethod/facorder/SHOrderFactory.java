package com.lcl.galaxy.pattern.factorypattern.factorymethod.facorder;

import com.lcl.galaxy.pattern.factorypattern.factorymethod.facpizza.Pizza;
import com.lcl.galaxy.pattern.factorypattern.factorymethod.facpizza.SHFruitPizza;
import com.lcl.galaxy.pattern.factorypattern.factorymethod.facpizza.SHGreekPizza;

/**
 * @Classname SHOrderFactory
 * @Created 
 * @Description 上海的工厂子类
 */
public class SHOrderFactory extends OrderPizza {
    @Override
    public Pizza createPizza(String orderType) {

        Pizza pizza = null;

        if (orderType.equals("GreekPizza")){

            pizza = new SHGreekPizza();
        } else if (orderType.equals("FruitPizza")){

            pizza = new SHFruitPizza();
        }

        return pizza;
    }
}
