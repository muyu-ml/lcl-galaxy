package com.lcl.galaxy.mybatis.common.dao.impl;

import com.lcl.galaxy.mybatis.common.dao.FrameUserDao;
import com.lcl.galaxy.mybatis.frame.sqlsession.MySqlSession;
import com.lcl.galaxy.mybatis.frame.sqlsession.MySqlSessionFactory;
import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FrameUserDaoImpl implements FrameUserDao {

    private MySqlSessionFactory mySqlSessionFactory;
    public FrameUserDaoImpl(MySqlSessionFactory mySqlSessionFactory){
        this.mySqlSessionFactory = mySqlSessionFactory;
    }

    @Override
    public UserDo findUser(UserDo userDo) throws Exception {

        MySqlSession mySqlSession = mySqlSessionFactory.openSession();
        UserDo user = mySqlSession.selectOne("lcltest.findUser", userDo);
        return user;
    }
}
