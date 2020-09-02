package pers.lxl.mylearnproject.javaee.servlet;
/*----HttpServletRequest----提供的接口方法可以拿到HTTP请求的几乎全部信息，常用的方法有：
getMethod()：返回请求方法，例如，"GET"，"POST"；
getRequestURI()：返回请求路径，但不包括请求参数，例如，"/hello"；
getQueryString()：返回请求参数，例如，"name=Bob&a=1&b=2"；
getParameter(name)：返回请求参数，GET请求从URL读取参数，POST请求从Body中读取参数；
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
public class ServletFirst {

}
