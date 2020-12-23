package com.lcl.galaxy.spring.frame.apis;

import com.lcl.galaxy.spring.frame.domain.UserDo;
import com.lcl.galaxy.spring.frame.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApis {

    private UserService userService;

    public UserApis(UserService userService){
        this.userService = userService;
    }

    public void findUserById(String id) throws Exception{
        UserDo userDo = userService.findUserById(id);
        log.info("=================={}=================", userDo);
    }
}
