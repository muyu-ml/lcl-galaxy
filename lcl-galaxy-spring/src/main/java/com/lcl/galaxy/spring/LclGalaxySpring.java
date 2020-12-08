package com.lcl.galaxy.spring;

import com.lcl.galaxy.spring.apis.UserApis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class LclGalaxySpring {

    public static void main(String[] args) {
        //ApplicationContext factory1=new ClassPathXmlApplicationContext("classpath:spring-config1.xml");
        ApplicationContext factory2=new ClassPathXmlApplicationContext("classpath:spring-config2.xml");

        log.info("==============");
    }

}
