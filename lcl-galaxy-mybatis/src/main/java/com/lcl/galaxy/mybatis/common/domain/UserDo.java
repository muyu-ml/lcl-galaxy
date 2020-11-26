package com.lcl.galaxy.mybatis.common.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserDo {
    public UserDo() {
    }

    private long id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;



}
