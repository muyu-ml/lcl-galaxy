package com.lcl.galaxy.spring.frame.service;

import com.lcl.galaxy.spring.frame.dao.UserDao;
import com.lcl.galaxy.spring.frame.domain.UserDo;
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
