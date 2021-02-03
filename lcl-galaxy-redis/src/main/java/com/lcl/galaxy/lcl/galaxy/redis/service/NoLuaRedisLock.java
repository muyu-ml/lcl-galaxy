package com.lcl.galaxy.lcl.galaxy.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * redis乐观锁
 */
@Slf4j
@Service
public class NoLuaRedisLock {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 2.6之前
     *
     * @return
     */
    public boolean tryLockLua(String lockKey, String requestId, int expire) {
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expire, TimeUnit.MILLISECONDS);
        if(flag == null){
            return false;
        }
        return flag;
    }


    public boolean releaseLock(String lockKey, String requestId) {
        String value = (String) redisTemplate.opsForValue().get(lockKey);
        if(!requestId.equals(value)){
            return false;
        }
        Boolean flag = redisTemplate.delete(lockKey);
        if(flag == null){
            return false;
        }
        return flag;
    }

}
