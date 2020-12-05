package com.lcl.galaxy.mybatis.frame.executor;

import com.lcl.galaxy.mybatis.frame.config.MyConfiguration;
import com.lcl.galaxy.mybatis.frame.config.MyMappedStatement;

import java.util.List;

public class MyCachingExecutor implements MyExecutor {


    private MyExecutor myExecutor;

    public MyCachingExecutor(MyExecutor myExecutor) {
        this.myExecutor = myExecutor;
    }

    @Override
    public <T> List<T> query(MyMappedStatement myMappedStatement, MyConfiguration myConfiguration, Object param) {
        return myExecutor.query(myMappedStatement, myConfiguration, param);
    }
}
