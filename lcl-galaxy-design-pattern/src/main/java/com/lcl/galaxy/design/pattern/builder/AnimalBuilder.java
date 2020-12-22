package com.lcl.galaxy.design.pattern.builder;

public class AnimalBuilder {

    private Animal animal = new Animal();

    public static AnimalBuilder builder(){
        return new AnimalBuilder();
    }

    public AnimalBuilder eye(String eye){
        animal.setEye(eye);
        return this;
    }

    public AnimalBuilder ears(String ears){
        animal.setEars(ears);
        return this;
    }

    public AnimalBuilder nose(String nose){
        animal.setNose(nose);
        return this;
    }

    public AnimalBuilder hair(String hair){
        animal.setHair(hair);
        return this;
    }

    public Animal build(){
        return animal;
    }

}
