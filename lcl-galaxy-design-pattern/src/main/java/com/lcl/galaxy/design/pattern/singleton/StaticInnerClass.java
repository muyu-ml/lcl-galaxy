package com.lcl.galaxy.design.pattern.singleton;

public class StaticInnerClass {

    private static class SingletonHandler{
        private static final StaticInnerClass STATIC_INNER_CLASS = new StaticInnerClass();
    }

    private StaticInnerClass(){}

    public StaticInnerClass getStaticInnerClass() throws Exception {
        if(SingletonHandler.STATIC_INNER_CLASS != null){
            throw new Exception("单例被破坏");
        }
        return SingletonHandler.STATIC_INNER_CLASS;
    }
}
