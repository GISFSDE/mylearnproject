package pers.lxl.mylearnproject.javase.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class SystemIn {
    public static void main(String[] args) {
        // 控制台输入
        try (InputStream is = System.in;) {
            while (true) {
                // 敲入a,然后敲回车可以看到
                // 97 13 10
                // 97是a的ASCII码
                // 13 10分别对应回车换行
                int i = is.read();//单个读取
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner s = new Scanner(System.in);//逐行读取

        while (true) {
//Scanner读取字符串
            String line = s.nextLine();
            System.out.println(line);

//Scanner从控制台读取整数
            int a = s.nextInt();
            System.out.println("第一个整数：" + a);
            int b = s.nextInt();
            System.out.println("第二个整数：" + b);

        }
    }
}
