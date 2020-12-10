package com.lcl.galaxy.mybatis;

import com.lcl.galaxy.spring.aop.UserService;
import com.lcl.galaxy.spring.demo.config.SpringConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
