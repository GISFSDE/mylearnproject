package com.gisfsde.springbootlearn.controller;

import com.gisfsde.springbootlearn.entity.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxl
 */
@RestController//@Controller 和 @ResponseBody 注解的合体版
public class HelloController {
    //SpringBoot 可以通过@PropertySource，@Value，@Environment，@ConfigurationProperties 绑定变量

    @Value("${name}")
    /**我们并没有在 yml 文件中注明属性的类型，而是在使用的时候定义的*/
    private String name;

    @Value("${age}")
    private Integer age;


    // 这样写配置文件繁琐而且可能会造成类的臃肿，因为有许许多多的 @Value 注解,我们可以把配置信息封装成一个类

    @Value("${content}")
    private String content;

    @Autowired
    private StudentProperties studentProperties;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!"+name+age+content+studentProperties.getName()+studentProperties.getAge();
    }


}
