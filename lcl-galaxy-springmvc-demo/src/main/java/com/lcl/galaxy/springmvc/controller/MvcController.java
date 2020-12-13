package com.lcl.galaxy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/mvc")
@Controller
public class MvcController {

    private static final String str = "这是一个静态资源";


    @RequestMapping("str")
    public ModelAndView getStr(String name){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("user",str);
        return new ModelAndView("hello","user",str);
    }
}
