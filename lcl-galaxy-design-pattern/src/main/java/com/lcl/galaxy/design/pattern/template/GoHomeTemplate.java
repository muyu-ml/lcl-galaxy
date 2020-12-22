package com.lcl.galaxy.design.pattern.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GoHomeTemplate {

    public void goHomeMian(){
        log.info("========= goStation ============");
        buyTickets();
        ridingTools();
        log.info("========= goHome ============");
    }

    protected abstract void ridingTools();

    protected abstract void buyTickets();
}
