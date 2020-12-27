package com.lcl.galaxy.springmvc.frame.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryUserHandler implements MyHttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("QueryUserHandler");
    }
}
