package pers.lxl.mylearnproject.javaee.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
/*需熟悉常见方法，数据转送方式*/
/**WebServlet注解表示这是一个Servlet，并映射到地址*/
@WebServlet(urlPatterns = "/")
public class HelloServlet extends HttpServlet {
/**HttpServletRequest和HttpServletResponse两个对象，分别代表HTTP请求和响应*/

/**GET 请求来自于一个 URL 的正常请求，或者来自于一个未指定 METHOD 的 HTML 表单，它由 doGet() 方法处理。
 * http://www.test.com/hello?key1=value1&key2=value2
 * 哪些是get方式呢？
 * form默认的提交方式
 * 如果通过一个超链访问某个地址
 * 如果在地址栏直接输入某个地址
 * ajax指定使用get方式的时候*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置响应类型:
        resp.setContentType("text/html");
//        获取表单参数值
        String name = req.getParameter("name");
        if (name == null) {
            name = "world";
        }
        // 获取输出流:
        PrintWriter pw = resp.getWriter();
        // 写入响应:
        pw.write("<h1>Hello, "+name+"</h1>");
        // 最后不要忘记flush强制输出:
        pw.flush();
    }
/**声明周期：构造函数--》init--》service--》destory--》被回收*/
    public HelloServlet() {
        super();
    }

    @Override
    protected long getLastModified(HttpServletRequest req) {
        return super.getLastModified(req);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }
/**POST 请求来自于一个特别指定了 METHOD 为 POST 的 HTML 表单，它由 doPost() 方法处理。
 * 哪些是post方式呢？
 * 在form上显示设置 method="post"的时候
 * ajax指定post方式的时候*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
/**service() 方法是执行实际任务的主要方法。Servlet 容器（即 Web 服务器）调用 service() 方法来处理来自客户端（浏览器）的请求，并把格式化的响应写回给客户端。
 每次服务器接收到一个 Servlet 请求时，服务器会产生一个新的线程并调用服务。service() 方法检查 HTTP 请求类型（GET、POST、PUT、DELETE 等），并在适当的时候调用 doGet、doPost、doPut，doDelete 等方法。
 service() 方法由容器调用，service 方法在适当的时候调用 doGet、doPost、doPut、doDelete 等方法。所以，您不用对 service() 方法做任何动作，您只需要根据来自客户端的请求类型来重写 doGet() 或 doPost() 即可。*/
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

//    -----------------------------------------------------

/**destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。
 在调用 destroy() 方法之后，servlet 对象被标记为垃圾回收。*/
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public String getInitParameter(String name) {
        return super.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return super.getInitParameterNames();
    }

    @Override
    public ServletConfig getServletConfig() {
        return super.getServletConfig();
    }

    @Override
    public ServletContext getServletContext() {
        return super.getServletContext();
    }

    @Override
    public String getServletInfo() {
        return super.getServletInfo();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
/**init 方法被设计成只调用一次。它在第一次创建 Servlet 时被调用，在后续每次用户请求时不再调用。因此，它是用于一次性初始化，就像 Applet 的 init 方法一样。
 Servlet 创建于用户第一次调用对应于该 Servlet 的 URL 时，但是您也可以指定 Servlet 在服务器第一次启动时被加载。
 当用户调用一个 Servlet 时，就会创建一个 Servlet 实例，每一个用户请求都会产生一个新的线程，适当的时候移交给 doGet 或 doPost 方法。init() 方法简单地创建或加载一些数据，这些数据将被用于 Servlet 的整个生命周期。*/
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void log(String message) {
        super.log(message);
    }

    @Override
    public void log(String message, Throwable t) {
        super.log(message, t);
    }

    @Override
    public String getServletName() {
        return super.getServletName();
    }
}
//package后war包放tomcat的webapps下，启动Tomcat后进行访问即可
//http://localhost:8080/hello/,后面加hello是因为项目叫hello，改为ROOT.war即可作为默认应用在http://localhost:8080/访问
//类似Tomcat这样的Web服务器也称为Servlet容器
//在Servlet容器中运行的Servlet具有如下特点：
//无法在代码中直接通过new创建Servlet实例，必须由Servlet容器自动创建Servlet实例；
//Servlet容器只会给每个Servlet类创建唯一实例；
//Servlet容器会使用多线程执行doGet()或doPost()方法。
//在Servlet中定义的实例变量会被多个线程同时访问，要注意线程安全；
//HttpServletRequest和HttpServletResponse实例是由Servlet容器传入的局部变量，它们只能被当前线程访问，不存在多个线程访问的问题；
//在doGet()或doPost()方法中，如果使用了ThreadLocal，但没有清理，那么它的状态很可能会影响到下次的某个请求，因为Servlet容器很可能用线程池实现线程复用。
//因此，正确编写Servlet，要清晰理解Java的多线程模型，需要同步访问的必须同步。


//tomcat手动程序启动
//启动简单，无需下载Tomcat或安装任何IDE插件；
//调试方便，可在IDE中使用断点调试；
//使用Maven创建war包后，也可以正常部署到独立的Tomcat服务器中。


//<packaging>类型仍然为war，引入依赖tomcat-embed-core和tomcat-embed-jasper，引入的Tomcat版本<tomcat.version>为9.0.26
//public class Main {
//    public static void main(String[] args) throws Exception {
//        // 启动Tomcat:
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(Integer.getInteger("port", 8080));
//        tomcat.getConnector();
//        // 创建webapp:
//        Context ctx = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
//        WebResourceRoot resources = new StandardRoot(ctx);
//        resources.addPreResources(
//                new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
//        ctx.setResources(resources);
//        tomcat.start();
//        tomcat.getServer().await();
//    }
//}

/**----HttpServletRequest----提供的接口方法可以拿到HTTP请求的几乎全部信息，常用的方法有：
 getMethod()：返回请求方法，例如，"GET"，"POST"；
 getRequestURI()：返回请求路径，但不包括请求参数，例如，"/hello"；
 getQueryString()：返回请求参数，例如，"name=Bob&a=1&b=2"；
 getParameter(name)：返回请求参数，GET请求从URL读取参数，POST请求从Body中读取参数；
 getParameterValues()：如果参数出现一次以上，则调用该方法，并返回多个值，例如复选框。
 getParameterNames()：如果您想要得到当前请求中的所有参数的完整列表，则调用该方法。
 getContentType()：获取请求Body的类型，例如，"application/x-www-form-urlencoded"；
 getContextPath()：获取当前Webapp挂载的路径，对于ROOT来说，总是返回空字符串""；
 getCookies()：返回请求携带的所有Cookie；
 getHeader(name)：获取指定的Header，对Header名称不区分大小写；
 getHeaderNames()：返回所有Header名称；
 getInputStream()：如果该请求带有HTTP Body，该方法将打开一个输入流用于读取Body；
 getReader()：和getInputStream()类似，但打开的是Reader；
 getRemoteAddr()：返回客户端的IP地址；
 getScheme()：返回协议类型，例如，"http"，"https"；
 此外，HttpServletRequest还有两个方法：setAttribute()和getAttribute()，
 可以给当前HttpServletRequest对象附加多个Key-Value，相当于把HttpServletRequest当作一个Map<String, Object>使用。

 ------HttpServletResponse------封装了一个HTTP响应。由于HTTP响应必须先发送Header，
 再发送Body，所以，操作HttpServletResponse对象时，必须先调用设置Header的方法，最后调用发送Body的方法。
 常用的设置Header的方法有：
 setStatus(sc)：设置响应代码，默认是200；
 setContentType(type)：设置Body的类型，例如，"text/html"；
 setCharacterEncoding(charset)：设置字符编码，例如，"UTF-8"；
 setHeader(name, value)：设置一个Header的值；
 addCookie(cookie)：给响应添加一个Cookie；
 addHeader(name, value)：给响应添加一个Header，因为HTTP协议允许有多个相同的Header；
 写入响应时，需要通过getOutputStream()获取写入流，或者通过getWriter()获取字符流，二者只能获取其中一个。
 写入响应前，无需设置setContentLength()，因为底层服务器会根据写入的字节数自动设置，如果写入的数据量很小
 ，实际上会先写入缓冲区，如果写入的数据量很大，服务器会自动采用Chunked编码让浏览器能识别数据结束符而不需要设置Content-Length头。
 但是，写入完毕后调用flush()却是必须的，因为大部分Web服务器都基于HTTP/1.1协议，会复用TCP连接。如果没有调用flush()，
 将导致缓冲区的内容无法及时发送到客户端。此外，写入完毕后千万不要调用close()，原因同样是因为会复用TCP连接，如果关闭写入流，将关闭TCP连接，使得Web服务器无法复用此TCP连接。*/