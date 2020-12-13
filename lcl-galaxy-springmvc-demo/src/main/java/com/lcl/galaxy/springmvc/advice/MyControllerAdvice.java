package com.lcl.galaxy.springmvc.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class MyControllerAdvice {

    @ModelAttribute
    public Map<String, String> ma(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "lcl");
        return map;
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        log.info("========initBinder==========");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handlerExection(Exception e){
        return e.getMessage();
    }
}
