package com.lcl.galaxy.pattern.factorypattern.abstractfactory.absorder;

import com.lcl.galaxy.pattern.factorypattern.abstractfactory.abspizza.Pizza;
import com.lcl.galaxy.pattern.factorypattern.abstractfactory.abspizza.SHFruitPizza;
import com.lcl.galaxy.pattern.factorypattern.abstractfactory.abspizza.SHGreekPizza;


public class SHFactory implements AbsFactory{

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;

        if (orderType.equals("GreekPizza")){

            pizza = new SHGreekPizza();

        }else if (orderType.equals("FruitPizza")){

            pizza = new SHFruitPizza();
        }

        return pizza;
    }
}
