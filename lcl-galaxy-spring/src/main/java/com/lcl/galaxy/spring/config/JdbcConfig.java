package com.lcl.galaxy.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@Slf4j
public class JdbcConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;


    @Bean(name = "dataSource")
    public DataSource createDataSource() throws Exception{
        DataSource dataSource = new ComboPooledDataSource();
        ((ComboPooledDataSource) dataSource).setDriverClass(driver);
        ((ComboPooledDataSource) dataSource).setJdbcUrl(url);
        ((ComboPooledDataSource) dataSource).setUser(username);
        ((ComboPooledDataSource) dataSource).setPassword(password);
        log.info("dabasource=============【{}】", dataSource);
        return dataSource;
    }
}
