package pers.lxl.mylearnproject.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//WebServlet注解表示这是一个Servlet，并映射到地址/:
@WebServlet(urlPatterns = "/")
public class HelloServlet extends HttpServlet {
//    HttpServletRequest和HttpServletResponse两个对象，分别代表HTTP请求和响应
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置响应类型:
        resp.setContentType("text/html");
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