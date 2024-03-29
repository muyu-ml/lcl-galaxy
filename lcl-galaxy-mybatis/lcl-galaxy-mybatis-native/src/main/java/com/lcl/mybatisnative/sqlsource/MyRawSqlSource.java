package com.lcl.mybatisnative.sqlsource;

import com.lcl.mybatisnative.config.MyDynamicContext;
import com.lcl.mybatisnative.sqlnode.MySqlNode;

public class MyRawSqlSource implements MySqlSource {

    private MySqlSource mySqlSource;

    public MyRawSqlSource(MySqlNode mySqlNode) {
        MyDynamicContext context = new MyDynamicContext(null);
        mySqlNode.apply(context);
        MySqlSourceParser mySqlSourceParser = new MySqlSourceParser();
        mySqlSource = mySqlSourceParser.parse(context.getSql());
    }

    @Override
    public MyBoundSql getBoundSql(Object param) {
        return mySqlSource.getBoundSql(param);
    }
}
