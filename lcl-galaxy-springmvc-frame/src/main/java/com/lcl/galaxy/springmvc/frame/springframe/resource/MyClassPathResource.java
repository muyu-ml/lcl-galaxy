package com.lcl.galaxy.springmvc.frame.springframe.resource;

import java.io.InputStream;

public class MyClassPathResource implements MyResources {

    private String location;

    public MyClassPathResource(String location){
        this.location = location;
    }

    @Override
    public InputStream getResourceAsStream() {
        return MyResources.class.getClassLoader().getResourceAsStream(location);
    }
}
