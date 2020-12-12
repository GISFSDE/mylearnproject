package pers.lxl.mylearnproject.javase.io.io.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**使用缓存流写出数据顶**/
public class PrintStreamAndPrintWriter {

    public static void main(String[] args) {
        // 向文件lol2.txt中写入三行语句
        File f = new File("fileTest.txt");

        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f);
                // 缓存流必须建立在一个存在的流的基础上
                PrintWriter pw = new PrintWriter(fw);
        ) {
            pw.println("garen kill teemo");
            //强制把缓存中的数据写入硬盘，无论缓存是否已满
            pw.flush();
            pw.println("teemo revive after 1 minutes");
            pw.println("teemo try to garen, but killed again");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
