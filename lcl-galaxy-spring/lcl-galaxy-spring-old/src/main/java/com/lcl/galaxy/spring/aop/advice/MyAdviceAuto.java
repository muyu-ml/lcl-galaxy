package com.lcl.galaxy.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component("myAdviceAuto")
public class MyAdviceAuto {

    @Before(value = "execution(void *..*Impl.insert(..))")
    public void log(){
      log.info("===================打印日志===================");
    }


    @Around(value = "execution(* *..*Impl.*(..))")
    public String trans(ProceedingJoinPoint proceedingJoinPoint){
        Object[] args = proceedingJoinPoint.getArgs();
        String proceed = null;
        try {
            proceed = "返回结果" + (String) proceedingJoinPoint.proceed(args) ;
        } catch (Throwable throwable) {
            return "================异常";
        }
        return proceed;
    }


    @Before(value = "com.lcl.galaxy.spring.aop.advice.MyAdviceAuto.fn()" )
    public void logfn(){
        log.info("=========================logfn=============================");
    }

    @Before(value = "com.lcl.galaxy.spring.aop.advice.MyAdviceAuto.fn()" )
    public void killfn(){
        log.info("=========================killfn=============================");
    }

    @Pointcut(value = "execution(* *..*.*(..))")
    public void fn(){
        log.info("===================fn()====================");
    }
}
