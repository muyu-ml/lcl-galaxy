package com.lcl.galaxy.mybatis.frame.sqlsource;

import com.lcl.galaxy.mybatis.frame.config.MyDynamicContext;
import com.lcl.galaxy.mybatis.frame.sqlnode.MySqlNode;

public class MyDynamicSqlSource implements MySqlSource {


    private MySqlNode mySqlNode;

    public MyDynamicSqlSource(MySqlNode mySqlNode) {
        this.mySqlNode = mySqlNode;
    }

    @Override
    public MyBoundSql getBoundSql(Object param) {
        MyDynamicContext context = new MyDynamicContext(param);
        mySqlNode.apply(context);
        MySqlSourceParser mySqlSourceParser = new MySqlSourceParser();
        MySqlSource sqlSource = mySqlSourceParser.parse(context.getSql());
        MyBoundSql boundSql = sqlSource.getBoundSql(param);
        return boundSql;
    }
}
