package com.lcl.galaxy.springnative.resource;

import java.io.InputStream;

public class ClasspathResource implements Resource{
    private String location;

    public ClasspathResource(String location){
        this.location = location;
    }

    @Override
    public InputStream getResourceAsStream() {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
