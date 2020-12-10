package com.lcl.galaxy.spring.transaction.service;


import com.lcl.galaxy.spring.transaction.domain.OrderInfoDo;
import com.lcl.galaxy.spring.transaction.domain.UserDo;

import java.util.List;

public interface UserService {
     void transactionTest(UserDo userDo, OrderInfoDo orderInfoDo);
}
