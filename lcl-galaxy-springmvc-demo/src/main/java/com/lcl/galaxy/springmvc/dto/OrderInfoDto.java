package com.lcl.galaxy.springmvc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfoDto {
    private String name;
    private String orderId;
    private Date createDate;
}
