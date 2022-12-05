package com.lcl.mybatisnative.sqlsource;

import lombok.Data;

@Data
public class MyParameterMapping {

    private String name;

    public MyParameterMapping(String content) {
        this.name = content;
    }
}
