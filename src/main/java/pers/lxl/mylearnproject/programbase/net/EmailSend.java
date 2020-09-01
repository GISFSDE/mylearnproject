package pers.lxl.mylearnproject.programbase.net;
/*邮件软件MUA：Mail User Agent
* 邮件服务器则称为MTA：Mail Transfer Agent(邮件中转的代理)
*   MDA：Mail Delivery Agent，意思是邮件到达的代理
* MUA到MTA发送邮件的协议就是SMTP协议，它是Simple Mail Transport Protocol的缩写，使用标准端口25，也可以使用加密端口465或587。
SMTP协议是一个建立在TCP之上的协议，任何程序发送邮件都必须遵守SMTP协议。使用Java程序发送邮件时，我们无需关心SMTP协议的底层原理，
* 只需要使用JavaMail这个标准API就可以直接发送邮件。*/
//MUA--》MTA--》MTA--》MDA《--MUA
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Properties;
/*假设我们准备使用自己的邮件地址me@example.com给小明发送邮件，
已知小明的邮件地址是xiaoming@somewhere.com，发送邮件前，我们首先要确定作为MTA的邮件服务器地址和端口号。邮件服务器地址通常是smtp.example.com，端口号由邮件服务商确定使用25、465还是587。以下是一些常用邮件服务商的SMTP信息：
QQ邮箱：SMTP服务器是smtp.qq.com，端口是465/587；
163邮箱：SMTP服务器是smtp.163.com，端口是465；
Gmail邮箱：SMTP服务器是smtp.gmail.com，端口是465/587。
有了SMTP服务器的域名和端口号，我们还需要SMTP服务器的登录信息，通常是使用自己的邮件地址作为用户名，登录口令是用户口令或者一个独立设置的SMTP口令。
我们来看看如何使用JavaMail发送邮件。*/
public class EmailSend {
    public static void main(String[] args) throws MessagingException, IOException {
        //    1.准备SMTP登录信息
        // 服务器地址:
        String smtp = "smtp.qq.com";
        // 登录用户名:
        String username = "714416426@qq.com";
        // 登录口令:
        String password = "anxbb0724";
        // 连接到SMTP服务器587端口:
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp); // SMTP主机名
        props.put("mail.smtp.port", "587"); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
// 获取Session实例:
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
// 设置发送方地址:
        message.setFrom(new InternetAddress("714416426@qq.com"));
// 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("228817112@qq.com"));
// 设置邮件主题:
        message.setSubject("Hello", "UTF-8");
// 设置邮件正文:发送HTML邮件：message.setText(body, "UTF-8", "html");
        message.setText("Hi Xiaoming...", "UTF-8");
//发送附件
        // 不能直接调用message.setText()方法，要构造一个Multipart对象：
        Multipart multipart = new MimeMultipart();
// 添加text:
        BodyPart textpart = new MimeBodyPart();
        textpart.setContent("Hi BB", "text/html;charset=utf-8");
        //发送内嵌图片的HTML邮件
        textpart.setContent("<h1>Hello</h1><p><img src=\"cid:img01\"></p>", "text/html;charset=utf-8");
        multipart.addBodyPart(textpart);
// 添加image:
        BodyPart imagepart = new MimeBodyPart();
        imagepart.setFileName("微信图片_20200821174645.jpg");
        imagepart.setDataHandler(new DataHandler(new ByteArrayDataSource("C:\\Users\\Administrator\\Desktop\\lixianglun\\微信图片_20200821174645.jpg", "application/octet-stream")));
        // 与HTML的<img src="cid:img01">关联:
        imagepart.setHeader("Content-ID", "<img01>");
        multipart.addBodyPart(imagepart);
// 设置邮件内容为multipart:
        message.setContent(multipart);
// 发送:
        Transport.send(message);
// 设置debug模式便于调试:
        session.setDebug(true);
    }


}
