package com.lcl.galaxy.springmvc.frame.adapter;

import com.alibaba.fastjson.JSON;
import com.lcl.galaxy.springmvc.frame.annotation.MyResponseBody;
import com.lcl.galaxy.springmvc.frame.handler.MyHandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyRequestMappingHandlerAdapter  implements MyHandlerAdapter {
    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        MyHandlerMethod handlerMethod = (MyHandlerMethod) handler;
        Object controller = handlerMethod.getController();
        Method method = handlerMethod.getMethod();
        Object[] args = getParamters(request, method);
        Object returnValue = null;
        if(args == null){
            returnValue = method.invoke(controller);
        }else{
            returnValue = method.invoke(controller, args);
        }
        handlerReturnValue(returnValue, method, response);

    }

    private void handlerReturnValue(Object returnValue, Method method, HttpServletResponse response) throws IOException {
        if(method.isAnnotationPresent(MyResponseBody.class)){
            if(returnValue instanceof String){
                response.setContentType("text/plain;charset=utf8");
                response.getWriter().write(String.valueOf(returnValue));
            }else {
                response.setContentType("application/json;charset=utf8");
                response.getWriter().write(JSON.toJSONString(returnValue));
            }
        }else {

        }
    }

    private Object[] getParamters(HttpServletRequest request, Method method) {
        List<Object> params = new ArrayList();
        //从对象中获取KV请求
        Map<String, String[]> parameterMap = request.getParameterMap();
        if(parameterMap != null && parameterMap.size() > 0){
            //获取方法的形参集合
            Parameter[] parameters = method.getParameters();
            //遍历所有形参集合
            for (Parameter parameter : parameters){
                //获取形参名称和类型(该步需要特殊处理)
                String name = parameter.getName();
                //获取参数名称去参数集合中获取对应的值
                String[] stringValues = parameterMap.get(name);
                Class<?> type = parameter.getType();
                //参数类型转换
                handlerParamterType(stringValues, type, params);
            }
            return  params.toArray();
        }
        return null;
    }

    private void handlerParamterType(String[] stringValues, Class<?> type, List<Object> params) {
        if(type == String.class){
            params.add(String.valueOf(stringValues[0]));
        }else if(type == Integer.class){
            params.add(Integer.parseInt(stringValues[0]));
        }
    }

    @Override
    public boolean support(Object handler) {
        return handler instanceof MyHandlerMethod;
    }
}
