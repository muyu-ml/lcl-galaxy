package com.lcl.galaxy.mybatis.frame.sqlsession;

import com.lcl.galaxy.mybatis.frame.config.MyConfiguration;
import com.lcl.galaxy.mybatis.frame.config.MyMappedStatement;

import java.util.List;

public class MyDefualtSqlSession implements MySqlSession {
    private MyConfiguration myConfiguration;
    public MyDefualtSqlSession(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> objects = this.selctList(statementId, param);
        if(objects == null || objects.size() == 0){
            return null;
        }
        return (T) objects.get(0);
    }

    @Override
    public <T> List<T> selctList(String statementId, Object param) {
        MyMappedStatement myMappedStatement = myConfiguration.getMyMappedStatement(statementId);
        return null;
    }
}
