package com.lcl.galaxy.spring.factory;

import com.lcl.galaxy.spring.service.UserService;
import com.lcl.galaxy.spring.service.impl.UserServiceImpl;

public class StaticFactory {
    public static UserService createUserService(){
        return new UserServiceImpl();
    }
}
