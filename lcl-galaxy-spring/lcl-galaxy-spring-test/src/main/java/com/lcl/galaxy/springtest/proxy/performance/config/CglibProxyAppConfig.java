package com.lcl.galaxy.springtest.proxy.performance.config;

import com.lcl.galaxy.springtest.proxy.service.AccountService;
import com.lcl.galaxy.springtest.proxy.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
public class CglibProxyAppConfig {
    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccountService getProxy(){
        return new AccountServiceImpl();
    }
}
