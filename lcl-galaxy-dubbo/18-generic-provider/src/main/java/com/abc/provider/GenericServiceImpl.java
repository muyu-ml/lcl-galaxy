package com.abc.provider;


import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

public class GenericServiceImpl implements GenericService {

    /**
     *
     * @param method  消费者调用的方法名
     * @param parameterTypes  消费者调用的方法的参数类型列表
     * @param args  消费者调用方法时传递的实参值
     * @return
     * @throws GenericException
     */
    @Override
    public Object $invoke(String method, String[] parameterTypes,
                          Object[] args) throws GenericException {
        if ("hello".equals(method)) {
            return "Generic hello，" + args[0];
        }
        return null;
    }
}
