package com.lcl.galaxy.spring.demo.service.impl;

import com.lcl.galaxy.spring.demo.UserService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl2 implements UserService2 {

    public UserServiceImpl2(){
      log.info("无参构造被调用");
    }
}
