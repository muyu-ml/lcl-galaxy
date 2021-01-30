package com.lcl.galaxy.lcl.galaxy.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/*
 * 将默认序列化改为Jackson2JsonRedisSerializer序列化，
 * */
@Configuration
public class RedisTemplateConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        //创建Json序列化对象
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 将默认序列化改为Jackson2JsonRedisSerializer序列化
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setKeySerializer(jackson2JsonRedisSerializer);// key序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
        template.setHashKeySerializer(jackson2JsonRedisSerializer);// Hash key序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        return template;
    }
}
