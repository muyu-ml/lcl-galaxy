package com.lcl.galaxy.spring.transaction.dao.impl;

import com.lcl.galaxy.spring.transaction.dao.OrderInfoDao;
import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderInfoDaoImpl implements OrderInfoDao {


    @Override
    public void insert(OrderInfoDo orderInfoDo) {
        log.info("插入订单={}", orderInfoDo);
    }
}
