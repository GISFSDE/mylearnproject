package com.gisfsde.springbootlearn.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component//表明当前类是一个 Java Bean
//配置绑定,需要@Component,或者使用的地方使用
//当不能在原类中添加@component注解时（比如用他人包时），则在使用的地方添加此注解,作用是开启student配置绑定功能且将其注入到容器中
//@EnableConfigurationProperties(StudentProperties.class)
@ConfigurationProperties(prefix = "student")//表示获取所有配置文件中前缀为 sutdent 的配置信息
public class StudentProperties {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
