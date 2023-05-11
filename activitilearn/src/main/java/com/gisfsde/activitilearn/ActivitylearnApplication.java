package com.gisfsde.activitilearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * activiti6 支持springboot1.2.6到2.0.0，其他版本添加一下注解
 */
@SpringBootApplication(exclude = {
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class ActivitylearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitylearnApplication.class, args);
    }

}
