package com.lcl.galaxy.design.pattern.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrainGoHome extends GoHomeTemplate {
    @Override
    protected void ridingTools() {
      log.info("============ by train ===============");
    }

    @Override
    protected void buyTickets() {
        log.info("============ buy train tickets===============");
    }
}
