package com.lcl.galaxy.lcl.galaxy.redis.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.*;

@Service
@Slf4j
public class RedisDistributedLock {

    @Autowired
    private LuaRedisLock redisLock;

    @Autowired
    private NoLuaRedisLock noLuaRedisLock;


    public static ThreadPoolExecutor threadPoolExecutor;

    static {
        final int corePoolSize = 10;
        final int maximumPoolSize = 10;
        final long keepAliveTime = 0L;
        final TimeUnit unit = TimeUnit.MILLISECONDS;
        final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100000);
        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("RedisDistributedLockTest").build();
        final RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit, workQueue, threadFactory, handler);
    }



    public void luaRedisLock(){
        String lockKey = "test";
        int num = 1;
        int whileWhere = 0;
        while (whileWhere++ < 10000){
            int localnum = num++;
            threadPoolExecutor.submit(()->{
                String requestId = UUID.randomUUID().toString();

                boolean flag = redisLock.tryLockLua(lockKey, requestId, 3 * 1000);
                if(flag){
                    log.info("第{}条放入线程池数据，requestId={}，第一条数据", localnum,requestId);
                    for (int i = 0; i < 10000; i++){ }
                    log.info("第{}条放入线程池数据，requestId={}，最后一条数据", localnum,requestId);
                    redisLock.releaseLock(lockKey,requestId);
                }
            });
        }
    }


    public void unLuaRedisLock(){
        String lockKey = "test";
        int num = 1;
        int whileWhere = 0;
        while (whileWhere++ < 10000){
            int localnum = num++;
            threadPoolExecutor.submit(()->{
                String requestId = UUID.randomUUID().toString();

                boolean flag = noLuaRedisLock.tryLockLua(lockKey, requestId, 3 * 1000);
                if(flag){
                    log.info("第{}条放入线程池数据，requestId={}，第一条数据", localnum,requestId);
                    for (int i = 0; i < 10000; i++){ }
                    log.info("第{}条放入线程池数据，requestId={}，最后一条数据", localnum,requestId);
                    noLuaRedisLock.releaseLock(lockKey,requestId);
                }
            });
        }
    }


    public void redissonLock(){
        String lockKey = "test";
        int num = 1;
        int whileWhere = 0;
        while (whileWhere++ < 10000){
            int localnum = num++;
            threadPoolExecutor.submit(()->{
                String requestId = UUID.randomUUID().toString();

                boolean flag = RedissonLockService.acquire(lockKey);
                if(flag){
                    log.info("第{}条放入线程池数据，requestId={}，第一条数据", localnum,requestId);
                    for (int i = 0; i < 10000; i++){ }
                    log.info("第{}条放入线程池数据，requestId={}，最后一条数据", localnum,requestId);
                    RedissonLockService.release(lockKey);
                }
            });
        }
    }

}
