package com.lcl.galaxy.lcl.galaxy.mongo.apis;

import com.lcl.galaxy.lcl.galaxy.mongo.service.MongDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo")
public class MongoApis {

    @Autowired
    private MongDBService mongDBService;

    @RequestMapping("/test")
    public String testMongo(){
        mongDBService.maintest();
        return "OK";
    }
}
