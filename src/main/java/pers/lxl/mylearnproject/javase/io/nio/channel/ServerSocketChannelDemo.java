package pers.lxl.mylearnproject.javase.io.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
//http://localhost:1234/
public class ServerSocketChannelDemo {

    public static final String GREETING = "Hello java nio.\r\n";

    public static void main(String[] argv) throws Exception {
        int port = 1234; // default
        if (argv.length > 0) {
            port = Integer.parseInt(argv[0]);
        }
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
//        打开
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        while (true) {
            System.out.println("Waiting for connections");
//            监听新的链接，阻塞会在此阻塞住进程非阻塞会返回null
            SocketChannel sc = ssc.accept();
            if (sc == null) {
                System.out.println("null");
                Thread.sleep(2000);
            } else {
                System.out.println("Incoming connection from: " + sc.socket().getRemoteSocketAddress());
//                指针指向0
                buffer.rewind();
                sc.write(buffer);
//                关闭
                sc.close();
            }
        }
    }
}