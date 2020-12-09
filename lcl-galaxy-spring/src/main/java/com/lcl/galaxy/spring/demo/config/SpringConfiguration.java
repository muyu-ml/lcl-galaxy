package com.lcl.galaxy.spring.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.annotation.*;

@Configuration
@Slf4j
@ComponentScan(basePackages = "com.lcl.galaxy.spring")
@Import(JdbcConfig.class)
public class SpringConfiguration {

    public SpringConfiguration(){
        log.info("spring容器启动");
    }

    @Bean
    @Scope("singletion")
    public DefaultSqlSessionFactory getSqlSession(){
        return new DefaultSqlSessionFactory(new org.apache.ibatis.session.Configuration());
    }

}
