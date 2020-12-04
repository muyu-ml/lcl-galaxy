package com.lcl.galaxy.mybatis.frame.sqlsession;

import com.lcl.galaxy.mybatis.frame.config.MyConfiguration;

public class MyDefaultSqlSessionFactory implements MySqlSessionFactory {

    private MyConfiguration myConfiguration;

    public MyDefaultSqlSessionFactory(MyConfiguration myConfiguration){
        this.myConfiguration = myConfiguration;
    }

    @Override
    public MySqlSession openSession() {
        return new MyDefualtSqlSession(myConfiguration);
    }
}
