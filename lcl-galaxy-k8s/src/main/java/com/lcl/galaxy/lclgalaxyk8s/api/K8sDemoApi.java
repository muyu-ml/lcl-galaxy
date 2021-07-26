package com.lcl.galaxy.lclgalaxyk8s.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/k8s")
public class K8sDemoApi {
    @RequestMapping("test1")
    public String test1(){
        return " test1 version 1.0 OK ~" ;
    }
}
