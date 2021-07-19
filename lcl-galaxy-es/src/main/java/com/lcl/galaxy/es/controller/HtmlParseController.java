package com.lcl.galaxy.es.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HtmlParseController {
    /*@Autowired
    private ContentService contentService;

    //从京东爬取关键字信息(title、price、src)存放到es中
    @GetMapping("/parse/{keyword}")
    @ResponseBody
    public Boolean parse(@PathVariable String keyword) throws Exception{
        return contentService.parseContent(keyword);
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
        return contentService.searchPage(keyword,pageNo,pageSize);
    }

    @GetMapping("/parse/add2es")
    public String test(String keyword){
        System.out.println(keyword);
        return "redirect:/parse/"+keyword;
    }
*/
}
