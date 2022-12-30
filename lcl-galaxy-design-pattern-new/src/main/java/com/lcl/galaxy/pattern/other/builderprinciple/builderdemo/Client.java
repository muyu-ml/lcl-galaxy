package com.lcl.galaxy.pattern.other.builderprinciple.builderdemo;

/**
 * @Classname Client
 * @Created 
 * @Description 客户端
 */
public class Client {

    public static void main(String[] args) {

        //?
        HighBuilding highBuilding = new HighBuilding();

        //创建房屋指挥者
        House house = new HouseDirector(highBuilding).buildHouse();

        System.out.println(house);
    }
}
