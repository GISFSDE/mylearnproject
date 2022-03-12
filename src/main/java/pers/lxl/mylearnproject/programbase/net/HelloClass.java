package pers.lxl.mylearnproject.programbase.net;





import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * 计算机网络接入互联网，就必须使用TCP/IP协议。
 * 一个IP地址用于唯一标识一个网络接口（Network Interface）
 * 一台联入互联网的计算机肯定有一个IP地址，但也可能有多个IP地址。
 * IP地址分为IPv4和IPv6两种。IPv4采用32位地址，类似101.202.99.12，
 * 而IPv6采用128位地址，类似2001:0DA8:100A:0000:0000:1020:F2F3:1428。
 * IPv4地址总共有232个（大约42亿），而IPv6地址则总共有2128个（大约340万亿亿亿亿），
 * IPv4的地址目前已耗尽，而IPv6的地址是根本用不完的。
 * IP地址又分为公网IP地址和内网IP地址。公网IP地址可以直接被访问，内网IP地址只能在内网访问。内网IP地址类似于：
 * 192.168.x.x/10.x.x.x
 * 有一个特殊的IP地址，称之为本机地址，它总是127.0.0.1。
 * 一网卡，一本机地址，一ip地址（接入网络）
 * IP = 101.202.99.2
 * Mask = 255.255.255.0
 * Network = IP & Mask = 101.202.99.0
 * 同网络（ip前段即网络号同）可直接通信，不同需通过路由器或者交换机这些设备间接通信，这种设备叫做网关
 * 网关的作用就是连接多个网络，负责把来自一个网络的数据包发到另一个网络，这个过程叫路由。
 * （域名）nslookup查看，因为直接记忆IP地址非常困难，所以我们通常使用域名访问某个特定的服务。
 * 域名解析服务器DNS负责把域名翻译成对应的IP，客户端再根据IP地址访问服务器。
 * （网络模型OSI（Open System Interconnect））
 * <p>
 * 应用层：提供应用程序之间的通信；
 * 表示层：处理数据格式，加解密等等；
 * 会话层：负责建立和维护会话；
 * 传输层：负责提供端到端的可靠传输；
 * 网络层：负责根据目标地址选择路由来传输数据；
 * 链路层和物理层：负责把数据进行分片并且真正通过物理网络传输，例如，无线网、光纤等。
 * <p>
 * （TCP/IP）模型并不是对应到OSI的7层模型，而是大致对应OSI的5层模型：
 * OSI         TCP/IP        五层协议
 * 应用层	      应用层        应用层
 * 表示层
 * 会话层
 * 传输层	     传输层        传输层
 * 网络层	     IP层          网络层
 * 数据链路层	网络接口层   数据链路层
 * 物理层                   物理层
 * <p>
 * （常用协议）
 * IP协议是一个分组交换，它不保证可靠传输。而TCP协议是传输控制协议，它是面向连接的协议，支持可靠传输和双向通信。
 * TCP协议是建立在IP协议之上的，简单地说，IP协议只负责发数据包，不保证顺序和正确性，而TCP协议负责控制数据包传输，
 * 它在传输数据之前需要先建立连接，建立连接后才能传输数据，传输完后还需要断开连接。TCP协议之所以能保证数据的可靠传输，
 * 是通过接收确认、超时重传这些机制实现的。并且，TCP协议允许双向通信，即通信双方可以同时发送和接收数据。
 * TCP协议也是应用最广泛的协议，许多高级协议都是建立在TCP协议之上的，例如HTTP、SMTP等。
 * UDP协议（User Datagram Protocol）是一种数据报文协议，它是无连接协议，不保证可靠传输。因为UDP协议在通信前不需要建立连接，
 * 因此它的传输效率比TCP高，而且UDP协议比TCP协议要简单得多。
 * 选择UDP协议时，传输的数据通常是能容忍丢失的，例如，一些语音视频通信的应用会选择UDP协议。
 */

public class HelloClass {
    @Test
    public void InetAddress() throws UnknownHostException {
//        查询本机地址
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        InetAddress inetAddress1 = InetAddress.getByName("localhost");
        InetAddress inetAddress2 = InetAddress.getLocalHost();
//        /127.0.0.1
//localhost/127.0.0.1
//BF-202103261718/126.10.1.1
        System.out.println(inetAddress);
        System.out.println(inetAddress1);
        System.out.println(inetAddress2);

//        查询网站ip地址  www.baidu.com/182.61.200.6
        InetAddress inetAddress3 = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress3);

//      常用方法
//182.61.200.6
//182.61.200.6
//www.baidu.com
        System.out.println(inetAddress3.getCanonicalHostName());
        System.out.println(inetAddress3.getHostAddress());
        System.out.println(inetAddress3.getHostName());

        InetSocketAddress inetSocketAddress=new InetSocketAddress("localhost", 8080);
        System.out.println(inetSocketAddress.getAddress());
        System.out.println(inetSocketAddress.getHostName());
        System.out.println(inetSocketAddress.getPort());
        System.out.println(inetSocketAddress.getHostString());
    }



}
