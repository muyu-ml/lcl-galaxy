package com.lcl.mybatisnative.executor;

import com.lcl.mybatisnative.config.MyConfiguration;
import com.lcl.mybatisnative.config.MyMappedStatement;

import java.util.List;

public interface MyExecutor {
    <T> List<T> query(MyMappedStatement myMappedStatement, MyConfiguration myConfiguration, Object param);
}
