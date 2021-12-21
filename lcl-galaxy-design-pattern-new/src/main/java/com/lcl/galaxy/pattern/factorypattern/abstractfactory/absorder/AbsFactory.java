package com.lcl.galaxy.pattern.factorypattern.abstractfactory.absorder;

import com.lcl.galaxy.pattern.factorypattern.abstractfactory.abspizza.Pizza;


public interface AbsFactory {


    public Pizza createPizza(String orderType);

}
