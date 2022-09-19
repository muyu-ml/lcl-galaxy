package com.example.zookeeper.controller;

import com.example.zookeeper.service.Node;

/**
 * @Desctiption
 * @Author wallace
 * @Date 2021/5/18
 */
@RestController
public class Home {
    @RequestMapping(value = {"/", "/index", "/home"}, method = RequestMethod.GET)
    public String home() throws Exception {

        return Node.INSTANCE.getRole();
    }
}
