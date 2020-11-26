package com.lcl.galaxy.mybatis.xml.mapper;

import com.lcl.galaxy.mybatis.common.domain.UserDo;
import com.lcl.galaxy.mybatis.common.dto.UserOrderDto;
import com.lcl.galaxy.mybatis.common.dto.UserOrdersDto;

import java.util.List;

public interface UserMapper {
    UserDo findUserById(int id) throws Exception;

    List<UserDo> findUsersByName(String name) throws Exception;

    int insertUser(UserDo userDo);

    int getCount(String username);

    UserDo selectAsAliase(int id) throws Exception;

    List<UserOrderDto> selectUserOrderDtoByUserName(String name) throws Exception;

    List<UserOrderDto> selectUserOrderDtoByUserName2(String name) throws Exception;


    List<UserOrdersDto> selectUserOrdersDtoByUserName(String name) throws Exception;
}
