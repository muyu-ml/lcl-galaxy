package com.lcl.galaxy.mybatis;

import com.lcl.galaxy.mybatis.common.domain.UserDo;
import com.lcl.galaxy.mybatis.demo.dao.UserDao;
import com.lcl.galaxy.mybatis.demo.dao.impl.UserDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

@Slf4j
public class DemoTest {

    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws Exception {
        SqlSessionFactoryBuilder sessionFactoryBuilder = new
                SqlSessionFactoryBuilder();
        InputStream inputStream =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
    }
    @Test
    public void testFindUserById() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        UserDo user = userDao.findUserById(2);
        log.info("userDo====[{}]",user);
    }
    @Test
    public void testFindUsersByName() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        List<UserDo> userDoList = userDao.findUsersByName("lcl");
        log.info("userDoList====[{}]",userDoList);
    }
}
