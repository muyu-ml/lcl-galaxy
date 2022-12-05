package com.lcl.mybatisnative.sqlnode;

import com.lcl.mybatisnative.config.MyDynamicContext;
import com.lcl.mybatisnative.util.OgnlUtils;

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
