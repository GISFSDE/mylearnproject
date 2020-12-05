package pers.lxl.mylearnproject.javase.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*为什么要有flush()？因为向磁盘、网络写入数据的时候，出于效率的考虑，
操作系统并不是输出一个字节就立刻写入到文件或者发送到网络，
而是把输出的字节先放到内存的一个缓冲区里（本质上就是一个byte[]数组），
等到缓冲区写满了，再一次性写入文件或者网络。对于很多IO设备来说，
一次写一个字节和一次写1000个字节，花费的时间几乎是完全一样的，
所以OutputStream有个flush()方法，能强制把缓冲区内容输出。
通常情况下，我们不需要调用这个flush()方法，因为缓冲区写满了OutputStream会自动调用它
并且，在调用close()方法关闭OutputStream之前，也会自动调用flush()方法。
特别情况，缓冲区过大，需马上发送的时候手动flush*/
public class OutputStreamL {
    public static void main(String[] args) throws IOException {
        try(OutputStream output = new FileOutputStream("F:\\PROJECT\\IDEA\\mylearnproject\\src\\main\\resources\\fileTest.txt")){//文件存在则覆盖，不存在创建
            byte data[] = {34, 35};
        output.write(72); // H
        output.write(101); // e
        output.write(108); // l
        output.write(108); // l
        output.write(data); // o
//        output.write(111); // o
        output.write("Hello瓦达无多".getBytes("UTF-8")); // 一次性写入若干字节
            // }
        output.close();//try之后编译器在此自动为我们写入finally并调用close()
    }
}}
