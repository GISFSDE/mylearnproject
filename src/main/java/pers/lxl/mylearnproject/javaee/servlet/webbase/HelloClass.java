package pers.lxl.mylearnproject.javaee.servlet.webbase;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**其他网络相关知识：https://www.runoob.com/servlet/servlet-http-status-codes.html
请求页面的流程如下：

与服务器建立TCP连接；
发送HTTP请求；
收取HTTP响应，然后把网页在浏览器中显示出来。
浏览器发送的HTTP请求如下：

GET / HTTP/1.1
Host: www.sina.com.cn
User-Agent: Mozilla/5.0 xxx
Accept:  *'//*

        Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8
        其中，第一行表示使用GET请求获取路径为/的资源，并使用HTTP/1.1协议，从第二行开始，每行都是以Header: Value形式表示的HTTP头，比较常用的HTTP Header包括：
        Host: 表示请求的主机名，因为一个服务器上可能运行着多个网站，因此，Host表示浏览器正在请求的域名；
        User-Agent: 标识客户端本身，例如Chrome浏览器的标识类似Mozilla/5.0 ... Chrome/79，IE浏览器的标识类似Mozilla/5.0 (Windows NT ...) like Gecko；
        Accept：表示浏览器能接收的资源类型，如text/*，image/*或者*;//*表示所有；
        Accept-Language：表示浏览器偏好的语言，服务器可以据此返回不同语言的网页；
        Accept-Encoding：表示浏览器可以支持的压缩类型，例如gzip, deflate, br。
        服务器的响应如下：
        HTTP/1.1 200 OK
        Content-Type: text/html
        Content-Length: 21932
        Content-Encoding: gzip
        Cache-Control: max-age=300
<html>...网页数据...
        服务器响应的第一行总是版本号+空格+数字+空格+文本，数字表示响应代码，其中2xx表示成功，3xx表示重定向，4xx表示客户端引发的错误，5xx表示服务器端引发的错误。数字是给程序识别，文本则是给开发者调试使用的。常见的响应代码有：
        200 OK：表示成功；
        301 Moved Permanently：表示该URL已经永久重定向；
        302 Found：表示该URL需要临时重定向；
        304 Not Modified：表示该资源没有修改，客户端可以使用本地缓存的版本；
        400 Bad Request：表示客户端发送了一个错误的请求，例如参数无效；
        401 Unauthorized：表示客户端因为身份未验证而不允许访问该URL；
        403 Forbidden：表示服务器因为权限问题拒绝了客户端的请求；
        404 Not Found：表示客户端请求了一个不存在的资源；
        500 Internal Server Error：表示服务器处理时内部出错，例如因为无法连接数据库；
        503 Service Unavailable：表示服务器此刻暂时无法处理请求。
        从第二行开始，服务器每一行均返回一个HTTP头。服务器经常返回的HTTP Header包括：
        Content-Type：表示该响应内容的类型，例如text/html，image/jpeg；
        Content-Length：表示该响应内容的长度（字节数）；
        Content-Encoding：表示该响应压缩算法，例如gzip；
        Cache-Control：指示客户端应如何缓存，例如max-age=300表示可以最多缓存300秒。
        HTTP请求和响应都由HTTP Header和HTTP Body构成，其中HTTP Header每行都以\r\n结束。如果遇到两个连续的\r\n，那么后面就是HTTP Body。浏览器读取HTTP Body，并根据Header信息中指示的Content-Type、Content-Encoding等解压后显示网页、图像或其他内容。
        通常浏览器获取的第一个资源是HTML网页，在网页中，如果嵌入了JavaScript、CSS、图片、视频等其他资源，浏览器会根据资源的URL再次向服务器请求对应的资源。*/

/**读取 HTTP 头的方法下面的方法可用在 Servlet 程序中读取 HTTP 头。这些方法通过 HttpServletRequest 对象可用。
 * 1	Cookie[] getCookies()
 * 返回一个数组，包含客户端发送该请求的所有的 Cookie 对象。
 * 2	Enumeration getAttributeNames()
 * 返回一个枚举，包含提供给该请求可用的属性名称。
 * 3	Enumeration getHeaderNames()
 * 返回一个枚举，包含在该请求中包含的所有的头名。
 * 4	Enumeration getParameterNames()
 * 返回一个 String 对象的枚举，包含在该请求中包含的参数的名称。
 * 5	HttpSession getSession()
 * 返回与该请求关联的当前 session 会话，或者如果请求没有 session 会话，则创建一个。
 * 6	HttpSession getSession(boolean create)
 * 返回与该请求关联的当前 HttpSession，或者如果没有当前会话，且创建是真的，则返回一个新的 session 会话。
 * 7	Locale getLocale()
 * 基于 Accept-Language 头，返回客户端接受内容的首选的区域设置。
 * 8	Object getAttribute(String name)
 * 以对象形式返回已命名属性的值，如果没有给定名称的属性存在，则返回 null。
 * 9	ServletInputStream getInputStream()
 * 使用 ServletInputStream，以二进制数据形式检索请求的主体。
 * 10	String getAuthType()
 * 返回用于保护 Servlet 的身份验证方案的名称，例如，"BASIC" 或 "SSL"，如果JSP没有受到保护则返回 null。
 * 11	String getCharacterEncoding()
 * 返回请求主体中使用的字符编码的名称。
 * 12	String getContentType()
 * 返回请求主体的 MIME 类型，如果不知道类型则返回 null。
 * 13	String getContextPath()
 * 返回指示请求上下文的请求 URI 部分。
 * 14	String getHeader(String name)
 * 以字符串形式返回指定的请求头的值。
 * 15	String getMethod()
 * 返回请求的 HTTP 方法的名称，例如，GET、POST 或 PUT。
 * 16	String getParameter(String name)
 * 以字符串形式返回请求参数的值，或者如果参数不存在则返回 null。
 * 17	String getPathInfo()
 * 当请求发出时，返回与客户端发送的 URL 相关的任何额外的路径信息。
 * 18	String getProtocol()
 * 返回请求协议的名称和版本。
 * 19	String getQueryString()
 * 返回包含在路径后的请求 URL 中的查询字符串。
 * 20	String getRemoteAddr()
 * 返回发送请求的客户端的互联网协议（IP）地址。
 * 21	String getRemoteHost()
 * 返回发送请求的客户端的完全限定名称。
 * 22	String getRemoteUser()
 * 如果用户已通过身份验证，则返回发出请求的登录用户，或者如果用户未通过身份验证，则返回 null。
 * 23	String getRequestURI()
 * 从协议名称直到 HTTP 请求的第一行的查询字符串中，返回该请求的 URL 的一部分。
 * 24	String getRequestedSessionId()
 * 返回由客户端指定的 session 会话 ID。
 * 25	String getServletPath()
 * 返回调用 JSP 的请求的 URL 的一部分。
 * 26	String[] getParameterValues(String name)
 * 返回一个字符串对象的数组，包含所有给定的请求参数的值，如果参数不存在则返回 null。
 * 27	boolean isSecure()
 * 返回一个布尔值，指示请求是否使用安全通道，如 HTTPS。
 * 28	int getContentLength()
 * 以字节为单位返回请求主体的长度，并提供输入流，或者如果长度未知则返回 -1。
 * 29	int getIntHeader(String name)
 * 返回指定的请求头的值为一个 int 值。
 * 30	int getServerPort()
 * 返回接收到这个请求的端口号。
 * 31	int getParameterMap()
 * 将参数封装成 Map 类型。
 */
// *一个 Web 服务器响应一个 HTTP 请求时，响应通常包括一个状态行、一些响应报头、一个空行和文档。一个典型的响应如下所示：
// * HTTP/1.1 200 OK
// * Content-Type: text/html
// * Header2: ...
// * ...
// * HeaderN: ...
// *   (Blank Line)
// <!doctype ...>
// * <html>
// * <head>...</head>
// * <body>
// * ...
// * </body>
// * </html>

//状态行包括 HTTP 版本（在本例中为 HTTP/1.1）、一个状态码（在本例中为 200）和一个对应于状态码的短消息（在本例中为 OK）。
//下表总结了从 Web 服务器端返回到浏览器的最有用的 HTTP 1.1 响应报头，您会在 Web 编程中频繁地使用它们：
//头信息	描述
//Allow	这个头信息指定服务器支持的请求方法（GET、POST 等）。
//Cache-Control	这个头信息指定响应文档在何种情况下可以安全地缓存。可能的值有：public、private 或 no-cache 等。Public 意味着文档是可缓存，Private 意味着文档是单个用户私用文档，且只能存储在私有（非共享）缓存中，no-cache 意味着文档不应被缓存。
//Connection	这个头信息指示浏览器是否使用持久 HTTP 连接。值 close 指示浏览器不使用持久 HTTP 连接，值 keep-alive 意味着使用持久连接。
//Content-Disposition	这个头信息可以让您请求浏览器要求用户以给定名称的文件把响应保存到磁盘。
//Content-Encoding	在传输过程中，这个头信息指定页面的编码方式。
//Content-Language	这个头信息表示文档编写所使用的语言。例如，en、en-us、ru 等。
//Content-Length	这个头信息指示响应中的字节数。只有当浏览器使用持久（keep-alive）HTTP 连接时才需要这些信息。
//Content-Type	这个头信息提供了响应文档的 MIME（Multipurpose Internet Mail Extension）类型。
//Expires	这个头信息指定内容过期的时间，在这之后内容不再被缓存。
//Last-Modified	这个头信息指示文档的最后修改时间。然后，客户端可以缓存文件，并在以后的请求中通过 If-Modified-Since 请求头信息提供一个日期。
//Location	这个头信息应被包含在所有的带有状态码的响应中。在 300s 内，这会通知浏览器文档的地址。浏览器会自动重新连接到这个位置，并获取新的文档。
//Refresh	这个头信息指定浏览器应该如何尽快请求更新的页面。您可以指定页面刷新的秒数。
//Retry-After	这个头信息可以与 503（Service Unavailable 服务不可用）响应配合使用，这会告诉客户端多久就可以重复它的请求。
//Set-Cookie	这个头信息指定一个与页面关联的 cookie。
    //设置 HTTP 响应报头的方法
//下面的方法可用于在 Servlet 程序中设置 HTTP 响应报头。这些方法通过 HttpServletResponse 对象可用。
//序号	方法 & 描述
//1	String encodeRedirectURL(String url)
//为 sendRedirect 方法中使用的指定的 URL 进行编码，或者如果编码不是必需的，则返回 URL 未改变。
//2	String encodeURL(String url)
//对包含 session 会话 ID 的指定 URL 进行编码，或者如果编码不是必需的，则返回 URL 未改变。
//3	boolean containsHeader(String name)
//返回一个布尔值，指示是否已经设置已命名的响应报头。
//4	boolean isCommitted()
//返回一个布尔值，指示响应是否已经提交。
//5	void addCookie(Cookie cookie)
//把指定的 cookie 添加到响应。
//6	void addDateHeader(String name, long date)
//添加一个带有给定的名称和日期值的响应报头。
//7	void addHeader(String name, String value)
//添加一个带有给定的名称和值的响应报头。
//8	void addIntHeader(String name, int value)
//添加一个带有给定的名称和整数值的响应报头。
//9	void flushBuffer()
//强制任何在缓冲区中的内容被写入到客户端。
//10	void reset()
//清除缓冲区中存在的任何数据，包括状态码和头。
//11	void resetBuffer()
//清除响应中基础缓冲区的内容，不清除状态码和头。
//12	void sendError(int sc)
//使用指定的状态码发送错误响应到客户端，并清除缓冲区。
//13	void sendError(int sc, String msg)
//使用指定的状态发送错误响应到客户端。
//14	void sendRedirect(String location)
//使用指定的重定向位置 URL 发送临时重定向响应到客户端。
//15	void setBufferSize(int size)
//为响应主体设置首选的缓冲区大小。
//16	void setCharacterEncoding(String charset)
//设置被发送到客户端的响应的字符编码（MIME 字符集）例如，UTF-8。
//17	void setContentLength(int len)
//设置在 HTTP Servlet 响应中的内容主体的长度，该方法设置 HTTP Content-Length 头。
//18	void setContentType(String type)
//如果响应还未被提交，设置被发送到客户端的响应的内容类型。
//19	void setDateHeader(String name, long date)
//设置一个带有给定的名称和日期值的响应报头。
//20	void setHeader(String name, String value)
//设置一个带有给定的名称和值的响应报头。
//21	void setIntHeader(String name, int value)
//设置一个带有给定的名称和整数值的响应报头。
//22	void setLocale(Locale loc)
//如果响应还未被提交，设置响应的区域。
//23	void setStatus(int sc)
//为该响应设置状态码。
public class HelloClass {
    public class Server {
        public void main(String[] args) throws IOException {
            ServerSocket ss = new ServerSocket(8080); // 监听指定端口
            System.out.println("server is running...");
            for (;;) {
                Socket sock = ss.accept();
                System.out.println("connected from " + sock.getRemoteSocketAddress());
                Thread t = new Handler(sock);
                t.start();
            }
        }
    }

    class Handler extends Thread {
        Socket sock;

        public Handler(Socket sock) {
            this.sock = sock;
        }

        @Override
        public void run() {
            try (InputStream input = this.sock.getInputStream()) {
                try (OutputStream output = this.sock.getOutputStream()) {
                    handle(input, output);
                }
            } catch (Exception e) {
                try {
                    this.sock.close();
                } catch (IOException ioe) {
                }
                System.out.println("client disconnected.");
            }
        }

        private void handle(InputStream input, OutputStream output) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
            // 处理HTTP请求
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        System.out.println("Process new http request...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        // 读取HTTP请求:
        boolean requestOk = false;
        String first = reader.readLine();
        if (first.startsWith("GET / HTTP/1.")) {
            requestOk = true;
        }
        for (;;) {
            String header = reader.readLine();
            // 读取到空行时, HTTP Header读取完毕
            if (header.isEmpty()) {
                break;
            }
            System.out.println(header);
        }
        System.out.println(requestOk ? "Response OK" : "Response Error");
        if (!requestOk) {
            // 发送错误响应:
            writer.write("404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            // 发送成功响应:
            String data = "<html><body><h1>Hello, world!</h1></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            // 空行标识Header和Body的分隔
            writer.write("\r\n");
            writer.write(data);
            writer.flush();
        }
    }
}
