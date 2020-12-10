package com.lcl.galaxy.spring.aop.service;

import com.lcl.galaxy.spring.aop.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void insert() {
        log.info("=========================insert======================");
    }

    @Override
    public void insert(String id) {
        log.info("=========================insert111======================");
    }

    @Override
    public void insert(String id, String name) {
        log.info("=========================insert2222======================");
    }

    @Override
    public void userInsert() {
        log.info("=========================userInsert======================");
    }

    @Override
    public String around(String s) {
        int t = 1/0;
        return "s==[{}]" + s;
    }



}
