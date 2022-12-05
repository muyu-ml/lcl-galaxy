package com.lcl.mybatisnative.executor;

import com.lcl.mybatisnative.config.MyConfiguration;
import com.lcl.mybatisnative.config.MyMappedStatement;

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
