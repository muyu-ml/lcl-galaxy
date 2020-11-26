package com.lcl.galaxy.mybatis.common.dto;

import com.lcl.galaxy.mybatis.common.domain.OrderInfoDo;
import com.lcl.galaxy.mybatis.common.domain.UserDo;
import lombok.Data;
import java.util.Date;

@Data
public class UserOrderDto extends UserDo {
    private long id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
    private OrderInfoDo orderInfoDo;

}
