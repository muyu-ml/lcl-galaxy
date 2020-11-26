package com.lcl.galaxy.mybatis;

import com.lcl.galaxy.mybatis.anno.mapper.UserAnnoMapper;
import com.lcl.galaxy.mybatis.common.domain.UserDo;
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
public class AnnoTest {

    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws Exception {
        SqlSessionFactoryBuilder sessionFactoryBuilder = new
                SqlSessionFactoryBuilder();
        InputStream inputStream =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
        //sqlSessionFactory.getConfiguration().addMapper(UserAnnoMapper.class);
    }
    @Test
    public void testFindUserById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserAnnoMapper userMapper = sqlSession.getMapper(UserAnnoMapper.class);
            UserDo user = userMapper.findUserById(2);
            log.info("userDo====[{}]",user);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testFindUsersByName() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserAnnoMapper userMapper = sqlSession.getMapper(UserAnnoMapper.class);
            List<UserDo> userDoList = userMapper.findUsersByName("lcl");
            log.info("userDoList====[{}]",userDoList);
        }finally {
            sqlSession.close();
        }

    }
}
