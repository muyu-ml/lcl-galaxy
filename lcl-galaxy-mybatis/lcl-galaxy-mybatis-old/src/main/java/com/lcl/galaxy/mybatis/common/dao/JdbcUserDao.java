package com.lcl.galaxy.mybatis.common.dao;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;

public interface JdbcUserDao {

    UserDo findUserById(int id) throws Exception;

}
