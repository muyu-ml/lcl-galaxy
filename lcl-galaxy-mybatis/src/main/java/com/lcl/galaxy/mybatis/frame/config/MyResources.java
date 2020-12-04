package com.lcl.galaxy.mybatis.frame.config;

import java.io.InputStream;

public class MyResources {
    public static InputStream getResourceAsStream(String resource) {
        return MyResources.class.getClassLoader().getResourceAsStream(resource);
    }
}
