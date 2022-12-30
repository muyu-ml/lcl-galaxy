package com.lcl.galaxy.pattern.other.builderprinciple.builderdemo;

/**
 * @Classname CommonHouse
 * @Created 
 * @Description 具体建造者
 */
public class CommonHouse extends HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("普通房屋正在筑基中。。。。");
    }

    @Override
    public void buildWalls() {

        System.out.println("普通房屋正在砌墙中。。。。");

    }

    @Override
    public void roofed() {

        System.out.println("普通房屋正在封顶中。。。。");

    }
}
