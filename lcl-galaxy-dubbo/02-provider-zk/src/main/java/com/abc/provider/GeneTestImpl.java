package com.abc.provider;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

public class GeneTestImpl implements GenericService {
    @Override
    public Object $invoke(String method, String[] paramterTypes, Object[] args) throws GenericException {
        if("geneHello".equals(method)){
            return "test OK ~";
        }
        return null;
    }
}
