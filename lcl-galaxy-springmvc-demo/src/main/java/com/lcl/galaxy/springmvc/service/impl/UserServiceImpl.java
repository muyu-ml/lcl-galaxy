package com.lcl.galaxy.springmvc.service.impl;

import com.lcl.galaxy.springmvc.mapper.UserMapper;
import com.lcl.galaxy.springmvc.domain.UserDo;
import com.lcl.galaxy.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDo> getUserByName(String name) {
        return userMapper.findUsersByName(name);
    }
}
