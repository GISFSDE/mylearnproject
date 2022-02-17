package pers.lxl.mylearnproject.javase.io.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));

        SocketChannel socketChanne2 = SocketChannel.open();
        socketChanne2.connect(new InetSocketAddress("www.baidu.com", 80));
//        连接校验
        socketChannel.isOpen(); // 测试SocketChannel是否为open状态
        socketChannel.isConnected(); //测试SocketChannel是否已经被连接
        socketChannel.isConnectionPending(); //测试SocketChannel是否正在进行连接
        socketChannel.finishConnect(); //校验正在进行套接字连接的SocketChannel是否已经完成连接
//        读写模式 前面提到SocketChannel支持阻塞和非阻塞两种模式：,false为非阻塞
        socketChannel.configureBlocking(false);

//        通过setOptions方法可以设置socket套接字的相关参数
        socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, Boolean.TRUE).setOption(StandardSocketOptions.TCP_NODELAY, Boolean.TRUE);
//        可以通过getOption获取相关参数的值。如默认的接收缓冲区大小是8192byte。
        socketChannel.getOption(StandardSocketOptions.SO_KEEPALIVE);
        socketChannel.getOption(StandardSocketOptions.SO_RCVBUF);
//        读写
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");
    }
}
