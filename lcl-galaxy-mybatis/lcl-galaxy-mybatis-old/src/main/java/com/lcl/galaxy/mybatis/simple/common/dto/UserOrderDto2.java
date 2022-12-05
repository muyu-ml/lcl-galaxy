package com.lcl.galaxy.mybatis.simple.common.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserOrderDto2{
    private long id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;


    private String name;
    private String orderId;
    private BigDecimal payMoney;
    private Date orderCreateTime;

}
