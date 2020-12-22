package com.lcl.galaxy.design.pattern.singleton;

import java.io.Serializable;

public class DoubleCheckSingleAnimal implements Serializable {

    private String name;

    private static volatile DoubleCheckSingleAnimal singleAnimal = null;

    private DoubleCheckSingleAnimal(){

    }


    public static DoubleCheckSingleAnimal getSingleAnima (){
        if(singleAnimal == null){
            synchronized (DoubleCheckSingleAnimal.class){
                if(singleAnimal == null){
                    singleAnimal = new DoubleCheckSingleAnimal();
                }
            }
        }
        return singleAnimal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
