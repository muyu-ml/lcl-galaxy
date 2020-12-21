package com.lcl.galaxy.design.pattern.factory;

public class AnimalFactory {
    public static Animal CreateAnimal(String name){
        if ("dog".equals(name)){
            return new Dog();
        }else if("cat".equals(name)){
            return new Cat();
        }
        return null;
    }
}
