package com.lcl.galaxy.design.pattern.singleton;

public class HungrySingleAnimal {
    private static HungrySingleAnimal singleAnimal = new HungrySingleAnimal();

    private HungrySingleAnimal(){

    }

    public static HungrySingleAnimal getSingleAnimal(){
        return singleAnimal;
    }

}
