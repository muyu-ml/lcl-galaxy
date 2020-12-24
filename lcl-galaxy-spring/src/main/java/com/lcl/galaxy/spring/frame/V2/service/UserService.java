package com.lcl.galaxy.spring.frame.V2.service;

import com.lcl.galaxy.spring.frame.V2.domain.UserDo;

public interface UserService {
    UserDo findUserById(String id) throws Exception;
}
