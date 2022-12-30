package com.lcl.galaxy.pattern.creative.factory.factorypattern.abstractfactory.absorder;

import com.lcl.galaxy.pattern.creative.factory.factorypattern.abstractfactory.abspizza.BJFruitPizza;
import com.lcl.galaxy.pattern.creative.factory.factorypattern.abstractfactory.abspizza.BJGreekPizza;
import com.lcl.galaxy.pattern.creative.factory.factorypattern.abstractfactory.abspizza.Pizza;


public class BJFactory implements AbsFactory{

    @Override
    public Pizza createPizza(String orderType) {

        Pizza pizza = null;

        if (orderType.equals("GreekPizza")){

            pizza = new BJGreekPizza();

        }else if (orderType.equals("FruitPizza")){

            pizza = new BJFruitPizza();
        }

        return pizza;
    }
}
