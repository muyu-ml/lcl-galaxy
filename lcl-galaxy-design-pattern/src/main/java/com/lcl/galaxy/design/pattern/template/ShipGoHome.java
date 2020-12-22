package com.lcl.galaxy.design.pattern.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShipGoHome extends GoHomeTemplate {
    @Override
    protected void ridingTools() {
      log.info("============ by ship ===============");
    }

    @Override
    protected void buyTickets() {
        log.info("============ buy ship tickets===============");
    }
}
