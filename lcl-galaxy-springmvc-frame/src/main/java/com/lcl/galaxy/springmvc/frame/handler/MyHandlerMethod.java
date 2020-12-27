package com.lcl.galaxy.springmvc.frame.handler;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class MyHandlerMethod {
    private Object controller;
    private Method method;

    public MyHandlerMethod(Object controller, Method method){
        super();
        this.controller = controller;
        this.method = method;
    }
}
