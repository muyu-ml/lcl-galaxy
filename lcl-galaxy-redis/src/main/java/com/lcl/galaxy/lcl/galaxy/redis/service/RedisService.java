package com.lcl.galaxy.lcl.galaxy.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void testRedis(){

        stringRedisTemplate.opsForValue().set("lcl1","val1");

        String val1 = stringRedisTemplate.opsForValue().get("lcl1");

        System.out.println("lcl1=============" + val1);

    }

    public void testRedisSentinel(){

        stringRedisTemplate.opsForValue().set("lcl1","val1");

        String val1 = stringRedisTemplate.opsForValue().get("lcl1");

        System.out.println("lcl1=============" + val1);

    }
}
