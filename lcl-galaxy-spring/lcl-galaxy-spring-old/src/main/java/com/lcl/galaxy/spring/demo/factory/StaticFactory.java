package com.lcl.galaxy.spring.demo.factory;

import com.lcl.galaxy.spring.demo.UserServiceImpl;
import com.lcl.galaxy.spring.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticFactory {
    public static UserService createUserService(){
        log.info("静态工厂构造");
        return new UserServiceImpl();
    }
}
