package com.lcl.galaxy.spring.frame.V2.apis;

import com.lcl.galaxy.spring.frame.V2.domain.UserDo;
import com.lcl.galaxy.spring.frame.V2.service.UserService;
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
