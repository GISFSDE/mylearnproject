package pers.lxl.mylearnproject.javaee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**把一些公用逻辑从各个Servlet中抽离出来，JavaEE的Servlet规范还提供了一种Filter组件
即过滤器，它的作用是，在HTTP请求到达Servlet之前，可以被一个或多个Filter预处理，
类似打印日志、登录检查等逻辑，完全可以放到Filter中。
编写Filter时，必须实现Filter接口，在doFilter()方法内部，要继续处理请求，
必须调用chain.doFilter()。最后，用@WebFilter注解标注该Filter需要过滤的URL。这里的/*表示所有路径。
多个Filter时顺序对结果有影响但是只有在web.xml进行配置才能指定顺序
 Servlet 过滤器是可用于 Servlet 编程的 Java 类，可以实现以下目的：

 在客户端的请求访问后端资源之前，拦截这些请求。
 在服务器的响应发送回客户端之前，处理这些响应。
 根据规范建议的各种类型的过滤器：

 身份验证过滤器（Authentication Filters）。
 数据压缩过滤器（Data compression Filters）。
 加密过滤器（Encryption Filters）。
 触发资源访问事件过滤器。
 图像转换过滤器（Image Conversion Filters）。
 日志记录和审核过滤器（Logging and Auditing Filters）。
 MIME-TYPE 链过滤器（MIME-TYPE Chain Filters）。
 标记化过滤器（Tokenizing Filters）。
 XSL/T 过滤器（XSL/T Filters），转换 XML 内容。


 web.xml 中的 filter-mapping 元素的顺序决定了 Web 容器应用过滤器到 Servlet 的顺序。若要反转过滤器的顺序，您只需要在 web.xml 文件中反转 filter-mapping 元素即可。
 */
@WebFilter(urlPatterns = "/*")
public class FilterL implements Filter {
    /**该方法完成实际的过滤操作，当客户端请求方法与过滤器设置匹配的URL时，Servlet容器将先调用过滤器的doFilter方法。FilterChain用户访问后续过滤器。*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        fiter中文处理
        System.out.println("EncodingFilter:doFilter");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//获取来路用户的ip地址
        String ip = request.getRemoteAddr();
//        获取用户访问的页面地址
        String url = request.getRequestURL().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String date = sdf.format(d);

        System.out.printf("%s %s 访问了 %s%n", date, ip, url);
//        过滤器放行，表示继续运行下一个过滤器，或者最终访问的某个servlet,jsp,html等等
        filterChain.doFilter(request, response);
//        配置web.xml
//在web.xml中进行filter的配置，和servlet的配置很类似
//<filter>
//    <filter-name>EncodingFilter</filter-name>
//    <filter-class>filter.EncodingFilter</filter-class>
//</filter>
//<filter-mapping>
//    <filter-name>EncodingFilter</filter-name>
//    <url-pattern>/*</url-pattern>
//</filter-mapping>
//表示所有的访问都会过滤
//如果配置成
//<url-pattern>*.jsp</url-pattern>
//就表示只过滤jsp
        /*<filter>指定一个过滤器。
<filter-name>用于为过滤器指定一个名字，该元素的内容不能为空。
<filter-class>元素用于指定过滤器的完整的限定类名。
<init-param>元素用于为过滤器指定初始化参数，它的子元素<param-name>指定参数的名字，<param-value>指定参数的值。
在过滤器中，可以使用FilterConfig接口对象来访问初始化参数。
<filter-mapping>元素用于设置一个 Filter 所负责拦截的资源。一个Filter拦截的资源可通过两种方式来指定：Servlet 名称和资源访问的请求路径
<filter-name>子元素用于设置filter的注册名称。该值必须是在<filter>元素中声明过的过滤器的名字
<url-pattern>设置 filter 所拦截的请求路径(过滤器关联的URL样式)
<servlet-name>指定过滤器所拦截的Servlet名称。
<dispatcher>指定过滤器所拦截的资源被 Servlet 容器调用的方式，可以是REQUEST,INCLUDE,FORWARD和ERROR之一，默认REQUEST。用户可以设置多个<dispatcher>子元素用来指定 Filter 对资源的多种调用方式进行拦截。
<dispatcher>子元素可以设置的值及其意义
REQUEST：当用户直接访问页面时，Web容器将会调用过滤器。如果目标资源是通过RequestDispatcher的include()或forward()方法访问时，那么该过滤器就不会被调用。
INCLUDE：如果目标资源是通过RequestDispatcher的include()方法访问时，那么该过滤器将被调用。除此之外，该过滤器不会被调用。
FORWARD：如果目标资源是通过RequestDispatcher的forward()方法访问时，那么该过滤器将被调用，除此之外，该过滤器不会被调用。
ERROR：如果目标资源是通过声明式异常处理机制调用时，那么该过滤器将被调用。除此之外，过滤器不会被调用。*/
    }
/**web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，读取web.xml配置，完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作（filter对象只会创建一次，init方法也只会执行一次）。开发人员通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。
 * filterConfig.getInitParameter()获取对象参数*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("First Filter init()");
    }
/**Servlet容器在销毁过滤器实例前调用该方法，在该方法中释放Servlet过滤器占用的资源。*/
    @Override
    public void destroy() {

    }
}
