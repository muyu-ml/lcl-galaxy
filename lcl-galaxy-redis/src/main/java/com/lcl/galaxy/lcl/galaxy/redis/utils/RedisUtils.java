package com.lcl.galaxy.lcl.galaxy.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public String runLuaScript(String luaFileName, List<String> keyList) {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/" + luaFileName)));
        redisScript.setResultType(String.class);
        String result = "";
        String argsone = "none";
        //logger.error("开始执行lua");
        try {
            result = stringRedisTemplate.execute(redisScript, keyList, argsone);
        } catch (Exception e) {
            log.error("秒杀失败", e);
        }

        return result;
    }
}
