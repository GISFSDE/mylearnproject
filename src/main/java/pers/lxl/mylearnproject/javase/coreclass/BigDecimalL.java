package pers.lxl.mylearnproject.javase.coreclass;

import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ROUND_CEILING;

/**
 * Java在java.math包中提供的API类BigDecimal，用来对超过16位有效位的数进行精确的运算。
 * 双精度浮点型变量double可以处理16位有效数。在实际应用中，需要对更大或者更小的数进行运算和处理。
 * float和double只能用来做科学计算或者是工程计算，在商业计算中要用java.math.BigDecimal。
 * BigDecimal所创建的是对象，我们不能使用传统的+、-、*、/等算术运算符直接对其对象进行数学运算，而必须调用其相对应的方法。
 * 方法中的参数也必须是BigDecimal的对象。构造器是类的特殊方法，专门用来创建对象，特别是带有参数的对象。
 *
 * @author Administrator
 *
 * 参考：https://blog.csdn.net/qq_35868412/article/details/89029288
 */
public class BigDecimalL {
    public static void main(String[] args) {
//        精度遗失问题
//        float 还是double都是浮点数，而计算机是二进制的，浮点数会失去一定的精确度，只能用来科学或工程计算，不能用来商业计算。根本原因是:十进制值通常没有完全相同的二进制表示形式;十进制数的二进制表示形式可能不精确。只能无限接近于那个值
//        0.19999999999999998
        System.out.println(0.3 - 0.1);
//       0.020000000000000004
        System.out.println(0.2 * 0.1);
//        0.30000000000000004
        System.out.println(0.2 + 0.1);
//        2.9999999999999996
        System.out.println(0.3 / 0.1);
//金融项目中需要准确计算，不能有精度遗失问题，所以商业使用需要用BigDecimal==============================


//BigDecimal(int)       创建一个具有参数所指定整数值的对象。
//BigDecimal(double)   创建一个具有参数所指定双精度值的对象。 //不推荐使用
//BigDecimal(long)    创建一个具有参数所指定长整数值的对象。
//BigDecimal(String) 创建一个具有参数所指定以字符串表示的数值的对象。//推荐使用
//add(BigDecimal)        BigDecimal对象中的值相加，然后返回这个对象。
//subtract(BigDecimal) BigDecimal对象中的值相减，然后返回这个对象。
//multiply(BigDecimal)  BigDecimal对象中的值相乘，然后返回这个对象。
//divide(BigDecimal)     BigDecimal对象中的值相除，然后返回这个对象。
//toString()                将BigDecimal对象的数值转换成字符串。
//doubleValue()          将BigDecimal对象中的值以双精度数返回。
//floatValue()             将BigDecimal对象中的值以单精度数返回。
//longValue()             将BigDecimal对象中的值以长整数返回。
//intValue()               将BigDecimal对象中的值以整数返回。
        BigDecimal bd1 = new BigDecimal("4264.24");
        BigDecimal bd2 = new BigDecimal("2132.1200");
        BigDecimal bd3 = new BigDecimal("213200");
        BigDecimal bd6 = new BigDecimal("4.0000");
        BigDecimal bd7 = new BigDecimal("3.0");
//        加
        System.out.println("加"+bd1.add(bd3));
//        减
        System.out.println("减"+bd1.subtract(bd1));
//        乘
        System.out.println("乘"+bd6.multiply(bd7));
//        除
//        不分配保留小数点保留位数的非整除运算会导致报错java.lang.ArithmeticException：Non-terminating decimal expansion; no exact representable decimal result.
//        System.out.println(bd1.divide(bd3));
//        多传入两个参数即可避免
//       divide(BigDecimal，保留小数点后几位小数，舍入模式)

//舍入模式
//ROUND_CEILING    //向正无穷方向舍入
//ROUND_DOWN    //向零方向舍入
//ROUND_FLOOR    //向负无穷方向舍入
//ROUND_HALF_DOWN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向下舍入, 例如1.55 保留一位小数结果为1.5
//ROUND_HALF_EVEN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP，如果是偶数，使用ROUND_HALF_DOWN
//ROUND_HALF_UP    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留一位小数结果为1.6,也就是我们常说的“四舍五入”
//ROUND_UNNECESSARY    //计算结果是精确的，不需要舍入模式
//ROUND_UP    //向远离0的方向舍入

        System.out.println("除"+bd6.divide(bd7,2,ROUND_CEILING));

        //scale()表示小数位数
        System.out.println(bd1.scale());
        System.out.println(bd2.scale());
        System.out.println(bd3.scale());
        //stripTrailingZeros()方法，可以将一个BigDecimal格式化为一个相等的，但去掉了末尾0的BigDecimal
        BigDecimal d1 = bd1.stripTrailingZeros();
        BigDecimal d2 = bd2.stripTrailingZeros();
        BigDecimal d3 = bd3.stripTrailingZeros();
        //scale()返回负数，例如，-2，表示这个数是个整数，并且末尾有2个0
        System.out.println(d1.scale());
        System.out.println(d2.scale());
        System.out.println(d3.scale());
        //除法时，存在无法除尽的情况，这时，就必须指定精度以及如何进行截断,还可同事求余数
        //四舍五入
        BigDecimal bd4 = bd1.setScale(4, RoundingMode.HALF_UP);
        //直接截断
        BigDecimal bd5 = bd1.setScale(4, RoundingMode.DOWN);
        //divideAndRemainder()方法时，返回的数组包含两个BigDecimal，分别是商和余数，
        // 其中商总是整数，余数不会大于除数。我们可以利用这个方法判断两个BigDecimal是否是整数倍数
        BigDecimal[] dr = bd1.divideAndRemainder(bd2);
        if (dr[1].signum() == 0) {
            System.out.println("bd1是bd2的整" + dr[0] + "倍");
        }

        //在比较两个BigDecimal的值是否相等时，要特别注意，使用equals()方法不但要求两个BigDecimal的值相等，还要求它们的scale()相等
        //必须使用compareTo()方法来比较，它根据两个值的大小分别返回负数、正数和0，分别表示小于、大于和等于


//        JDK的描述：1、参数类型为double的构造方法的结果有一定的不可预知性。有人可能认为在Java中写入newBigDecimal(0.1)所创建的BigDecimal正好等于 0.1（非标度值 1，其标度为 1），
//        但是它实际上等于0.1000000000000000055511151231257827021181583404541015625。这是因为0.1无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。
//        这样，传入到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
//        2、另一方面，String 构造方法是完全可预知的：写入 newBigDecimal("0.1") 将创建一个 BigDecimal，它正好等于预期的 0.1。因此，比较而言，通常建议优先使用String构造方法
//        当double必须用作BigDecimal的源时，请使用Double.toString(double)转成String，然后使用String构造方法，或使用BigDecimal的静态方法valueOf
    }
}
