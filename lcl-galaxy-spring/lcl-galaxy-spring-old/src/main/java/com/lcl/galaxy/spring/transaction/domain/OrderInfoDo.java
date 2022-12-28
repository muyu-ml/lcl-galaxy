package com.lcl.galaxy.spring.transaction.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class OrderInfoDo {
    private long id;
    private String name;
    private String orderId;
    private BigDecimal payMoney;
    private Date orderCreateTime;

}
