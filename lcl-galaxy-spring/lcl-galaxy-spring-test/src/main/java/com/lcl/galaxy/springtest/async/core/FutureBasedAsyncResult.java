package com.lcl.galaxy.springtest.async.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureBasedAsyncResult<T> extends AbstractAsyncResult<T>{

    private Future<T> future;

    private Object value;


    @Override
    public Object getResult() {
        if(future == null){
            return this.value;
        }
        try {
            T t = future.get();
//            if(null != t){
//                return ((FutureBasedAsyncResult)t).getValue();
//            }
            return t;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setFuture(Future<T> future) {
        this.future = future;
    }

}
