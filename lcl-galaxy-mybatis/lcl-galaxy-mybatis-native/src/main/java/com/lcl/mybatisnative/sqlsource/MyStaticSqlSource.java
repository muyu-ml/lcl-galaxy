package com.lcl.mybatisnative.sqlsource;

import java.util.List;

public class MyStaticSqlSource implements MySqlSource {


    private String sql;
    private List<MyParameterMapping> myParameterMappingList;


    public MyStaticSqlSource(String sql, List<MyParameterMapping> myParameterMappingList){
        this.sql = sql;
        this.myParameterMappingList = myParameterMappingList;
    }


    @Override
    public MyBoundSql getBoundSql(Object param) {
        return new MyBoundSql(sql, myParameterMappingList);
    }
}
