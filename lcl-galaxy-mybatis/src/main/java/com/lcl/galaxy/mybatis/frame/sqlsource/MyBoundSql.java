package com.lcl.galaxy.mybatis.frame.sqlsource;

import lombok.Data;

import java.util.List;

@Data
public class MyBoundSql {

    private String sql;
    private List<MyParameterMapping> myParameterMappings;

    public MyBoundSql(String sql, List<MyParameterMapping> myParameterMappingList) {
        this.sql = sql;
        this.myParameterMappings = myParameterMappingList;
    }
}
