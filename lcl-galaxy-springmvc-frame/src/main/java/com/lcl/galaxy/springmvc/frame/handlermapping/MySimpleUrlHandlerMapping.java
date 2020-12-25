package com.lcl.galaxy.springmvc.frame.handlermapping;

import com.lcl.galaxy.springmvc.frame.handler.QueryUserHandler;
import com.lcl.galaxy.springmvc.frame.handler.SaveUserHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class MySimpleUrlHandlerMapping implements MyHandlerMapping {

    private Map<String, Object> urlHanlderMap = new HashMap<>();


    public void init(){
        urlHanlderMap.put("/query", new QueryUserHandler());
        urlHanlderMap.put("/save", new SaveUserHandler());
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        return urlHanlderMap.get(request.getRequestURI());
    }
}
