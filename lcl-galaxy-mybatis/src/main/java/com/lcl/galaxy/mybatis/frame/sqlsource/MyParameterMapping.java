package com.lcl.galaxy.mybatis.frame.sqlsource;

import lombok.Data;

@Data
public class MyParameterMapping {

    private String name;

    public MyParameterMapping(String content) {
        this.name = content;
    }
}
