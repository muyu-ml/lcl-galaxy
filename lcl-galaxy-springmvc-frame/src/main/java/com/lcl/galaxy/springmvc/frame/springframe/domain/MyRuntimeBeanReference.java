package com.lcl.galaxy.springmvc.frame.springframe.domain;

import lombok.Data;

@Data
public class MyRuntimeBeanReference {

    private String ref;

    public MyRuntimeBeanReference(String ref) {
        this.ref = ref;
    }
}
