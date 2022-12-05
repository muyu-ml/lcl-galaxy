package com.lcl.galaxy.mybatis.simple.common.dto;

import com.lcl.galaxy.mybatis.simple.common.domain.OrderInfoDo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserOrdersDto {
    private long id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
    private List<OrderInfoDo> orderInfoDoList;

}
