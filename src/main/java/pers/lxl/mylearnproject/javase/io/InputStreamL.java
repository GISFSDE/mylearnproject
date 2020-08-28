package pers.lxl.mylearnproject.javase.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/*InputStream并不是一个接口，而是一个抽象类，它是所有输入流的超类。这个抽象类定义的一个最重要的方法就是int read()，签名如下：
public abstract int read() throws IOException;
这个方法会读取输入流的下一个字节，并返回字节表示的int值（0~255）。如果已读到末尾，返回-1表示不能继续读取了。*/
public class InputStreamL {
    public static void main(String[] args) throws IOException {
        // 创建一个FileInputStream对象:
        InputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\lixianglun\\fileTest.txt");
        for (; ; ) {//死循环，比while（true）更好，因为编译后指令更少，而且没有判断跳转，更加简洁
            int n = inputStream.read(); // 反复调用read()方法，直到返回-1
            if (n == -1) {
                break;
            }
            System.out.println(n); // 打印byte的值
        }
        inputStream.close(); // 关闭流释放对应的底层资源，便让操作系统把资源释放掉，否则，应用程序占用的资源会越来越多，不但白白占用内存，还会影响其他应用程序的运行。
//        利用缓冲区一次读取多个字节
        try (InputStream input = new FileInputStream("..\\fileTest.txt")) {
            // 定义1000个字节大小的缓冲区:
            byte[] buffer = new byte[1000];
            int n;
            while ((n = input.read(buffer)) != -1) { // 读取到缓冲区,read()方法是阻塞的（Blocking），必须等待read()方法返回才能执行下一行代码
                System.out.println("read " + n + " bytes.");
            }
        }

    }
}
