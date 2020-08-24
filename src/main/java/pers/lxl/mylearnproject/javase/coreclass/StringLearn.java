package pers.lxl.mylearnproject.javase.coreclass;

import java.util.Arrays;

public class StringLearn {
    public static void main(String[] args) {
        /** The value is used for character storage. */
//    private final char value[];源代码存储方式，不可变，String为引用类型
        String s1 = "ST,RING";
        String s2="string".toUpperCase();
        System.out.println(s1==s2);//比较对象
        System.out.println(s1.equals(s2));//比较内容
        System.out.println("wd".contains("w"));
        System.out.println("Hello".indexOf("l"));
        System.out.println("Hello".lastIndexOf("l"));
        System.out.println("Hello".startsWith("He") );
        System.out.println("Hello".endsWith("lo"));
        System.out.println("Hello".substring(2));//索引号是从0开始的
        System.out.println("Hello".substring(2, 4));
        System.out.println("  \tHello\r\n ".trim());
        System.out.println("  ".isEmpty());
        System.out.println(s1.replace('S', 'w'));
        System.out.println(s1.replaceAll("[\\,\\;\\s]+", ","));
        String[] ss = s1.split("\\,");
        System.out.println(Arrays.toString(ss));
        String[] ss2 = {"zce", "dwd", "wd"};
        String s = String.join("***", ss2);
        System.out.println(s);
        String ssss=String.valueOf(123.32);//把任意基本类型或引用类型转换为字符串
        System.out.println(ssss);
        int n2 = Integer.parseInt("ff", 16); // 按十六进制转换，255
        System.out.println(n2);
        char[] cs = "Hello".toCharArray(); // String -> char[]
        String sss = new String(cs); // char[] -> String
    }


}
