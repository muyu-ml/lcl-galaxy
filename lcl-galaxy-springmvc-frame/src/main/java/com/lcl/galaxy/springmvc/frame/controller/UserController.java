package com.lcl.galaxy.springmvc.frame.controller;

import com.lcl.galaxy.springmvc.frame.annotation.MyController;
import com.lcl.galaxy.springmvc.frame.annotation.MyRequestMapping;
import com.lcl.galaxy.springmvc.frame.annotation.MyResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@MyController
@MyRequestMapping("/user")
public class UserController implements Serializable {
    private static long serialUUid = 1L;

    @MyRequestMapping("/get")
    @MyResponseBody
    public Map<String, Object> getUser(Integer id, String name){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        return map;
    }


    @MyRequestMapping("/save")
    @MyResponseBody
    public String saveUser(){
        return "OK";
    }
}
