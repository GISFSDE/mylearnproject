package pers.lxl.mylearnproject.javase.io.nio.pipe;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeDemo {

//        1.获取管道
//        2获取sink通道
//        3.创建缓冲区
//        4.写入数据
//        5.获取source通道
//        6.创建source通道
//        7.创建缓冲区，读取数据
//        8.关闭通道
    /**/
    @Test
    public void testPipe() throws IOException {
        // 1、获取通道
        Pipe pipe = Pipe.open();
        // 2、获取sink管道，用来传送数据
        Pipe.SinkChannel sinkChannel = pipe.sink();
        // 3、申请一定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("atguigu".getBytes());
        byteBuffer.flip();
        // 4、sink发送数据
        sinkChannel.write(byteBuffer);
        // 5、创建接收pipe数据的source管道
        Pipe.SourceChannel sourceChannel = pipe.source();
        // 6、接收数据，并保存到缓冲区中
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
        int length = sourceChannel.read(byteBuffer2);
        System.out.println(new String(byteBuffer2.array(), 0, length));
        sourceChannel.close();
        sinkChannel.close();
    }
}
