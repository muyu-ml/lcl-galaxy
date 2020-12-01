package com.lcl.galaxy.mybatis.simple.common.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderInfoDo {
    private long id;
    private String name;
    private String orderId;
    private BigDecimal payMoney;
    private Date orderCreateTime;

}
