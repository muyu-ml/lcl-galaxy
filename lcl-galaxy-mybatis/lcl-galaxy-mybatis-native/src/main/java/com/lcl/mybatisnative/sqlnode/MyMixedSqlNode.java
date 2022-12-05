package com.lcl.mybatisnative.sqlnode;

import com.lcl.mybatisnative.config.MyDynamicContext;

import java.util.List;

public class MyMixedSqlNode implements MySqlNode {

    private List<MySqlNode> mySqlNodeList;

    public MyMixedSqlNode(List<MySqlNode> mySqlNodeList) {
        this.mySqlNodeList = mySqlNodeList;
    }

    @Override
    public void apply(MyDynamicContext context) {
        for (MySqlNode mySqlNode : mySqlNodeList){
            mySqlNode.apply(context);
        }
    }
}
