package com.lcl.galaxy.spring.aop;

public interface UserService {
    public void insert();

    public void insert(String id);

    public void insert(String id, String name);

    public void userInsert();

    public String around(String s);
}
