<%--JSP是Java Server Pages的缩写，它的文件必须放到/src/main/webapp下，
文件名必须以.jsp结尾，整个文件与HTML并无太大区别，但需要插入变量，
或者动态输出的地方，使用特殊指令<% ... %>。
包含在<%--和--%->之间的是JSP的注释，它们会被完全忽略；
包含在<%和%>之间的是Java代码，可以编写任意Java代码；
如果使用<%= xxx %>则可以快捷输出一个变量的值。
JSP页面内置了几个变量：
out：表示HttpServletResponse的PrintWriter；
session：表示当前HttpSession对象；
request：表示HttpServletRequest对象。
Servlet就是一个能处理HTTP请求，发送HTTP响应的小程序，
而发送响应无非就是获取PrintWriter，然后输出HTML,由此输出可以看出编写一个界面过于麻烦，所以有了jsp
tomcat.8080临时目录下可以看到jsp在执行之前首先被编译成一个servlet（hello_jsp.java）
JSP本质上就是一个Servlet，只不过无需配置映射路径，Web Server会根据路径查找对应的.jsp文件，
如果找到了，就自动编译成Servlet再执行。在服务器运行过程中，如果修改了JSP的内容，那么服务器会自动重新编译。
page指令---引入Java类：
<%@ page import="java.io.*" %>
include指令---可以引入另一个JSP文件
--%>
<html>
<head>
    <title>Hello World - JSP</title>
</head>
<body>
<%-- JSP Comment --%>
<h1>Hello World!</h1>
<p>
    <%
        out.println("Your IP address is ");
    %>
    <span style="color:red">
        <%= request.getRemoteAddr() %>
    </span>
</p>
</body>
</html>