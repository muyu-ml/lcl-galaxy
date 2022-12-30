package com.lcl.galaxy.pattern.other.builderprinciple.builderdemo;

/**
 * @Classname HighBuilding
 * @Created 
 * @Description I walk very slowly, but I never walk backwards
 */
public class HighBuilding extends HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("高楼正在筑基中。。。。");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼正在砌墙中。。。。");

    }

    @Override
    public void roofed() {
        System.out.println("高楼正在封顶中。。。。");

    }
}
