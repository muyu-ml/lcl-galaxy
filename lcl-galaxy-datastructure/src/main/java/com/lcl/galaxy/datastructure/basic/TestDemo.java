package com.lcl.galaxy.datastructure.basic;

public class TestDemo {

    private static ThreadLocal<Integer> localInt = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        log("start main...");
        new Thread(() -> {
            localInt.set(8);
            log("run task...");
        }).start();
        new Thread(() -> {
            localInt.set(9);
            log("print...");
        }).start();
        log("end main.");
    }


    static void log(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + localInt.get());
    }
}
