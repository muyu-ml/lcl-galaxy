package com.lcl.galaxy.lcl.galaxy.mysql.service;

import com.lcl.galaxy.lcl.galaxy.mysql.dao.OrderMapper;
import com.lcl.galaxy.lcl.galaxy.mysql.dao.OrderNewMapper;
import com.lcl.galaxy.lcl.galaxy.mysql.domain.OrderInfo;
import com.lcl.galaxy.lcl.galaxy.mysql.domain.OrderInfoNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderNewMapper orderNewMapper;

    public void save(OrderInfo orderInfo) {
        orderMapper.insert(orderInfo);
    }


    public void save(OrderInfoNew orderInfo) {
        orderNewMapper.insert(orderInfo);
    }

    public List<OrderInfoNew> query(OrderInfoNew build) {
        List<OrderInfoNew> orderInfoNewList = orderNewMapper.findAll();
        log.info("查询结果为==={}",orderInfoNewList);
        return orderInfoNewList;
    }
}
