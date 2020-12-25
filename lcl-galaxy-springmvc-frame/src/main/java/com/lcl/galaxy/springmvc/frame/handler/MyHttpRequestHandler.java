package com.lcl.galaxy.springmvc.frame.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHttpRequestHandler {
    void handleRequest(HttpServletRequest request, HttpServletResponse response);
}
