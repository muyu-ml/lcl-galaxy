package com.lcl.mybatisnative.sqlsource;

import com.lcl.mybatisnative.config.MyDynamicContext;
import com.lcl.mybatisnative.sqlnode.MySqlNode;

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
