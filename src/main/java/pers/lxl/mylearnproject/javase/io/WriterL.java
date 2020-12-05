package pers.lxl.mylearnproject.javase.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterL {
    public static void main(String[] args) {
        // 准备文件lol2.txt
        File f = new File("fileTest.txt");
        // 创建基于文件的Writer
        try (
                FileWriter fr = new FileWriter(f)) {
            // 以字符流的形式把数据写入到文件中
            String data = "abcdefg1234567890";
            char[] cs = data.toCharArray();
            fr.write(cs);

        } catch (
                IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
