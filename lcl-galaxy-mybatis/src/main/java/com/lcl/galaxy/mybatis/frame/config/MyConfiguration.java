package com.lcl.galaxy.mybatis.frame.config;

import lombok.Data;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.HashMap;
import java.util.Map;

@Data
public class MyConfiguration {
    private BasicDataSource basicDataSource;
    private Map<String, MyMappedStatement> mappedStatementMap = new HashMap<>();

    public MyMappedStatement getMyMappedStatement(String statementId){
        return mappedStatementMap.get(statementId);
    }
}
