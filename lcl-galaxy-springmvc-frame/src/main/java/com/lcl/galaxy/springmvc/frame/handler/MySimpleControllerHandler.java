package com.lcl.galaxy.springmvc.frame.handler;

import com.lcl.galaxy.springmvc.frame.model.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MySimpleControllerHandler {
    MyModelAndView handlerRequest(HttpServletRequest request, HttpServletResponse response);
}
