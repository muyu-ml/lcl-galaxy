package com.lcl.galaxy.mybatis.common.dao;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;

public interface FrameUserDao {

    UserDo findUser(UserDo userDo) throws Exception;
}
