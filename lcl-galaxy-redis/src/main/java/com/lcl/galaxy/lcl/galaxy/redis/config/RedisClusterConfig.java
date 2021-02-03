/*
package com.lcl.galaxy.lcl.galaxy.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableCaching // 开启缓存支持
public class RedisClusterConfig extends CachingConfigurerSupport {




    @Autowired
    private Environment environment;


    @Bean
    public LettuceConnectionFactory myLettuceConnectionFactory() {
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("spring.redis.cluster.nodes", environment.getProperty("spring.redis.cluster.nodes"));
        source.put("spring.redis.cluster.timeout", environment.getProperty("spring.redis.cluster.timeout"));
        source.put("spring.redis.cluster.max-redirects", environment.getProperty("spring.redis.cluster.max-redirects"));
        RedisClusterConfiguration redisClusterConfiguration;
        redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        return new LettuceConnectionFactory(redisClusterConfiguration);
    }

}
*/
