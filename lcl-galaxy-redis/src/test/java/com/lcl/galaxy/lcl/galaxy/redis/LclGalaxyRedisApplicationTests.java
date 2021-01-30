package com.lcl.galaxy.lcl.galaxy.redis;

import com.lcl.galaxy.lcl.galaxy.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class LclGalaxyRedisApplicationTests {

    /**
     * redisService
     */
    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
    }


    @Test
    void redisTest() {
        redisService.testRedis();
    }

    @Test
    void redisSentinelTest() {
        redisService.testRedis();
    }

}
