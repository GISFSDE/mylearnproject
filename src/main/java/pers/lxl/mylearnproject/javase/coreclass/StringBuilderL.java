package pers.lxl.mylearnproject.javase.coreclass;

/*
 * String使用+拼接字符串，如果在循环中，在循环中，
 * 每次循环都会创建新的字符串对象，然后扔掉旧的字符串。
 * 这样，绝大部分字符串都是临时对象，不但浪费内存，还会影响GC效率
 * 为高效拼接字符串Java标准库提供了StringBuilder，它是一个可变对象，可以预分配缓冲区
 * 对于普通的字符串+操作，并不需要我们将其改写为StringBuilder，
 * 因为Java编译器在编译时就自动把多个连续的+操作编码为StringConcatFactory的操作。
 * 在运行期，StringConcatFactory会自动把字符串连接操作优化为数组复制或者StringBuilder操作
 * StringBuffer通过同步来保证多个线程操作StringBuffer也是安全的，但是同步会带来执行速度的下降
 * StringBuilder和StringBuffer接口完全相同，现在完全没有必要使用StringBuffer*/
public class StringBuilderL {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer(1024);
        for (int i = 0; i < 1000; i++) {
            sb.append('s');
            sb.append(i);
        }
        sb.append("good ")
                .append("LXL")
                .insert(0, "DD");//链式操作
        String s = sb.toString();
        System.out.println(s);
    }
}
