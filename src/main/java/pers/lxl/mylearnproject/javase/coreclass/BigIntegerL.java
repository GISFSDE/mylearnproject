package pers.lxl.mylearnproject.javase.coreclass;

import java.math.BigInteger;

/**CPU原生提供的整型最大范围是64位long型整数
 * 如果我们使用的整数范围超过了long型怎么办？这个时候，就只能用软件来模拟一个大整数。
 * java.math.BigInteger就是用来表示任意大小的整数。
 * BigInteger内部用一个int[]数组来模拟一个非常大的整数：
 * @author Administrator*/
public class BigIntegerL {
    public static void main(String[] args) {
        BigInteger bi = new BigInteger("12131231231233");
        BigInteger bi1 = new BigInteger("121321211111111");
        System.out.println(bi.pow(5));
        //BigInteger做运算的时候，只能使用实例方法,和long型整数运算比，BigInteger不会有范围限制，但缺点是速度比较慢。
        BigInteger sum = bi.add(bi1);
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
         将直接抛出ArithmeticException异常
         如果BigInteger的值甚至超过了float的最大范围（3.4x1038），那么返回的float为Infinity
         其他常用方法：

        BigInteger abs()  返回大整数的绝对值
        BigInteger add(BigInteger val) 返回两个大整数的和
        BigInteger and(BigInteger val)  返回两个大整数的按位与的结果
        BigInteger andNot(BigInteger val) 返回两个大整数与非的结果
        BigInteger divide(BigInteger val)  返回两个大整数的商
        BigInteger gcd(BigInteger val)  返回大整数的最大公约数
        BigInteger max(BigInteger val) 返回两个大整数的最大者
        BigInteger min(BigInteger val) 返回两个大整数的最小者
        BigInteger mod(BigInteger val) 用当前大整数对val求模
        BigInteger multiply(BigInteger val) 返回两个大整数的积
        BigInteger negate() 返回当前大整数的相反数
        BigInteger not() 返回当前大整数的非
        BigInteger or(BigInteger val) 返回两个大整数的按位或
        BigInteger pow(int exponent) 返回当前大整数的exponent次方
        BigInteger remainder(BigInteger val) 返回当前大整数除以val的余数
        BigInteger leftShift(int n) 将当前大整数左移n位后返回
        BigInteger rightShift(int n) 将当前大整数右移n位后返回
        BigInteger subtract(BigInteger val)返回两个大整数相减的结果
        byte[] toByteArray(BigInteger val)将大整数转换成二进制反码保存在byte数组中
        String toString() 将当前大整数转换成十进制的字符串形式
        BigInteger xor(BigInteger val) 返回两个大整数的异或
         */

    }
}
