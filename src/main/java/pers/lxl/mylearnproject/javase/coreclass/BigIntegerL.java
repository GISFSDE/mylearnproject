package pers.lxl.mylearnproject.javase.coreclass;

import java.math.BigInteger;

/*CPU原生提供的整型最大范围是64位long型整数
 * 如果我们使用的整数范围超过了long型怎么办？这个时候，就只能用软件来模拟一个大整数。
 * java.math.BigInteger就是用来表示任意大小的整数。
 * BigInteger内部用一个int[]数组来模拟一个非常大的整数：*/
public class BigIntegerL {
    public static void main(String[] args) {
        BigInteger bi = new BigInteger("12131231231233");
        BigInteger bi1 = new BigInteger("121321211111111");
        System.out.println(bi.pow(5));
        BigInteger sum = bi.add(bi1);//BigInteger做运算的时候，只能使用实例方法,和long型整数运算比，BigInteger不会有范围限制，但缺点是速度比较慢。
        /*转换为byte：byteValue()
         转换为short：shortValue()
         转换为int：intValue()
         转换为long：longValue()
         转换为float：floatValue()
         转换为double：doubleValue()
         通过上述方法，可以把BigInteger转换成基本类型。
         如果BigInteger表示的范围超过了基本类型的范围，
         转换时将丢失高位信息，即结果不一定是准确的。如果需要准确地转换成基本类型，
         可以使用intValueExact()、longValueExact()等方法，在转换时如果超出范围，
         将直接抛出ArithmeticException异常*/
    }
}
