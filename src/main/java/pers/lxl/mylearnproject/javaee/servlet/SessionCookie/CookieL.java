package pers.lxl.mylearnproject.javaee.servlet.SessionCookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*浏览器在请求某个URL时，是否携带指定的Cookie，取决于Cookie是否满足以下所有要求：
URL前缀是设置Cookie时的Path；
Cookie在有效期内；
Cookie设置了secure时必须以https访问。*/
@WebServlet(urlPatterns = "/pref")
public class CookieL extends HttpServlet {

    //private static final Set<String> LANGUAGES = Set.of("en", "zh");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
//        if (LANGUAGES.contains(lang)) {
        if (1==1) {
            // 创建一个新的Cookie:
            Cookie cookie = new Cookie("lang", lang);
            // 该Cookie生效的路径范围:
            cookie.setPath("/");
            // 该Cookie有效期:
            cookie.setMaxAge(8640000); // 8640000秒=100天
            // 将该Cookie添加到响应:
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/");
    }
}
