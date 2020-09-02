package pers.lxl.mylearnproject.javaee.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/*任何标注为@WebListener，且实现了特定接口的类会被Web服务器自动初始化。上述AppListener实现了ServletContextListener接口，
它会在整个Web应用程序初始化完成后，以及Web应用程序关闭后获得回调通知。我们可以把初始化数据库连接池等工作放到contextInitialized()回调方法中，
把清理资源的工作放到contextDestroyed()回调方法中，因为Web服务器保证在contextInitialized()执行后，才会接受用户的HTTP请求。
很多第三方Web框架都会通过一个ServletContextListener接口初始化自己。
除了ServletContextListener外，还有几种Listener：
HttpSessionListener：监听HttpSession的创建和销毁事件；
ServletRequestListener：监听ServletRequest请求的创建和销毁事件；
ServletRequestAttributeListener：监听ServletRequest请求的属性变化事件（即调用ServletRequest.setAttribute()方法）；
ServletContextAttributeListener：监听ServletContext的属性变化事件（即调用ServletContext.setAttribute()方法）；
一个Web服务器可以运行一个或多个WebApp，对于每个WebApp，Web服务器都会为其创建一个全局唯一的ServletContext实例，我们在AppListener里面编写的两个回调方法实际上对应的就是ServletContext实例的创建和销毁：
public void contextInitialized(ServletContextEvent sce) {
    System.out.println("WebApp initialized: ServletContext = " + sce.getServletContext());}
ServletRequest、HttpSession等很多对象也提供getServletContext()方法获取到同一个ServletContext实例。ServletContext实例最大的作用就是设置和共享全局信息。
此外，ServletContext还提供了动态添加Servlet、Filter、Listener等功能，它允许应用程序在运行期间动态添加一个组件，虽然这个功能不是很常用。*/
@WebListener
public class ListenerL implements ServletContextListener {
    // 在此初始化WebApp,例如打开数据库连接池等:
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("WebApp initialized.");
    }

    // 在此清理WebApp,例如关闭数据库连接池等:
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("WebApp destroyed.");
    }
}