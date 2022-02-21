package pers.lxl.mylearnproject.javase.io.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {
//服务端
    @Test
    public void ServerDemo() throws Exception {
//      1.获取服务端通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
//        绑定端口号
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
//        2.切换非阻塞模式
        ssc.configureBlocking(false);
//        获取选择器
        Selector selector = Selector.open();
        // 注册 channel，并且指定感兴趣的事件是 Accept
        ssc.register(selector, SelectionKey.OP_ACCEPT);
//        3.创建buffer
        ByteBuffer readBuff = ByteBuffer.allocate(1024);
        ByteBuffer writeBuff = ByteBuffer.allocate(128);
        writeBuff.put("received".getBytes());
        writeBuff.flip();
        while (true) {
            int nReady = selector.select();
//            遍历选择器
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();

                it.remove();
//                判断状态
                if (key.isAcceptable()) {
//              创建新的连接，并且把连接注册到selector上，进行监听，声明这个channel只对读操作感兴趣。
                    SocketChannel socketChannel = ssc.accept();
//                    切换到非阻塞
                    socketChannel.configureBlocking(false);
//                    注册
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
//                    获取通道
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuff.clear();
                    socketChannel.read(readBuff);
                    readBuff.flip();
                    System.out.println("received : " + new String(readBuff.array()));
                    key.interestOps(SelectionKey.OP_WRITE);
                } else if (key.isWritable()) {
                    writeBuff.rewind();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.write(writeBuff);
                    key.interestOps(SelectionKey.OP_READ);
                }
            }
        }

    }
//客户端代码
    @Test
    public void ClientDemo() {
        try {
//            1.获取通道，绑定主机与端口号
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
//            2.切换到非阻塞模式
            socketChannel.configureBlocking(false);
//            3.创建buffer
            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);
//            4.写入buffer数据
            writeBuffer.put("hello".getBytes());
//            5.模式切换
            writeBuffer.flip();
            while (true) {
                // 将position设回0
                writeBuffer.rewind();
//                6.写入通道
                socketChannel.write(writeBuffer);
//                7.关闭
                readBuffer.clear();
                socketChannel.read(readBuffer);
            }
        } catch (IOException e) {
        }
    }
}
