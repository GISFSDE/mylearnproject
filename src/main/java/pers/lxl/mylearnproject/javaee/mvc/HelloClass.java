package pers.lxl.mylearnproject.javaee.mvc;
/*Servlet适合编写Java代码，实现各种复杂的业务逻辑，但不适合输出复杂的HTML；
JSP适合编写HTML，并在其中插入动态内容，但不适合编写复杂的Java代码。
UserServlet看作业务逻辑处理，把User看作模型，把user.jsp看作渲染，
这种设计模式通常被称为MVC：Model-View-Controller，
即UserServlet作为控制器（Controller），User作为模型（Model），
user.jsp作为视图（View），整个MVC架构如下：
Browser--->Controller: UserServlet-->Model: User--->Browser
*/
public class HelloClass {

}
