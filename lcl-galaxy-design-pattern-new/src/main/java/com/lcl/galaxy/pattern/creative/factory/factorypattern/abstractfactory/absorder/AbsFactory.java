package com.lcl.galaxy.pattern.creative.factory.factorypattern.abstractfactory.absorder;

import com.lcl.galaxy.pattern.creative.factory.factorypattern.abstractfactory.abspizza.Pizza;


public interface AbsFactory {


    public Pizza createPizza(String orderType);

}
