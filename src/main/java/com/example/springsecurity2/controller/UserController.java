package com.example.springsecurity2.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {
    @GetMapping("hello")
    public String hello(HttpServletRequest request, HttpServletResponse response){
        return "hello";
    }

    @RequestMapping("/toLogin")
    @ResponseBody
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView printAdmin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView printUser() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user");
        return model;
    }

}
