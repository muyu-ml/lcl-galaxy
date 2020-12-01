package com.lcl.galaxy.mybatis.frame.dao;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;

import java.util.List;

public interface JdbcUserDao {

    UserDo findUserById(int id) throws Exception;

}
