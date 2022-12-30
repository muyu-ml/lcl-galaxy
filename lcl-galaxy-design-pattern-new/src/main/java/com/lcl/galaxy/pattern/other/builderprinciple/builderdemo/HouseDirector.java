package com.lcl.galaxy.pattern.other.builderprinciple.builderdemo;

/**
 * @Classname HouseDirector
 * @Created 
 * @Description 指挥者
 */
public class HouseDirector {

    //聚合抽象建造者
    public HouseBuilder houseBuilder;

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public House buildHouse(){

        houseBuilder.buildBasic();

        houseBuilder.roofed();

        houseBuilder.buildWalls();

        return houseBuilder.buildHouse();
    }
}
