package com.lcl.galaxy.springmvc.frame.servlet;

import com.lcl.galaxy.springmvc.frame.adapter.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyDispatcherServlet extends MyAbstructServlet {
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        //查找处理器
        Object handler = getHandler(request);
        //执行处理器方法
        MyHandlerAdapter mha = getHandlerAdapter(handler);
        //执行并响应结果
        mha.handleRequest(handler, request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        return null;
    }

    private Object getHandler(HttpServletRequest request) {
        return null;
    }
}
