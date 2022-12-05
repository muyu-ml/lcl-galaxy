package com.lcl.mybatistest.mapper;


import com.lcl.mybatistest.domain.UserDo;

import java.util.List;

public interface UserDao {
    UserDo findUserById(int id) throws Exception;
    List<UserDo> findUserByUserName(String username) throws Exception;
}
