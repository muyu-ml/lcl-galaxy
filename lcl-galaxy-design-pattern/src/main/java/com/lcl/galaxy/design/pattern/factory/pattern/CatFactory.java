package com.lcl.galaxy.design.pattern.factory.pattern;

import com.lcl.galaxy.design.pattern.factory.Animal;
import com.lcl.galaxy.design.pattern.factory.Cat;

public class CatFactory extends StaticAnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}
