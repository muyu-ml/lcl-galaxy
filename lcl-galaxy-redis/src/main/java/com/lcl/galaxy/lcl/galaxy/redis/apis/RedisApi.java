package com.lcl.galaxy.lcl.galaxy.redis.apis;

import com.lcl.galaxy.lcl.galaxy.redis.service.LuaService;
import com.lcl.galaxy.lcl.galaxy.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisApi {

    @Autowired
    private LuaService luaService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/sentinel")
    public void testSentinel(){
        redisService.testRedis();
    }

    @GetMapping("/lua")
    public String luaTest(String actId,String userId,int buyNum,String skuId,int perSkuLim,int perActLim){
        return luaService.skuSecond(actId,userId,buyNum,skuId,perSkuLim,perActLim);
    }
}
