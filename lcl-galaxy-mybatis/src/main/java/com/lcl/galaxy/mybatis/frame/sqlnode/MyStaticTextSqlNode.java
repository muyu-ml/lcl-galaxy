package com.lcl.galaxy.mybatis.frame.sqlnode;

import com.lcl.galaxy.mybatis.frame.config.MyDynamicContext;
import com.lcl.galaxy.mybatis.frame.util.GenericTokenParser;
import com.lcl.galaxy.mybatis.frame.util.ParameterMappingTokenHandler;

public class MyStaticTextSqlNode implements MySqlNode {

    private String sql;
    public MyStaticTextSqlNode(String sql) {
        this.sql = sql;
    }

    @Override
    public void apply(MyDynamicContext context) {

        context.appendSql(sql);
    }
}
