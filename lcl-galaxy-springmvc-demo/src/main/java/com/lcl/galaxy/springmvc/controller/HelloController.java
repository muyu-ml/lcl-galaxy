package com.lcl.galaxy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping("/view")
    public String getStrView(){
        return "hello";
    }


    @RequestMapping("/data")
    @ResponseBody
    public String getStr(){
        return "hello";
    }

}
