package com.lcl.galaxy.spring.frame.V2.dao;

import com.lcl.galaxy.spring.frame.V2.domain.UserDo;

public interface UserDao {
    UserDo findUserById(String id) throws Exception;
}
