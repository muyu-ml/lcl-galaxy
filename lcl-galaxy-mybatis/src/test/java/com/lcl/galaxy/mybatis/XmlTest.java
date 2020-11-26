package com.lcl.galaxy.mybatis;

import com.lcl.galaxy.mybatis.common.domain.UserDo;
import com.lcl.galaxy.mybatis.common.dto.UserOrderDto;
import com.lcl.galaxy.mybatis.common.dto.UserOrdersDto;
import com.lcl.galaxy.mybatis.xml.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Slf4j
public class XmlTest {

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
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<UserDo> userDoList = userMapper.findUsersByName("lcl");
            log.info("userDoList====[{}]",userDoList);
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testInsertUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserDo userDo = new UserDo();
            userDo.setUsername("qmm2");
            userDo.setAddress("北京");
            int row = userMapper.insertUser(userDo);
            sqlSession.commit();
            log.info("row====[{}]==========insertId====[{}]", row, userDo.getId());
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testGetCount() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int count = userMapper.getCount("qmm2");
            log.info("count====[{}]", count);
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSelectAsAliase() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserDo user = userMapper.selectAsAliase(2);
            log.info("userDo====[{}]",user);
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSelectUserOrderDtoByUserId() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<UserOrderDto> userOrderDto = userMapper.selectUserOrderDtoByUserName("qmm");
            log.info("userOrderDto====[{}]",userOrderDto);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserOrderDtoByUserId2() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<UserOrderDto> userOrderDto = userMapper.selectUserOrderDtoByUserName2("qmm");
            log.info("userOrderDto====[{}]",userOrderDto);
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSelectUserOrderDtosByUserId() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<UserOrdersDto> userOrdersDtoList = userMapper.selectUserOrdersDtoByUserName("lcl");
            log.info("userOrdersDtoList====[{}]",userOrdersDtoList);
        }finally {
            sqlSession.close();
        }
    }
}
