package com.lcl.galaxy.mybatis.simple.dao.impl;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import com.lcl.galaxy.mybatis.simple.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

@Slf4j
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public UserDo findUserById(int id) throws Exception {
        SqlSessionFactory sqlSessionFactory = this.sqlSessionFactory;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDo userDo = null;
        try {
            userDo = sqlSession.selectOne("test.findUserById",id);
        }finally {
            sqlSession.close();
        }
        return userDo;
    }

    @Override
    public List<UserDo> findUsersByName(String name) throws Exception {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        List<UserDo> userDoList = null;
        try{
            userDoList = sqlSession.selectList("test.findUserByUsername",name);
        }finally {
            sqlSession.close();
        }
        return userDoList;
    }

}
