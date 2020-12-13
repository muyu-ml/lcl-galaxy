package com.lcl.galaxy.springmvc.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        MyException myException = null;
        if(e instanceof MyException){
            myException = (MyException) e;
        }else {
            myException = new MyException("未知错误");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", myException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
