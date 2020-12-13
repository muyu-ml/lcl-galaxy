package com.lcl.galaxy.springmvc.controller;

import com.lcl.galaxy.springmvc.domain.UserDo;
import com.lcl.galaxy.springmvc.dto.OrderInfoDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@RequestMapping("/request")
@RestController
public class RequestController {

    private static final String returnStr = "返回值。。。。。。。。。。。";

    @RequestMapping("str")
    public String getStr(String name){
        return returnStr;
    }

    @RequestMapping("str1")
    public String getStr1(@RequestParam(value = "name",defaultValue = "123",required = false) String name123){
        return returnStr;
    }

    @RequestMapping("user")
    public String user(UserDo userDo){
        return returnStr;
    }

    @RequestMapping("strArray")
    public String strArray(String[] arrs){
        return returnStr;
    }


    @RequestMapping("date")
    public String date(LocalDate dateStr){
        return "获取的日期是" + dateStr;
    }


    @PostMapping("order")
    public String order(@RequestBody OrderInfoDto orderInfoDto){
        return "获取的日期是" + orderInfoDto;
    }

    @RequestMapping("upload")
    public String upLoad(MultipartFile file) throws IOException {
        if (file != null){
            String originalFilename = file.getOriginalFilename();
            String picPath = "";
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newName = UUID.randomUUID() + extName;
            File newFile = new File(newName + "." + extName);
            file.transferTo(newFile);
        }
        return "OK";
    }

}
