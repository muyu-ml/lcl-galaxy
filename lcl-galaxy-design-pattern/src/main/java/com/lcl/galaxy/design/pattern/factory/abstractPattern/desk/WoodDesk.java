package com.lcl.galaxy.design.pattern.factory.abstractPattern.desk;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WoodDesk extends Desk {
    @Override
    public void printDesk() {
       log.info("========== this is wood desk ===========");
    }
}
