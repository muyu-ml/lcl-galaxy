package com.lcl.galaxy.mybatis.frame.sqlnode;

import com.lcl.galaxy.mybatis.frame.config.MyDynamicContext;
import com.lcl.galaxy.mybatis.frame.util.OgnlUtils;

public class MyIfSqlNode implements MySqlNode{


    private String test;
    private MySqlNode mySqlNode;

    public MyIfSqlNode(String test, MyMixedSqlNode myMixedSqlNode) {
        this.test = test;
        this.mySqlNode = myMixedSqlNode;
    }

    @Override
    public void apply(MyDynamicContext context) {
        boolean flag = OgnlUtils.evaluateBoolean(test, context.getBingds().get("_param"));
        if(flag){
            mySqlNode.apply(context);
        }
    }
}
