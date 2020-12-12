package com.lcl.galaxy.springmvc.controller;

import com.lcl.galaxy.springmvc.domain.UserDo;
import com.lcl.galaxy.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/ByName")
    @ResponseBody
    public List<UserDo> getUser(String name){
        return userService.getUserByName(name);
    }
}
