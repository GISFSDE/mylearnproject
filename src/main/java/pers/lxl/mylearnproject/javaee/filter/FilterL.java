package pers.lxl.mylearnproject.javaee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*把一些公用逻辑从各个Servlet中抽离出来，JavaEE的Servlet规范还提供了一种Filter组件
即过滤器，它的作用是，在HTTP请求到达Servlet之前，可以被一个或多个Filter预处理，
类似打印日志、登录检查等逻辑，完全可以放到Filter中。
编写Filter时，必须实现Filter接口，在doFilter()方法内部，要继续处理请求，
必须调用chain.doFilter()。最后，用@WebFilter注解标注该Filter需要过滤的URL。这里的/*表示所有路径。
多个Filter时顺序对结果有影响但是只有在web.xml进行配置才能指定顺序
 */
@WebFilter(urlPatterns = "/*")
public class FilterL implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("EncodingFilter:doFilter");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
