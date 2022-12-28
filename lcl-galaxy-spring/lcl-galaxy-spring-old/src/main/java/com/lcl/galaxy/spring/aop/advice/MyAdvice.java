package com.lcl.galaxy.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyAdvice {
    public void log(){
      log.info("===================打印日志===================");
    }
}
