package com.lcl.galaxy.spring.frame.dao;

import com.lcl.galaxy.spring.frame.domain.UserDo;

public interface UserDao {
    UserDo findUserById(String id) throws Exception;
}
