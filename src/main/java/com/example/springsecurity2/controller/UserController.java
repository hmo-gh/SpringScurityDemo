package com.example.springsecurity2.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.image.ImageWatched;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;


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

//    @PostMapping("/login")
//    @ResponseBody
//    public Map<String, Object> helloLogin(@RequestBody JSONObj) {
//        Map<String, Object> responseMap = new LinkedHashMap<>();
//
//
//
//        return responseMap;
//    }

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

    @RequestMapping("/denied")
    @ResponseBody
    public ModelAndView deniedUser() {
        ModelAndView model = new ModelAndView();
        model.setViewName("denied");
        return model;
    }

}
