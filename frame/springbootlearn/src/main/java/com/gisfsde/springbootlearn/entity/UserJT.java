package com.gisfsde.springbootlearn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
//这里使用了Lombok的@Data和@NoArgsConstructor注解来自动生成各参数的Set、Get函数以及不带参数的构造函数
@Data
@NoArgsConstructor
public class UserJT {
    private String name;
    private Integer age;
}
