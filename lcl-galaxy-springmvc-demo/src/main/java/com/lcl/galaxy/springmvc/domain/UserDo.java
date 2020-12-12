package com.lcl.galaxy.springmvc.domain;


import java.io.Serializable;
import java.util.Date;

public class UserDo implements Serializable {
    private static long serialId = -6060343040263809614L;
    private long id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    public static long getSerialId() {
        return serialId;
    }

    public static void setSerialId(long serialId) {
        UserDo.serialId = serialId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }
}
