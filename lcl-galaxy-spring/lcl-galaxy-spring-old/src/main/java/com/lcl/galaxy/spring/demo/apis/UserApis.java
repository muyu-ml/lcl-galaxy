package com.lcl.galaxy.spring.demo.apis;

import com.lcl.galaxy.spring.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserApis {

    @Autowired
    private UserService userService;

    public String getUser(){
        log.info("user======{}",userService.getUserById());
        return null;
    }
}
