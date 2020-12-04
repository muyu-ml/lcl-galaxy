package com.lcl.galaxy.mybatis.frame.sqlsource;

import com.lcl.galaxy.mybatis.frame.sqlnode.MySqlNode;

public class MyRawSqlSource implements MySqlSource {

    private MySqlNode mySqlNode;

    public MyRawSqlSource(MySqlNode mySqlNode) {
        this.mySqlNode = mySqlNode;
    }

    @Override
    public MyBoundSql getBoundSql(Object param) {
        return null;
    }
}
