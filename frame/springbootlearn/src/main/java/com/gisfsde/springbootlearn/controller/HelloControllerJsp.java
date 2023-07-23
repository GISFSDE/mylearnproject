package com.gisfsde.springbootlearn.controller;

import javafx.scene.input.DataFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class HelloControllerJsp {
    @RequestMapping("/hellojsp")
    public String hello(Model m){
        //输出格式化时间
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        m.addAttribute("now", sdf.format(date));
        return "hello";
    }
}
