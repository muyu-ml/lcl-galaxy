package com.lcl.galaxy.spring.transaction.service.impl;

import com.lcl.galaxy.spring.transaction.dao.OrderInfoDao;
import com.lcl.galaxy.spring.transaction.dao.UserDao;
import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import com.lcl.galaxy.spring.transaction.service.UserService;
import com.lcl.galaxy.spring.transaction.service.UserService2;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Data
public class UserService2Impl implements UserService2 {


    @Override
    public void saveInfo(UserDo userDo, OrderInfoDo orderInfoDo) {
        log.info("==================");
    }
}
