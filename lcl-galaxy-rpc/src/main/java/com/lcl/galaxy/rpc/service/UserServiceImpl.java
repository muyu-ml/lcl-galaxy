package com.lcl.galaxy.rpc.service;

import com.lcl.galaxy.rpc.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private Map<String, String> userMap = new HashMap();

    public UserServiceImpl(){
        userMap.put("1", "lcl1");
        userMap.put("2", "lcl2");
        userMap.put("3", "lcl3");
        userMap.put("4", "lcl4");
    }

    @Override
    public String getUserNameByCode(String code) {
        return userMap.get(code);
    }
}
