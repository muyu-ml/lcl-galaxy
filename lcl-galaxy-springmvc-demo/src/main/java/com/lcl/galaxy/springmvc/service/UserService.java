package com.lcl.galaxy.springmvc.service;

import com.lcl.galaxy.springmvc.domain.UserDo;

import java.util.List;

public interface UserService {
    List<UserDo> getUserByName(String name);
}
