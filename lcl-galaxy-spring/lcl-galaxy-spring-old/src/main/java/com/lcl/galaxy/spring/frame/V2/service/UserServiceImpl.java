package com.lcl.galaxy.spring.frame.V2.service;

import com.lcl.galaxy.spring.frame.V2.dao.UserDao;
import com.lcl.galaxy.spring.frame.V2.domain.UserDo;
import lombok.Data;

@Data
public class UserServiceImpl implements UserService {


    private UserDao userDao;

    @Override
    public UserDo findUserById(String id) throws Exception{
        return userDao.findUserById(id);
    }

    public UserServiceImpl(){

    }
}
