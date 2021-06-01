package pers.lxl.mylearnproject.javase.io.io.bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**InputStream并不是一个接口，而是一个抽象类，它是所有输入流的超类。这个抽象类定义的一个最重要的方法就是int read()，签名如下：
public abstract int read() throws IOException;
这个方法会读取输入流的下一个字节，并返回字节表示的int值（0~255）。如果已读到末尾，返回-1表示不能继续读取了。
 * @author lxl*/
public class InputStreamL {
    public static void main(String[] args) throws IOException {
        //inputStream字节输入流，抽象类，只提供方法声明，不提供具体实现
        // 创建一个FileInputStream对象:
        InputStream inputStream = new FileInputStream("fileTest.txt");
        for (; ; ) {//死循环，比while（true）更好，因为编译后指令更少，而且没有判断跳转，更加简洁
            // 反复调用read()方法，直到返回-1
            int n = inputStream.read();
            if (n == -1) {
                break;
            }
            // 打印byte的值
            System.out.println(n);
        }
        inputStream.close(); // 关闭流释放对应的底层资源，便让操作系统把资源释放掉，否则，应用程序占用的资源会越来越多，不但白白占用内存，还会影响其他应用程序的运行。
//        利用缓冲区一次读取多个字节
        try (InputStream input = new FileInputStream("fileTest.txt")) {
            // 定义1000个字节大小的缓冲区:
            byte[] buffer = new byte[1000];
            int n;
            // 读取到缓冲区,read()方法是阻塞的（Blocking），必须等待read()方法返回才能执行下一行代码
            while ((n = input.read(buffer)) != -1) {
                System.out.println("read " + n + " bytes.");
            }
        }


        try {
            //准备文件lol.txt其中的内容是AB，对应的ASCII分别是65 66
            File f =new File("fileTest.txt");
            //创建基于文件的输入流
            FileInputStream fis =new FileInputStream(f);
            //创建字节数组，其长度就是文件的长度
            byte[] all =new byte[(int) f.length()];
            //以字节流的形式读取文件所有内容
            fis.read(all);
            for (byte b : all) {
                //打印出来是65 66
                System.out.println(b);
            }

            //每次使用完流，都应该进行关闭
            fis.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
/*装饰者模式
Java I/O 使用了装饰者模式来实现。以 InputStream 为例，

InputStream 是抽象组件；
FileInputStream 是 InputStream 的子类，属于具体组件，提供了字节流的输入操作；
FilterInputStream 属于抽象装饰者，装饰者用于装饰组件，为组件提供额外的功能。例如 BufferedInputStream 为 FileInputStream 提供缓存的功能。

实例化一个具有缓存功能的字节流对象时，只需要在 FileInputStream 对象上再套一层 BufferedInputStream 对象即可。

FileInputStream fileInputStream = new FileInputStream(filePath);
BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

DataInputStream 装饰者提供了对更多数据类型进行输入的操作，比如 int、double 等基本类型。*/