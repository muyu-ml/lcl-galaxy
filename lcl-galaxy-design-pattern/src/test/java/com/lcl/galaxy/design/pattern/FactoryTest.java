package com.lcl.galaxy.design.pattern;

import com.lcl.galaxy.design.pattern.factory.Animal;
import com.lcl.galaxy.design.pattern.factory.AnimalFactory;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.IronFactory;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.ProductionFactory;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.desk.Desk;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.shelf.Shelf;
import com.lcl.galaxy.design.pattern.factory.pattern.CatFactory;
import com.lcl.galaxy.design.pattern.factory.pattern.StaticAnimalFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FactoryTest {

    @Test
    public void factoryTest(){
        Animal animal = AnimalFactory.CreateAnimal("cat");
        animal.eat();
    }


    @Test
    public void factoryTest1(){
        StaticAnimalFactory factory = new CatFactory();
        Animal animal = factory.createAnimal();
        animal.eat();
    }


    @Test
    public void factoryTest3(){
        ProductionFactory productionFactory = new IronFactory();
        Desk desk = productionFactory.createDesk();
        desk.printDesk();
        Shelf shelf = productionFactory.createShelf();
        shelf.printShelf();
    }

}
