package com.lcl.galaxy.pattern.other.builderprinciple.builderdemo;

/**
 * @Classname HouseBuilder
 * @Created 
 * @Description 抽象建造者
        */
public abstract class HouseBuilder {

    //组合产品角色
    private House house;

    //构建房屋创建流程
    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void roofed();

    public House buildHouse(){

        return house;
    }



}
