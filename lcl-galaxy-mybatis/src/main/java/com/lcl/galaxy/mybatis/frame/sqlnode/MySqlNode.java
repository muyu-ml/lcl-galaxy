package com.lcl.galaxy.mybatis.frame.sqlnode;

import com.lcl.galaxy.mybatis.frame.config.MyDynamicContext;

public interface MySqlNode {
    void apply(MyDynamicContext context);
}
