package com.lcl.galaxy.es.controller;

import com.lcl.galaxy.es.service.HtmlParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class HtmlParseController {
    @Autowired
    private HtmlParseService service;

    //从京东爬取关键字信息(title、price、src)存放到es中
    @GetMapping("/parse/{keyword}")
    @ResponseBody
    public Boolean parse(@PathVariable String keyword) throws Exception{
        return service.parseHtmlParseDTO(keyword);
    }

    //从es中查询相关的信息 参数一：关键字 参数二：页码 参数三：显示条数
    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Map<String,Object>> search(@PathVariable("keyword")String keyword,
                                           @PathVariable("pageNo")int pageNo,
                                           @PathVariable("pageSize")int pageSize) throws IOException {
        if(pageNo==0){
            pageNo=1;
        }
        if(pageSize==0){
            pageSize=5;
        }
        return service.searchPage(keyword,pageNo,pageSize);
    }

    @GetMapping("/parse/add2es")
    public String test(String keyword){
        System.out.println(keyword);
        return "redirect:/parse/"+keyword;
    }

}
