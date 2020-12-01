package com.lcl.galaxy.mybatis;

import com.lcl.galaxy.mybatis.frame.dao.JdbcUserDao;
import com.lcl.galaxy.mybatis.frame.dao.impl.JdbcUserDaoImpl;
import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import com.lcl.galaxy.mybatis.simple.mapper.anno.UserAnnoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

@Slf4j
public class FrameTest {


    @Test
    public void testJdbcFindUserById() throws Exception {
        JdbcUserDao jdbcUserDao = new JdbcUserDaoImpl();
        UserDo userDo = jdbcUserDao.findUserById(2);
        log.info("userDo ========== 【{}】", userDo);
    }
    @Test
    public void testFindUsersByName() throws Exception {

    }
}
