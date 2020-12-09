package com.lcl.galaxy.spring.factory;

import com.lcl.galaxy.spring.service.UserService;
import com.lcl.galaxy.spring.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticFactory {
    public static UserService createUserService(){
        log.info("静态工厂构造");
        return new UserServiceImpl();
    }
}
