package com.lcl.galaxy.springtest.async.core;

import com.lcl.galaxy.springtest.async.api.AsyncExecutor;
import com.lcl.galaxy.springtest.async.api.AsyncResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

public class ThreadPoolBasedAsyncExecutor extends ThreadPoolExecutor implements AsyncExecutor {

    private static volatile boolean isInit = false;

    private static volatile boolean isDestroy = false;

    private static ExecutorService executorService = null;

    public ThreadPoolBasedAsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolBasedAsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolBasedAsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadPoolBasedAsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @SuppressWarnings("all")
    public static <T> AsyncResult<T> submit(Object target, Method method, Object[] objs){
        if(!isInit){
            init();
        }
        Future future = executorService.submit(() -> method.invoke(target, objs));
//        Future future = executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    method.invoke(target, objs);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        FutureBasedAsyncResult<T> asyncResult = new FutureBasedAsyncResult<>();
        asyncResult.setFuture(future);
        return asyncResult;
    }

    private static synchronized void init(){
        if(isInit){
            return;
        }
        executorService = Executors.newFixedThreadPool(10);
        updateExecutorStatus(true);
    }

    private static synchronized void destory(){
        if(isDestroy){
            return;
        }
        executorService = null;
        updateExecutorStatus(false);
    }

    private static void updateExecutorStatus(final boolean initStatus) {
        isInit = initStatus;
        isDestroy = !isInit;
    }
}
