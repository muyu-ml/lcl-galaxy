package com.lcl.galaxy.mybatis.frame.sqlsource;

import com.lcl.galaxy.mybatis.frame.sqlnode.MySqlNode;

public class MyDynamicSqlSource implements MySqlSource {


    private MySqlNode mySqlNode;

    public MyDynamicSqlSource(MySqlNode mySqlNode) {
        this.mySqlNode = mySqlNode;
    }

    @Override
    public MyBoundSql getBoundSql(Object param) {
        return null;
    }
}
