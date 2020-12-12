package com.lcl.galaxy.springmvc.controller;

import com.lcl.galaxy.springmvc.domain.UserDo;
import com.lcl.galaxy.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/res")
@Controller
public class ResultController {

    private static final String str = "这是一个静态资源";

    @RequestMapping("/view")
    public ModelAndView view(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("user",str);
        return new ModelAndView("hello","user",str);
    }

    @RequestMapping("/http")
    public void getStr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(str);
    }

    @RequestMapping("/forward")
    public void forword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view").forward(request, response);
    }

    @RequestMapping("/redirect")
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("http");
    }

    @RequestMapping("/index")
    public String get(){
        return "hello";
    }

    @RequestMapping("/str")
    @ResponseBody
    public String getStr(){
        return "hello";
    }

    @RequestMapping("/strRedirect")
    public String rediect(){
        return "redirect:http";
    }

    @RequestMapping("/strforword")
    public String forword(){
        return "forward:view";
    }

}
