package com.lcl.galaxy.mybatis.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDo implements Serializable {
    private static long serialId = -6060343040263809614L;
    private long id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;


}
