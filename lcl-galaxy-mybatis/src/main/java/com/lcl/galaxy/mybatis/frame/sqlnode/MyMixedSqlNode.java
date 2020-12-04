package com.lcl.galaxy.mybatis.frame.sqlnode;

import com.lcl.galaxy.mybatis.frame.config.MyDynamicContext;

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
