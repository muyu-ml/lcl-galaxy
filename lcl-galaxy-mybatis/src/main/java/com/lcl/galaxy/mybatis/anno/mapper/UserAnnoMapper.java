package com.lcl.galaxy.mybatis.anno.mapper;

import com.lcl.galaxy.mybatis.common.domain.UserDo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAnnoMapper {

    @Select("select * from user where id = #{id}")
    UserDo findUserById(int id) throws Exception;

    @Select("select * from user where username like CONCAT('%',#{name},'%')")
    List<UserDo> findUsersByName(String name) throws Exception;
}
