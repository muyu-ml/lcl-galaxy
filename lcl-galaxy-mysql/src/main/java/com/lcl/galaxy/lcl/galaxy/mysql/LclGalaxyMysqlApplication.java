package com.lcl.galaxy.lcl.galaxy.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.lcl.galaxy.lcl.galaxy.mysql.dao")
public class LclGalaxyMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(LclGalaxyMysqlApplication.class, args);
    }

}
