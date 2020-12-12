package pers.lxl.mylearnproject.javase.io.io.charstream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**---字符操作----
 * @author lxl*/
public class ReaderL {
        public static void main(String[] args) {
            // 准备文件lol.txt其中的内容是AB
            File f = new File("fileTest.txt");
            // 创建基于文件的Reader
            try (FileReader fr = new FileReader(f)) {
                // 创建字符数组，其长度就是文件的长度
                char[] all = new char[(int) f.length()];
                // 以字符流的形式读取文件所有内容
                fr.read(all);
                for (char b : all) {
                    // 打印出
                    System.out.println(b);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

/*
编码与解码

编码就是把字符转换为字节，而解码是把字节重新组合成字符。
如果编码和解码过程使用不同的编码方式那么就出现了乱码。

GBK 编码中，中文字符占 2 个字节，英文字符占 1 个字节；
UTF-8 编码中，中文字符占 3 个字节，英文字符占 1 个字节；
UTF-16be 编码中，中文字符和英文字符都占 2 个字节。
UTF-16be 中的 be 指的是 Big Endian，也就是大端。相应地也有 UTF-16le，le 指的是 Little Endian，也就是小端。

Java 的内存编码使用双字节编码 UTF-16be，这不是指 Java 只支持这一种编码方式，而是说 char 这种类型使用 UTF-16be 进行编码。char 类型占 16 位，也就是两个字节，Java 使用这种双字节编码是为了让一个中文或者一个英文都能使用一个 char 来存储。

String 的编码方式
String 可以看成一个字符序列，可以指定一个编码方式将它编码为字节序列，也可以指定一个编码方式将一个字节序列解码为 String。

String str1 = "中文";
byte[] bytes = str1.getBytes("UTF-8");
String str2 = new String(bytes, "UTF-8");
System.out.println(str2);
在调用无参数 getBytes() 方法时，默认的编码方式不是 UTF-16be。双字节编码的好处是可以使用一个 char 存储中文和英文，而将 String 转为 bytes[] 字节数组就不再需要这个好处，因此也就不再需要双字节编码。getBytes() 的默认编码方式与平台有关，一般为 UTF-8。

byte[] bytes = str1.getBytes();

InputStreamReader 实现从字节流解码成字符流；
OutputStreamWriter 实现字符流编码成为字节流。*/
