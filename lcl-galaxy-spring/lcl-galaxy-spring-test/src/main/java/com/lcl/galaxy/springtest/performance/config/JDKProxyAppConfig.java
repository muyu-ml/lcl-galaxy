package com.lcl.galaxy.springtest.performance.config;

import com.lcl.galaxy.springtest.service.AccountService;
import com.lcl.galaxy.springtest.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
public class JDKProxyAppConfig {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.INTERFACES)
    public AccountService getProxy(){
        return new AccountServiceImpl();
    }
}
