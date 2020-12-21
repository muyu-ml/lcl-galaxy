package com.lcl.galaxy.design.pattern.factory.abstractPattern.shelf;

import com.lcl.galaxy.design.pattern.factory.abstractPattern.desk.Desk;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IronShelf extends Shelf {
    @Override
    public void printShelf() {
            log.info("========== this is iron shelf ===========");
    }
}
