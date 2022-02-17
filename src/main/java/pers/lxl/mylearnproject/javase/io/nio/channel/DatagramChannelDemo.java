package pers.lxl.mylearnproject.javase.io.nio.channel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class DatagramChannelDemo {
    /**
     * 发包的datagram * * @throws IOException * @throws InterruptedException
     */
    @Test
    public void sendDatagram() throws IOException, InterruptedException {
        DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1", 9999);
        while (true) {
//            发送
            sendChannel.send(ByteBuffer.wrap("发包".getBytes("UTF-8")), sendAddress);
            System.out.println("发包端发包");
            Thread.sleep(1000);
        }
    }

    /**
     * 收包端 * * @throws IOException
     */
    @Test
    public void receive() throws IOException {
        DatagramChannel receiveChannel = DatagramChannel.open();
        InetSocketAddress receiveAddress = new InetSocketAddress(9999);
        receiveChannel.bind(receiveAddress);
        ByteBuffer receiveBuffer = ByteBuffer.allocate(512);
        while (true) {
            receiveBuffer.clear();
//            接收
            SocketAddress sendAddress = receiveChannel.receive(receiveBuffer);
            receiveBuffer.flip();
            System.out.print(sendAddress.toString() + " ");
            System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));
        }
    }

    /**
     * 只接收和发送9999的数据包 * * @throws IOException
     */
    @Test
    public void testConect1() throws IOException {
        DatagramChannel connChannel = DatagramChannel.open();
        connChannel.bind(new InetSocketAddress(9999));
        connChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        connChannel.write(ByteBuffer.wrap("发包".getBytes("UTF-8")));
        ByteBuffer readBuffer = ByteBuffer.allocate(512);
        while (true) {
            try {
                readBuffer.clear();
                connChannel.read(readBuffer);
                readBuffer.flip();
                System.out.println(Charset.forName("UTF-8").decode(readBuffer));
            } catch (Exception e) {
            }
        }

    }
}
