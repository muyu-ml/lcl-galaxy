package com.lcl.galaxy.mybatis.demo.dao;

import com.lcl.galaxy.mybatis.common.domain.UserDo;

import java.util.List;

public interface UserDao {

    UserDo findUserById(int id) throws Exception;

    List<UserDo> findUsersByName(String name) throws Exception;
}
