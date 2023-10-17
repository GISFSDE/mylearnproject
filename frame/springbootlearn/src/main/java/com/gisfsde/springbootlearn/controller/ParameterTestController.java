package com.gisfsde.springbootlearn.controller;

import com.gisfsde.springbootlearn.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lxl
 * @Date: 2022/04/21/22:23
 * @Description:
 */
@RestController
public class ParameterTestController {

    // 数组
    @RequestMapping("/array")
    public String array(String[] hobby) {
        return Arrays.toString(hobby);
    }
    @RequestMapping("/arrayAlone")
    // 单独接收集合数据需要加@RequestParam
    public String arrayAlone(@RequestParam List hobby) {
        return hobby.toString();
    }
    @RequestMapping("/Date")
    // 接收日期数据需要加@DateTimeFormat，并设置时间模板
    public String Date(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime time) {
        System.out.println(time.getClass());
        return time.toString();
    }
    // http://xxx?参数名=值&参数名=值
    // 1.int year 基本类型参数为空会报错，包装类型Integer允许为空
    // 2.各种方法都可使用，可以params和body同时使用，但body参数不会拼接到url后面，但可以接收
    // 3.参数可以不传
    @GetMapping("/getparams")
    public void params(String name, Integer age, int year) {
        System.out.println(name + "," + age + "," + year);
    }

    // 使用@RequestParam 值可以为空，但必须有key值，因为@RequestParam注解的required默认是true
    // 不可使用实体类
    // 指定name要按name传参(age1)，其他(age)会报错
    // value和name属性使用了@AliasFor，在spring当中起着一个注解属性别名传递值的作用。也就是我给value属性赋值，name属性同样也能取到值，name属性赋值，value属性也同样可以取到值。
    // 即@RequestParam(value = "msg")=@RequestParam("msg")=@RequestParam(name = "msg")
    // @RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo 可设置默认值,应对不传情况
    @GetMapping("/requestParam")
    public void requestParam(@RequestParam String name, @RequestParam(name = "age1", required = false) Integer age, String address) {
        System.out.println(name + "," + age + "," + address);
    }

    // 用于绑定 url 中的占位符{name}，名称要一致
    // 不加@PathVariable接收不到url占位符中的值
    @GetMapping("/pathVariable/{name}/{age}")
    public void pathVariable(@PathVariable String name, Integer age) {
        System.out.println(name + "," + age);
    }

    // 参数为实体类时，@RequestBody用来接收http请求中body中json数据
    // get、post都可以使用。一般用于post
    // 部分类型mvc会自动转换，Date 需以yyyy-MM-dd
    // required默认为true 默认不允许传空json
    // @RequestBody String name,如果参数为单个字符串，可以接收 非JSON 格式数据, 传输格式可以为JSON(application/json) TEXT(text/plain)，XML(application/xml)
    // String reqStr1, @RequestBody String reqStr2 参数为多个字符串，修饰其中一个，其他会为空，修饰参数将会以字符串方式传递
    @PostMapping("/requestBody")
    public void requestBody(@RequestBody User user) {
        System.out.println(user);
    }

    // @RequestHeader主要用来获取请求当中的请求头
    @GetMapping("/requestHeader")
    public void requestHeader(@RequestHeader User user) {
        System.out.println(user);
    }
    // HttpServletRequest 可直接获取body或url中的参数
    @GetMapping("/getUrlValue")
    public String getUrlValue(HttpServletRequest request) {
        // 没有的时候不会报错，直接为null
        String msg = request.getParameter("msg");
        System.out.println(msg);
        return msg;
    }
    @GetMapping("/getUrlValues")
    public String getHttpServletRequestValue(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return parameterMap.get("")[0];
    }

    // 可综合使用
    //  car/2/owner/zhangsan
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("inters") List<String> inters,
                                      @RequestParam Map<String, String> params,
                                      @CookieValue("_ga") String _ga,
                                      @CookieValue("_ga") Cookie cookie) {


        Map<String, Object> map = new HashMap<>();

//        map.put("id",id);
//        map.put("name",name);
//        map.put("pv",pv);
//        map.put("userAgent",userAgent);
//        map.put("headers",header);
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        map.put("_ga", _ga);
        System.out.println(cookie.getName() + "===>" + cookie.getValue());
        return map;
    }



    // 1、语法： 请求路径：/cars/sell;low=34;brand=byd,audi,yd
    // 2、SpringBoot默认是禁用了矩阵变量的功能
    //      手动开启：原理。对于路径的处理。UrlPathHelper进行解析。
    //              removeSemicolonContent（移除分号内容）支持矩阵变量的
    // 3、矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    // /boss/1;age=20/2;age=10

    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();

        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;

    }


    /*其他
    * @RequestAttribute注解的参数在项目里是自己解析出来的，并不是前端传递的。具体一点，在项目里的拦截器里会对Token信息进行解析，解析出来的参数重新放在请求里（用httpServletRequest.setAttribute(name, value)），后边接口接收参数时就用这个注解*/

}
