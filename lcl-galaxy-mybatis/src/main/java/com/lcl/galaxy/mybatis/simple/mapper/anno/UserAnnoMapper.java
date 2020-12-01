package com.lcl.galaxy.mybatis.simple.mapper.anno;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAnnoMapper {

    @Select("select * from user where id = #{id}")
    UserDo findUserById(int id) throws Exception;

    @Select("select * from user where username like CONCAT('%',#{name},'%')")
    List<UserDo> findUsersByName(String name) throws Exception;
}
