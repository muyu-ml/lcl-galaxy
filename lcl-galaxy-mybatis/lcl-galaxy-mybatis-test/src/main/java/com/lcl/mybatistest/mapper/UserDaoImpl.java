package com.lcl.mybatistest.mapper;

import com.lcl.mybatistest.domain.UserDo;
import com.lcl.mybatisnative.sqlsession.MySqlSession;
import com.lcl.mybatisnative.sqlsession.MySqlSessionFactory;

import java.util.List;

//@Slf4j
public class UserDaoImpl implements UserDao {

    private MySqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(MySqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public UserDo findUserById(int id) throws Exception {
        MySqlSessionFactory sqlSessionFactory = this.sqlSessionFactory;
        MySqlSession sqlSession = sqlSessionFactory.openSession();
        UserDo userDo = null;
        try {
            userDo = sqlSession.selectOne("com.lcl.mybatistest.mapper.findUserById",id);
        }finally {
            //sqlSession.close();
        }
        return userDo;
    }

    @Override
    public List<UserDo> findUserByUserName(String name) throws Exception {
        UserDo userDo = new UserDo();
        userDo.setUsername(name);
        MySqlSession sqlSession = this.sqlSessionFactory.openSession();
        List<UserDo> userDoList = null;
        try{
            userDoList = sqlSession.selectList("com.lcl.mybatistest.mapper.findUserByUsername",userDo);
        }finally {
            //sqlSession.close();
        }
        return userDoList;
    }

}
