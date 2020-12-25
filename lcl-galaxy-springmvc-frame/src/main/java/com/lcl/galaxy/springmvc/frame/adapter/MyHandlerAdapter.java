package com.lcl.galaxy.springmvc.frame.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
