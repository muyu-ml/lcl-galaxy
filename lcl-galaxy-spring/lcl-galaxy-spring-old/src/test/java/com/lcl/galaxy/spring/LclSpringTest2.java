package com.lcl.galaxy.spring;

import com.lcl.galaxy.spring.aop.UserService;
import com.lcl.galaxy.spring.demo.config.SpringConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring-config4.xml")
@ContextConfiguration(classes = SpringConfiguration.class)
public class LclSpringTest2 {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        String name = userService.around("lcl");
        log.info("=============={}", name);
    }
}
