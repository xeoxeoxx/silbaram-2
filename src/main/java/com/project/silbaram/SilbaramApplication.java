package com.project.silbaram;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.project.silbaram.dao")
@SpringBootApplication
public class SilbaramApplication {

    public static void main(String[] args) {
        SpringApplication.run(SilbaramApplication.class, args);
    }

}
