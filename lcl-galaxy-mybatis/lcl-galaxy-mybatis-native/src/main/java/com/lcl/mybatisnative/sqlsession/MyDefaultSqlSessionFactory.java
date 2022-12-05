package com.lcl.mybatisnative.sqlsession;

import com.lcl.mybatisnative.config.MyConfiguration;

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
