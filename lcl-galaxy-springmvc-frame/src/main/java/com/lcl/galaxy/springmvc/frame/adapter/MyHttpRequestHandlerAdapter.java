package com.lcl.galaxy.springmvc.frame.adapter;

import com.lcl.galaxy.springmvc.frame.handler.MyHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHttpRequestHandlerAdapter implements MyHandlerAdapter {
    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MyHttpRequestHandler httpRequestHandler = (MyHttpRequestHandler) handler;
        httpRequestHandler.handleRequest(request, response);
    }
}
