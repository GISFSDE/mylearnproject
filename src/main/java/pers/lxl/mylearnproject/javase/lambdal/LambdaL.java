package pers.lxl.mylearnproject.javase.lambdal;

import java.util.function.*;

/**
 * Lambda Learn
 * reference：https://blog.csdn.net/weixin_37766087/article/details/94999833
 */
//作用：将一段代码或者一个函数赋给一个对象，类似JS中var a= function(){}
public class LambdaL {

//函数式接口
//     一个只有一个抽象方法（不包含object中的方法）的接口。
//    @FunctionalInterface可以判断是否为函数式接口，不是会报编译错误
@FunctionalInterface
    interface eat {
        void eatFood();
    }

//    常用的函数式接口
//    在jdk中通用的函数式接口如下（都在java.util.function包中）：

    Runnable r = () -> System.out.printf("say hello");//没有输入参数，也没有输出
    Supplier<String> sp = () -> "hello";//只有输出消息，没有输入参数
    Consumer<String> cp = r -> System.out.printf(r);//有一个输入参数，没有输出
    Function<Integer, String> func = r -> String.valueOf(r);//有一个输入参数 有一个输出参数
    BiFunction<Integer, Integer, String> biFunc = (a, b) -> String.valueOf(a + b);//有两个输入参数 有一个输出参数
    BiConsumer<Integer, Integer> biCp = (a, b) -> System.out.printf(String.valueOf(a + b));//有两个输入参数 没有输出参数

    public static void main(String[] args) {
//lambda方式
        eat e = () -> System.out.printf("hello\n");
        e.eatFood();
//匿名类方式
        eat e1 = new eat() {
            @Override
            public void eatFood() {
                System.out.printf("anoymous class\n");
            }
        };
        e1.eatFood();
    }

}
