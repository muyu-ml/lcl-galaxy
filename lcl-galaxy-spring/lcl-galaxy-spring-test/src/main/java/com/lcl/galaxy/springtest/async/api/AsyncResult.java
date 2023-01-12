package com.lcl.galaxy.springtest.async.api;

import java.util.concurrent.Future;

public interface AsyncResult<T> extends Future<T> {
    Object getResult();
}
