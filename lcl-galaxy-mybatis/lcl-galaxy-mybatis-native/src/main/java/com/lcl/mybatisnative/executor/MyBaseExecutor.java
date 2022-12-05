package com.lcl.mybatisnative.executor;

import com.lcl.mybatisnative.config.MyConfiguration;
import com.lcl.mybatisnative.config.MyMappedStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MyBaseExecutor implements MyExecutor {


    private Map<String, Object> oneLevelMap = new HashMap();

    @Override
    public <T> List<T> query(MyMappedStatement myMappedStatement, MyConfiguration myConfiguration, Object param) {

        String sql = myMappedStatement.getMySqlSource().getBoundSql(param).getSql();
        Object object = oneLevelMap.get(sql);
        if(object != null){
            return (List<T>) object;
        }
        object = queryFromDataBase(myMappedStatement, myConfiguration, param);
        return (List<T>) object;
    }

    protected abstract List<Object> queryFromDataBase(MyMappedStatement myMappedStatement, MyConfiguration myConfiguration, Object param);
}
