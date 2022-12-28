package com.lcl.galaxy.spring.transaction.dao.impl;

import com.lcl.galaxy.spring.transaction.dao.UserDao2;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDaoI2mpl implements UserDao2 {


    @Override
    public void insert(UserDo userDo) {
        log.info("插入user={}", userDo);
    }
}
