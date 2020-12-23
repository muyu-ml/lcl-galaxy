package com.lcl.galaxy.spring.frame.service;

import com.lcl.galaxy.spring.frame.domain.UserDo;

public interface UserService {
    UserDo findUserById(String id) throws Exception;
}
