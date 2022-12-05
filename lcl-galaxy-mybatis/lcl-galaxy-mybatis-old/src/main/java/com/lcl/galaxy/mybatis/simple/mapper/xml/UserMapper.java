package com.lcl.galaxy.mybatis.simple.mapper.xml;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import com.lcl.galaxy.mybatis.simple.common.dto.UserOrderDto;
import com.lcl.galaxy.mybatis.simple.common.dto.UserOrdersDto;
import com.lcl.galaxy.mybatis.simple.common.vo.QueryVo;

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


    UserDo findUserByIdNoCache(int id) throws Exception;

    List<UserDo> findUserList(QueryVo queryVo);

    List<UserDo> findUserList1(QueryVo queryVo);

    List<UserDo> findUserList2(QueryVo queryVo);
}
