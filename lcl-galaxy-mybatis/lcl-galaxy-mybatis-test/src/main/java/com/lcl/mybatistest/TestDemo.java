package com.lcl.mybatistest;

import com.lcl.mybatistest.domain.UserDo;
import com.lcl.mybatistest.mapper.UserDao;
import com.lcl.mybatistest.mapper.UserDaoImpl;
import com.lcl.mybatisnative.sqlsession.MySqlSessionFactorBuilder;
import com.lcl.mybatisnative.sqlsession.MySqlSessionFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDemo {
    public static void main(String[] args) throws Exception {
        MySqlSessionFactory sqlSessionFactory = MySqlSessionFactorBuilder.build("SqlMapConfig.xml");
        UserDao jdbcUserDao = new UserDaoImpl(sqlSessionFactory);
        UserDo user = jdbcUserDao.findUserById(1);
        log.info("user===={}",user);
        System.out.println("user===={}"+user);
    }
}
