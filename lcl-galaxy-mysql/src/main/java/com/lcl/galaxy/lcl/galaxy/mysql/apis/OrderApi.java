package com.lcl.galaxy.lcl.galaxy.mysql.apis;

import com.lcl.galaxy.lcl.galaxy.mysql.domain.OrderInfo;
import com.lcl.galaxy.lcl.galaxy.mysql.domain.OrderInfoNew;
import com.lcl.galaxy.lcl.galaxy.mysql.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/save")
    public String save(long orderId, long venderId){
        orderService.save(OrderInfo.builder().orderId(orderId).venderId(venderId).build());
        return "OK";
    }


    @RequestMapping("/saveNew")
    public String saveNew(long orderId, long venderId){
        orderService.save(OrderInfoNew.builder().orderId(orderId).venderId(venderId).build());
        return "OK";
    }


    @RequestMapping("/query")
    public String query(long orderId, long venderId){
        orderService.query(OrderInfoNew.builder().orderId(orderId).venderId(venderId).build());
        return "OK";
    }

}
