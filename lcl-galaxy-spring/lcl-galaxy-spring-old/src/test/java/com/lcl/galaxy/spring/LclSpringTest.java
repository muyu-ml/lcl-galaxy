package com.lcl.galaxy.spring;

import com.lcl.galaxy.spring.aop.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class LclSpringTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config4.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        String name = userService.around("lcl");
        log.info("=============={}", name);
    }
}
