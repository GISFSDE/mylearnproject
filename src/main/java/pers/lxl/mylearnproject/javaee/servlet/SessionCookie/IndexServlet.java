package pers.lxl.mylearnproject.javaee.servlet.SessionCookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**https://www.runoob.com/servlet/servlet-cookies-handling.html*/
@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从HttpSession获取当前用户名:
        String user = (String) req.getSession().getAttribute("user");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("X-Powered-By", "JavaEE Servlet");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Welcome, " + (user != null ? user : "Guest") + "</h1>");
        if (user == null) {
            // 未登录，显示登录链接:
            pw.write("<p><a href=\"/signin\">Sign In</a></p>");
        } else {
            // 已登录，显示登出链接:
            pw.write("<p><a href=\"/signout\">Sign Out</a></p>");
        }
        pw.flush();
    }

    /**读取 Cookie*/
    private String parseLanguageFromCookie(HttpServletRequest req) {
        // 获取请求附带的所有Cookie:
        Cookie[] cookies = req.getCookies();
        // 如果获取到Cookie:
        if (cookies != null) {
            // 循环每个Cookie:
            for (Cookie cookie : cookies) {
                // 如果Cookie名称为lang:
                if (cookie.getName().equals("lang")) {
                    // 返回Cookie的值:
                    return cookie.getValue();
                }
            }
        }
        // 返回默认值:
        return "en";
    }

//删除 Cookie
//删除 Cookie 是非常简单的。如果您想删除一个 cookie，那么您只需要按照以下三个步骤进行：
//读取一个现有的 cookie，并把它存储在 Cookie 对象中。
//使用 setMaxAge() 方法设置 cookie 的年龄为零，来删除现有的 cookie。
//把这个 cookie 添加到响应头。

}
