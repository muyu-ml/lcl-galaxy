package com.lcl.galaxy.lcl.galaxy.redis.service;

import com.google.common.primitives.Longs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * redis乐观锁
 */
@Slf4j
@Service
public class LuaRedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    //加锁lua脚本
    private static final String SCRIPT_TRY_LOCK = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then redis.call('pexpire', KEYS[1],5000) return true else return false end";
    private static final String SCRIPT_UN_LOCK =  "if redis.call('get'  ,KEYS[1]) == ARGV[1] then redis.call('del',KEYS[1]) return true else return false end";

    /**
     * 2.6之前
     *
     * @return
     */
    public boolean tryLockLua(String lockKey, String requestId, int expire) {
        RedisCallback<Boolean> callback = (connection) -> {
            return connection.eval(SCRIPT_TRY_LOCK.getBytes(), ReturnType.BOOLEAN, 1,
                    lockKey.getBytes(Charset.forName("UTF-8")),
                    requestId.getBytes(Charset.forName("UTF-8")));
        };
        return (boolean) redisTemplate.execute(callback);
    }


    public boolean releaseLock(String lockKey, String requestId) {
        RedisCallback<Boolean> callback = (connection) -> {
            return connection.eval(SCRIPT_UN_LOCK.getBytes(), ReturnType.BOOLEAN, 1,
                    lockKey.getBytes(Charset.forName("UTF-8")),
                    requestId.getBytes(Charset.forName("UTF-8")));
        };
        return (Boolean) redisTemplate.execute(callback);
    }

}
