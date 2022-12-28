package com.lcl.galaxy.spring.frame.V2.domain;

import lombok.Data;

@Data
public class MyRuntimeBeanReference {

    private String ref;

    public MyRuntimeBeanReference(String ref) {
        this.ref = ref;
    }
}
