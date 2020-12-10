package com.lcl.galaxy.spring.transaction.service.impl;

import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.service.UserService;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
public class UserServiceImpl implements UserService {


    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate){
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void transactionTest(UserDo userDo, OrderInfoDo orderInfoDo) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                userMapper.insert(userDo);
                orderInfoMapper.insert(orderInfoDo);
            }
        });
    }
}
