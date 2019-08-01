package com.example.springsecurity2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.springsecurity2.entity.po.Msg;


@Controller
public class UserController {

    @RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("HOME PAGE","","");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/login")
    public String login(Model model){
        Msg msg =  new Msg("LOGIN PAGE","WELCOME","");
        model.addAttribute("msg", msg);
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(Model model){
        Msg msg =  new Msg("ADMIN PAGE","WELCOME","");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/user")
    public String user(Model model){
        Msg msg =  new Msg("USER PAGE","WELCOME","");
        model.addAttribute("msg", msg);
        return "home";
    }

}
