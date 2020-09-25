package pers.lxl.mylearnproject.springmvc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//@Controller
//public class HelloController {
//    @RequestMapping("/")
//    @ResponseBody
//    public String hello() {
//        return "Hello, world!";
//    }
//}

/**
 * 第一个页面
 */
//@Controller
//public class HelloController {
//    @RequestMapping("/")
//    public String hello() {
//        return "resultPage";
//    }
//}

/**
 * 将数据传递给视图
 */
//@Controller
//public class HelloController {
//    @RequestMapping("/")
//    public String hello(Model model) {
//        model.addAttribute("message", "Hello from the controller");
//        return "resultPage";
//    }
//}
/**
 *从请求参数中获取数据
 * localhost:8080/?name=Geoffroy
 * 请求参数是强制要求存在的。这意味着，如果导航至 localhost:8080，那
 * 么将会看到一个错误页面
 */
@Controller
public class HelloController {
    @RequestMapping("/")
//    public String hello(@RequestParam("name") String userName, Model
//            model) {
//        model.addAttribute("message", "Hello, " + userName);
//        return "resultPage";
//    }
    public String hello(@RequestParam(defaultValue = "world") String
                                name, Model model) {
        model.addAttribute("message", "Hello, " + name);
        return "resultPage";
    }
}