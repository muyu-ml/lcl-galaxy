package com.lcl.galaxy.mybatis.frame.config;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MyDynamicContext {
    private StringBuilder sb = new StringBuilder();
    private Map<String, Object> bingds = new HashMap<>();


    public MyDynamicContext(Object param){
        bingds.put("_param", param);
    }


    public Map<String, Object> getBingds(){
        return bingds;
    }

    public void appendSql(String sql){
        sb.append(sql);
    }

    public String getSql(){
        return sb.toString();
    }
}
