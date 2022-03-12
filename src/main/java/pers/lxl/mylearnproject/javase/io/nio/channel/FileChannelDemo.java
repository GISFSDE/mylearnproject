package pers.lxl.mylearnproject.javase.io.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        // 读
//        需要通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例
        RandomAccessFile aFile = new RandomAccessFile("src/main/resources/fileTest.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
//        获取当前通道位置
        long pos = inChannel.position();
//        设置当前通道位置
        inChannel.position(pos + 6);
//        返回通道当前大小
        System.out.println("此时通道大小："+inChannel.size());
//        截取(删除)文件7字节后部分
        inChannel.truncate(7);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("读取： " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        //强制将通道里的剩余数据写到磁盘中
        inChannel.force(true);
        aFile.close();
        System.out.println("操作结束");

        //覆盖起始位置开始的对应位置 写
        RandomAccessFile aFile1 = new RandomAccessFile("src/main/resources/fileTest.txt", "rw");
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf1 = ByteBuffer.allocate(48);
        FileChannel inChannel1 = aFile1.getChannel();
        buf1.clear();
        buf1.put(newData.getBytes());
        buf1.flip();
        while (buf1.hasRemaining()) {
            inChannel1.write(buf1);
        }
        inChannel1.close();


        RandomAccessFile aaFile = new RandomAccessFile("src/main/resources/fileTest.txt", "rw");
        FileChannel fromChannel = aaFile.getChannel();
        RandomAccessFile bbFile = new RandomAccessFile("src/main/resources/transferFrom.txt", "rw");
        FileChannel toChannel = bbFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        //        fromChannel to toChannel
        toChannel.transferFrom(fromChannel, position, count);
        //        fromChannel to toChannel
        fromChannel.transferTo(position, count, toChannel);
        aaFile.close();
        bbFile.close();
        System.out.println("通道传输over!");
    }
}
