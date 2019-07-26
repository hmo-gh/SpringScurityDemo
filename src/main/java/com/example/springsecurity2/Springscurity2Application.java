package com.example.springsecurity2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springsecurity2.mapper")
public class Springscurity2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springscurity2Application.class, args);
    }

}
