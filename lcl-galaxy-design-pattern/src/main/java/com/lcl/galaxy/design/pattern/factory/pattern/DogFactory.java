package com.lcl.galaxy.design.pattern.factory.pattern;

import com.lcl.galaxy.design.pattern.factory.Animal;
import com.lcl.galaxy.design.pattern.factory.Dog;

public class DogFactory extends StaticAnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}
