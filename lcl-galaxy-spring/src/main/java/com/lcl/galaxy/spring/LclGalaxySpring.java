package com.lcl.galaxy.spring;

import com.lcl.galaxy.spring.apis.UserApis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class LclGalaxySpring {

    public static void main(String[] args) {
        ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        UserApis userApis = new UserApis();
        userApis.getUser();
        log.info("==============");
    }
}
