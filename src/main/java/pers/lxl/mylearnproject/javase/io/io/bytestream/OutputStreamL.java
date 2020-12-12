package pers.lxl.mylearnproject.javase.io.io.bytestream;

import java.io.*;

/**为什么要有flush()？因为向磁盘、网络写入数据的时候，出于效率的考虑，
操作系统并不是输出一个字节就立刻写入到文件或者发送到网络，
而是把输出的字节先放到内存的一个缓冲区里（本质上就是一个byte[]数组），
等到缓冲区写满了，再一次性写入文件或者网络。对于很多IO设备来说，
一次写一个字节和一次写1000个字节，花费的时间几乎是完全一样的，
所以OutputStream有个flush()方法，能强制把缓冲区内容输出。
通常情况下，我们不需要调用这个flush()方法，因为缓冲区写满了OutputStream会自动调用它
并且，在调用close()方法关闭OutputStream之前，也会自动调用flush()方法。
特别情况，缓冲区过大，需马上发送的时候手动flush
 * @author lxl*/
public class OutputStreamL {
    /**实现文件复制*/
    public static void copyFile(String src, String dist) throws IOException {
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dist);

        byte[] buffer = new byte[20 * 1024];
        int cnt;

        // read() 最多读取 buffer.length 个字节
        // 返回的是实际读取的个数
        // 返回 -1 的时候表示读到 eof，即文件尾
        while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, cnt);
        }

        in.close();
    }
    public static void main(String[] args) throws IOException {
        //文件存在则覆盖，不存在创建
        try(OutputStream output = new FileOutputStream("F:\\PROJECT\\IDEA\\mylearnproject\\src\\main\\resources\\fileTest.txt")){
            byte[] data = {34, 35};
        // H
        output.write(72);
        // e
        output.write(101);
        // l
        output.write(108);
        // l
        output.write(108);
        // o
        output.write(data);
//        output.write(111); // o
        // 一次性写入若干字节
        output.write("Hello瓦达无多".getBytes("UTF-8"));
            // }
        output.close();//try之后编译器在此自动为我们写入finally并调用close()
    }
}}
