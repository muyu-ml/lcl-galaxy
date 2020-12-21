package com.lcl.galaxy.design.pattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dog extends Animal{
    @Override
    public void eat() {
        log.info("============ dog eat =============");
    }
}
