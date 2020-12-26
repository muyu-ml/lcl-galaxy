package com.lcl.galaxy.springmvc.frame.adapter;

import com.lcl.galaxy.springmvc.frame.handler.MySimpleControllerHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySimpleContrpllerHandlerAdapter implements MyHandlerAdapter {
    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) {
        MySimpleControllerHandler simpleControllerHandler = (MySimpleControllerHandler) handler;
        simpleControllerHandler.handlerRequest(request, response);
    }

    @Override
    public boolean support(Object handler) {
        return handler instanceof MySimpleContrpllerHandlerAdapter;
    }
}
