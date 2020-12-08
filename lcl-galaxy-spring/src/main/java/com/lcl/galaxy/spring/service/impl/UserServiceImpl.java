package com.lcl.galaxy.spring.service.impl;

import com.lcl.galaxy.spring.domain.UserDo;
import com.lcl.galaxy.spring.service.UserService;

public class UserServiceImpl implements UserService {

    private String id;
    private String name;

    @Override
    public UserDo getUserById() {
        return UserDo.builder().id(this.id).name(this.name).build();
    }

    public UserServiceImpl(){
        this.id = "initId";
        this.name = "initName";
    }

    public UserServiceImpl(String id, String name){
        this.id = id;
        this.name = name;
    }

}
