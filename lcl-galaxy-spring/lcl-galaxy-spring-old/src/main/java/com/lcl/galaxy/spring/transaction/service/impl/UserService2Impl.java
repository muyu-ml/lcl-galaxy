package com.lcl.galaxy.spring.transaction.service.impl;

import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import com.lcl.galaxy.spring.transaction.service.UserService2;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class UserService2Impl implements UserService2 {


    @Override
    public void saveInfo(UserDo userDo, OrderInfoDo orderInfoDo) {
        log.info("==================");
    }
}
