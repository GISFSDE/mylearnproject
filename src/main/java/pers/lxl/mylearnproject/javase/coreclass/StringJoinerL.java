package pers.lxl.mylearnproject.javase.coreclass;

import java.util.StringJoiner;

public class StringJoinerL {
    public static void main(String[] args) {
        String[] names = {"Bob", "Wb", "DA"};
        StringJoiner sj = new StringJoiner(", ","Hello ","!");//分隔符拼接数组,指定开头结尾
        for (String name:names
             ) {
            sj.add(name);
        }
        System.out.println(sj);
        System.out.println(sj.toString());
        String s = String.join(", ", names);//String.join内部默认使用StringJoiner，不需要指定开头结尾时使用更方便
        System.out.println(s);
    }
}
