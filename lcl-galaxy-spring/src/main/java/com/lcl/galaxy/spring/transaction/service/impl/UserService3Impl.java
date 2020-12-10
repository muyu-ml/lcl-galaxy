package com.lcl.galaxy.spring.transaction.service.impl;

import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import com.lcl.galaxy.spring.transaction.service.UserService2;
import com.lcl.galaxy.spring.transaction.service.UserService3;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Data
@Service
public class UserService3Impl implements UserService3 {


    @Transactional
    @Override
    public void saveInfo(UserDo userDo, OrderInfoDo orderInfoDo) {
        log.info("==================");
    }
}
