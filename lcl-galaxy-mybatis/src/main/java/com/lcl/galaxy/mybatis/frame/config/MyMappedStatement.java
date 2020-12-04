package com.lcl.galaxy.mybatis.frame.config;


import com.lcl.galaxy.mybatis.frame.sqlsource.MySqlSource;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyMappedStatement {
    private String statementId;
    private Class<?> paramterType;
    private Class<?> resultType;
    private String statementType;
    private MySqlSource mySqlSource;
}
