package pers.lxl.mylearnproject.javaee.servlet.SessionCookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**浏览器在请求某个URL时，是否携带指定的Cookie，取决于Cookie是否满足以下所有要求：
URL前缀是设置Cookie时的Path；
Cookie在有效期内；
Cookie设置了secure时必须以https访问。
 /**https://www.runoob.com/servlet/servlet-cookies-handling.html*/
@WebServlet(urlPatterns = "/pref")
public class CookieL extends HttpServlet {

    //private static final Set<String> LANGUAGES = Set.of("en", "zh");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
//        if (LANGUAGES.contains(lang)) {
        if (1==1) {
            // 1.创建一个新的Cookie:
//            无论是名字还是值，都不应该包含空格或以下任何字符：
//             [ ] ( ) = , " / ? @ : ;
            Cookie cookie = new Cookie("lang", lang);
            // 该Cookie生效的路径范围:
            cookie.setPath("/");
            // 该Cookie有效期:
            // 8640000秒=100天
            cookie.setMaxAge(8640000);
            // 将该Cookie添加到响应:
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/");
    }
}
/*序号	方法 & 描述
1	public void setDomain(String pattern)
该方法设置 cookie 适用的域，例如 runoob.com。
2	public String getDomain()
该方法获取 cookie 适用的域，例如 runoob.com。
3	public void setMaxAge(int expiry)
该方法设置 cookie 过期的时间（以秒为单位）。如果不这样设置，cookie 只会在当前 session 会话中持续有效。
4	public int getMaxAge()
该方法返回 cookie 的最大生存周期（以秒为单位），默认情况下，-1 表示 cookie 将持续下去，直到浏览器关闭。
5	public String getName()
该方法返回 cookie 的名称。名称在创建后不能改变。
6	public void setValue(String newValue)
该方法设置与 cookie 关联的值。
7	public String getValue()
该方法获取与 cookie 关联的值。
8	public void setPath(String uri)
该方法设置 cookie 适用的路径。如果您不指定路径，与当前页面相同目录下的（包括子目录下的）所有 URL 都会返回 cookie。
9	public String getPath()
该方法获取 cookie 适用的路径。
10	public void setSecure(boolean flag)
该方法设置布尔值，表示 cookie 是否应该只在加密的（即 SSL）连接上发送。
11	public void setComment(String purpose)
设置cookie的注释。该注释在浏览器向用户呈现 cookie 时非常有用。
12	public String getComment()
获取 cookie 的注释，如果 cookie 没有注释则返回 null。*/