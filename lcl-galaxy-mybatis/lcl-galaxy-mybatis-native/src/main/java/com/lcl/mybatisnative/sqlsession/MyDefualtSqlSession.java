package com.lcl.mybatisnative.sqlsession;

import com.lcl.mybatisnative.config.MyConfiguration;
import com.lcl.mybatisnative.config.MyMappedStatement;
import com.lcl.mybatisnative.executor.MyCachingExecutor;
import com.lcl.mybatisnative.executor.MyExecutor;
import com.lcl.mybatisnative.executor.MySimpleExecutor;

import java.util.List;

public class MyDefualtSqlSession implements MySqlSession {
    private MyConfiguration myConfiguration;
    public MyDefualtSqlSession(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> objects = this.selectList(statementId, param);
        if(objects == null || objects.size() == 0){
            return null;
        }else if(objects.size() != 1){
            throw new RuntimeException("查询出多条数据");
        }
        return (T) objects.get(0);
    }


    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        MyMappedStatement myMappedStatement = myConfiguration.getMyMappedStatement(statementId);
        MyExecutor myExecutor = new MyCachingExecutor(new MySimpleExecutor());
        return myExecutor.query(myMappedStatement, myConfiguration, param);
    }

}
