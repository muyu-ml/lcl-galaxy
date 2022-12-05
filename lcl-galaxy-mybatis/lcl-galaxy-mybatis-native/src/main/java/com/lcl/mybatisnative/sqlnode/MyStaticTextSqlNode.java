package com.lcl.mybatisnative.sqlnode;

import com.lcl.mybatisnative.config.MyDynamicContext;

public class MyStaticTextSqlNode implements MySqlNode {

    private String sql;
    public MyStaticTextSqlNode(String sql) {
        this.sql = sql;
    }

    @Override
    public void apply(MyDynamicContext context) {

        context.appendSql(" " + sql);
    }
}
