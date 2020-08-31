package pers.lxl.mylearnproject.javase.regularexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*为每一种字符写逻辑判断太麻烦，所以有了
* 正则表达式
* 是一套标准，用于任何语言
*
* .匹配一个字符且仅限一个字符
* \d匹配0~9
* \w可以匹配一个字母、数字或下划线
* \s可以匹配一个空格字符，tab字符（在Java中用\t表示）
* \d可以匹配一个数字
* \D则匹配一个非数字
* *可以匹配任意个字符
* +可以匹配至少一个字符
* ?可以匹配0个或一个字符
* {n}指定n个字符
* {n,m}指定匹配n~m个字符
* {n,}就可以匹配至少n个字符
* ^表示开头，$表示结尾
* [...]可以匹配范围内的字符，比如[1-9]
* [0-9a-fA-F]，它表示一共可以匹配。。
* [0-9a-fA-F]{6} 6位十六进制数
* 匹配任意字符，但不包括数字，可以写[^1-9]{3}
* |连接的两个正则规则是或规则
* learn\sjava|learn\sphp|learn\sgo，可以把公共部分提出来，然后用(...)把子规则括起来表示成learn\\s(java|php|go)
*
* */
public class HelloClass {

    public static void main(String[] args) {
        System.out.println(isValidNumber("11111111111"));

        //----分组匹配-------
            Pattern p = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
            Matcher m = p.matcher("010-12345678");
            if (m.matches()) {
                String g1 = m.group(1);//第一个子串
                String g2 = m.group(2);//第二个子串
                String all = m.group(0);//--全部---
                System.out.println(g1);
                System.out.println(g2);
                System.out.println(all);
            } else {
                System.out.println("匹配失败!");
            }


//        反复使用String.matches()对同一个正则表达式进行多次匹配效率较低，
//        因为每次都会创建出一样的Pattern对象。完全可以先创建出一个Pattern对象，
//        然后反复使用，就可以实现编译一次，多次匹配：
        Pattern pattern = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        pattern.matcher("010-12345678").matches(); // true

        //-----非贪婪匹配----
//        贪婪匹配：任何一个规则，它总是尽可能多地向后匹配，因此123000，用(\d+)(0*)匹配,\d+总是会把后面的0包含进来。
        //要让\d+尽量少匹配，让0*尽量多匹配，我们就必须让\d+使用非贪婪匹配。在规则\d+后面加个?即可表示非贪婪匹配。

        //----搜索和替换----
        //-分割字符串-
//        String.split()方法传入正则表达式。
        "a b c".split("\\s"); // { "a", "b", "c" }
//        -搜索字符串-
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern p1 = Pattern.compile("\\wo\\w");
        Matcher m1 = p1.matcher(s);
        while (m1.find()) {
            String sub = s.substring(m1.start(), m1.end());
            System.out.println(sub);
        }

        //-替换字符串-
        //String.replaceAll()，它的第一个参数是正则表达式，第二个参数是待替换的字符串
        String s1 = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        String r = s1.replaceAll("\\s+", " ");
        System.out.println(r); // "The quick brown fox jumps over the lazy dog."

        //-反向引用-
        String s2 = "the quick brown fox jumps over the lazy dog.";
        String r2 = s2.replaceAll("\\s([a-z]{4})\\s", " <b>$1</b> ");
        System.out.println(r2);//the quick brown fox jumps <b>over</b> the <b>lazy</b> dog.
    }
    static boolean isValidNumber(String s){
        return s.matches("\\d{11}");
    }




}
