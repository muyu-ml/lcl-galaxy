package com.lcl.galaxy.spring;

import com.lcl.galaxy.spring.apis.UserApis;
import com.lcl.galaxy.spring.config.JdbcConfig;
import com.lcl.galaxy.spring.config.SpringConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class LclGalaxySpring {

    public static void main(String[] args) {
        //ApplicationContext factory1=new ClassPathXmlApplicationContext("classpath:spring-config1.xml");
        //ApplicationContext factory2=new ClassPathXmlApplicationContext("classpath:spring-config2.xml");

        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(JdbcConfig.class);

        log.info("==============");
    }

}
