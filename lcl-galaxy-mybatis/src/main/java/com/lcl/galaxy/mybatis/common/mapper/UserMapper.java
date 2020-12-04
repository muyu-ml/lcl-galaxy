package com.lcl.galaxy.mybatis.common.mapper;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;

public interface UserMapper {
    UserDo findUserByUserName(String username) throws Exception;
}
