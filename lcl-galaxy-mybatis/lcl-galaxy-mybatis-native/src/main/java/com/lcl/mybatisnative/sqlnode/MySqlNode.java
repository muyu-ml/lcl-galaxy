package com.lcl.mybatisnative.sqlnode;

import com.lcl.mybatisnative.config.MyDynamicContext;

public interface MySqlNode {
    void apply(MyDynamicContext context);
}
