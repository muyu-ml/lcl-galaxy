package com.lcl.galaxy.springmvc.frame.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface MyHandlerAdapter {
    void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException;

    boolean support(Object handler);
}
