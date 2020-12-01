package com.lcl.galaxy.mybatis.frame.dao;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;

public interface FrameUserDao {

    UserDo findUserById(int id) throws Exception;
}
