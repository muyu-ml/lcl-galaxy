package com.lcl.galaxy.lcl.galaxy.redis.config;

import org.redisson.Redisson;
import org.redisson.config.Config;

public class RedissonManager {
    private static Config config = new Config(); //声明redisso对象
    private static Redisson redisson = null;

    static{
        config.useClusterServers() // 集群状态扫描间隔时间，单位是毫秒
                .setScanInterval(2000) //cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)
                .addNodeAddress("redis://8.131.245.53:8001" )
                .addNodeAddress("redis://8.131.245.53:8002" )
                .addNodeAddress("redis://8.131.245.53:8003" )
                .addNodeAddress("redis://8.131.245.53:8004" )
                .addNodeAddress("redis://8.131.245.53:8005" )
                .addNodeAddress("redis://8.131.245.53:8006" );
        //得到redisson对象
        redisson = (Redisson) Redisson.create(config);
    }

    //获取redisson对象的方法
    public static Redisson getRedisson(){
        return redisson;
    }
}
