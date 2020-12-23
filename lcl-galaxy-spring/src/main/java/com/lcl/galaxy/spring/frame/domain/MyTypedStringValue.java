package com.lcl.galaxy.spring.frame.domain;

import lombok.Data;

@Data
public class MyTypedStringValue {

    private String value;

    private Class<?>  targetType;

    public MyTypedStringValue(String value) {
        this.value = value;
    }
}
