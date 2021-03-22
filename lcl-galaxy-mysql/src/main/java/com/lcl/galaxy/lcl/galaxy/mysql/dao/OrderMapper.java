package com.lcl.galaxy.lcl.galaxy.mysql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.galaxy.lcl.galaxy.mysql.domain.OrderInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<OrderInfo> {
}
