package pers.lxl.mylearnproject.javase.coreclass;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalL {
    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal("4264.24");
        BigDecimal bd2 = new BigDecimal("2132.1200");
        BigDecimal bd3 = new BigDecimal("213200");
        System.out.println(bd1.scale());//scale()表示小数位数
        System.out.println(bd2.scale());
        System.out.println(bd3.scale());
        BigDecimal d1=bd1.stripTrailingZeros();//stripTrailingZeros()方法，可以将一个BigDecimal格式化为一个相等的，但去掉了末尾0的BigDecimal
        BigDecimal d2=bd2.stripTrailingZeros();
        BigDecimal d3=bd3.stripTrailingZeros();
        System.out.println(d1.scale());//scale()返回负数，例如，-2，表示这个数是个整数，并且末尾有2个0
        System.out.println(d2.scale());
        System.out.println(d3.scale());
        //除法时，存在无法除尽的情况，这时，就必须指定精度以及如何进行截断,还可同事求余数
        BigDecimal bd4 = bd1.setScale(4, RoundingMode.HALF_UP);//四舍五入
        BigDecimal bd5 = bd1.setScale(4, RoundingMode.DOWN);//直接截断
        //divideAndRemainder()方法时，返回的数组包含两个BigDecimal，分别是商和余数，
        // 其中商总是整数，余数不会大于除数。我们可以利用这个方法判断两个BigDecimal是否是整数倍数
        BigDecimal[] dr = bd1.divideAndRemainder(bd2);
        if (dr[1].signum()==0)
        {
            System.out.println("bd1是bd2的整"+dr[0]+"倍");
        }

        //在比较两个BigDecimal的值是否相等时，要特别注意，使用equals()方法不但要求两个BigDecimal的值相等，还要求它们的scale()相等
        //必须使用compareTo()方法来比较，它根据两个值的大小分别返回负数、正数和0，分别表示小于、大于和等于

    }
}
