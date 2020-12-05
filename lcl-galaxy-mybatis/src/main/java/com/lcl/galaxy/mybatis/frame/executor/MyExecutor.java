package com.lcl.galaxy.mybatis.frame.executor;

import com.lcl.galaxy.mybatis.frame.config.MyConfiguration;
import com.lcl.galaxy.mybatis.frame.config.MyMappedStatement;

import java.util.List;

public interface MyExecutor {
    <T> List<T> query(MyMappedStatement myMappedStatement, MyConfiguration myConfiguration, Object param);
}
