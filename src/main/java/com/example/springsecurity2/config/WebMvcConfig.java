package com.example.springsecurity2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/toLogin").setViewName("login");
//        registry.addViewController("/user").setViewName("user");
//        registry.addViewController("/admin").setViewName("admin");
    }
}