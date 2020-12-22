package com.lcl.galaxy.design.pattern.singleton;

import java.io.Serializable;

public class DoubleCheckSingleAnimal implements Serializable {

    private String name;

    private static volatile DoubleCheckSingleAnimal singleAnimal = null;

    private DoubleCheckSingleAnimal() throws Exception {
    }


    public static DoubleCheckSingleAnimal getSingleAnima () throws Exception {
        if(singleAnimal == null){
            synchronized (DoubleCheckSingleAnimal.class){
                if(singleAnimal == null){
                    singleAnimal = new DoubleCheckSingleAnimal();
                }
            }
        }
        return singleAnimal;
    }

    private Object readResolve(){
        return singleAnimal;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
