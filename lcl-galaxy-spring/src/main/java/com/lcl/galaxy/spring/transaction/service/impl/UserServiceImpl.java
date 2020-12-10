package com.lcl.galaxy.spring.transaction.service.impl;

import com.lcl.galaxy.spring.transaction.dao.OrderInfoDao;
import com.lcl.galaxy.spring.transaction.dao.UserDao;
import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.service.UserService;
import com.lcl.galaxy.spring.transaction.domain.UserDo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Data
public class UserServiceImpl implements UserService {


    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderInfoDao orderInfoDao;


    @Override
    public void transactionTest(UserDo userDo, OrderInfoDo orderInfoDo) {
        log.info("事务处理");
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                log.info("=======================");
                userDao.insert(userDo);
                orderInfoDao.insert(orderInfoDo);
            }
        });
        log.info("事务结束");
    }
}
