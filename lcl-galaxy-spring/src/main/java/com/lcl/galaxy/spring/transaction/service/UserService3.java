package com.lcl.galaxy.spring.transaction.service;


import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.domain.UserDo;

public interface UserService3 {
     void saveInfo(UserDo userDo, OrderInfoDo orderInfoDo);
}
