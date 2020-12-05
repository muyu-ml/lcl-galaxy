package com.lcl.galaxy.mybatis.common.jdbc;

import com.lcl.galaxy.mybatis.common.dao.JdbcUserDao;
import com.lcl.galaxy.mybatis.common.dao.impl.JdbcUserDaoImpl;
import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcDemo {
    public static void main(String[] args) throws Exception {
        JdbcUserDao jdbcUserDao = new JdbcUserDaoImpl();
        UserDo user = jdbcUserDao.findUserById(2);
        log.info("user===={}", user);
    }
}
