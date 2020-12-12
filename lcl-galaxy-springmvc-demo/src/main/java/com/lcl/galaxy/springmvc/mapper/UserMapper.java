package com.lcl.galaxy.springmvc.mapper;


import com.lcl.galaxy.springmvc.domain.UserDo;

import java.util.List;

public interface UserMapper {

    List<UserDo> findUsersByName(String name);


}
