package com.lcl.galaxy.springmvc.frame.springframe.domain;

import lombok.Data;

@Data
public class MyPropertyValue {

    private String name;

    private Object typedStringValue;

    public MyPropertyValue(String name, Object typedStringValue) {
        this.name = name;
        this.typedStringValue = typedStringValue;
    }
}
