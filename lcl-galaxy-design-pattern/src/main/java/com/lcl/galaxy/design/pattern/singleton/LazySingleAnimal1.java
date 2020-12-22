package com.lcl.galaxy.design.pattern.singleton;

public class LazySingleAnimal1 {

    private static volatile LazySingleAnimal1 singleAnimal = null;

    private LazySingleAnimal1(){

    }

    /**
     * 不安全
     * @return
     */
    public static LazySingleAnimal1 getSingleAnimal(){
        if(singleAnimal == null){
            singleAnimal = new LazySingleAnimal1();
        }
        return singleAnimal;
    }

    /**
     * 效率低
     * @return
     */
    public static synchronized LazySingleAnimal1 getSingleAnima2(){
        if(singleAnimal == null){
            singleAnimal = new LazySingleAnimal1();
        }
        return singleAnimal;
    }


    /**
     * 存在问题：
     * @return
     */
    public static LazySingleAnimal1 getSingleAnima3 (){
        if(singleAnimal == null){
            synchronized (LazySingleAnimal1.class){
                singleAnimal = new LazySingleAnimal1();
            }
        }
        return singleAnimal;
    }


    public static LazySingleAnimal1 getSingleAnima4 (){
        if(singleAnimal == null){
            synchronized (LazySingleAnimal1.class){
                if(singleAnimal == null){
                    singleAnimal = new LazySingleAnimal1();
                }
            }
        }
        return singleAnimal;
    }
}
