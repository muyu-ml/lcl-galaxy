package com.lcl.galaxy.springmvc.controller;

import com.lcl.galaxy.springmvc.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ErrorTest {

    @RequestMapping(value = "/error", produces = "text/plain;charset=UTF-8")
    public String errTest(String id) throws MyException, Exception {
        id = new String(id.getBytes("ISO8859-1"),"utf-8") ;
        log.info("id==========={}", id);
        if("1".equals(id)){
            throw new MyException("id = 1  error");
        }
        return "成功";
    }
}
