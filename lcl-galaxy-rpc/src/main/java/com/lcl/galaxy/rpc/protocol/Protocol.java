package com.lcl.galaxy.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class Protocol implements Serializable {

    //接口名称（包括完整类路径）
    private String interfaceName;
    //调用方法名
    private  String methodName;
    //参数类型按照接口参数顺序定义
    private Class[] paramsTypes;
    //参数的数据值
    private Object[] parameters;

    public Protocol() {
        super();
    }

    public Protocol(String interfaceName, String methodName, Class[] paramsTypes, Object[] parameters) {
        super();
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramsTypes = paramsTypes;
        this.parameters = parameters;
    }
}
