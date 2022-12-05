package com.lcl.mybatisnative.config;


import com.lcl.mybatisnative.sqlsource.MySqlSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MyMappedStatement {
    private String statementId;
    private Class<?> paramterType;
    private Class<?> resultType;
    private String statementType;
    private MySqlSource mySqlSource;
}
