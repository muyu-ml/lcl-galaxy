package com.lcl.galaxy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password){
        if("lcl".equals(username) && "123".equals(password)){
            session.setAttribute("username", username);
            return "redirect:orderInfo";
        }
        return "login";
    }

    @RequestMapping("/orderInfo")
    public String order(HttpSession session, String username, String password){
        return "orderInfo";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }
}
