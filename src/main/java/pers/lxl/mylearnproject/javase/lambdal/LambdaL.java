package pers.lxl.mylearnproject.javase.lambdal;

import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;
import pers.lxl.mylearnproject.javaee.servlet.SessionCookie.SignOutServlet;

import java.util.function.*;

/**
 * Lambda Learn
 * reference：
 * https://mbd.baidu.com/newspage/data/landingsuper?third=baijiahao&baijiahao_id=1685401324435208513&c_source=duedge&p_tk=7164XVbr93kEf9ebx0OCzJEBUC%2FSHtSm0HlbQtXUZnwLWsjL7uvd3VtqGMu6%2BPCP1nEnkpjkthOA%2Fges4OjPt98pqpAcTnKrjR0H1SBkWqMgO7awH2MmJkS5erhN2ZKz%2Bqas&p_timestamp=1646483138&p_sign=39698dd00b7d66b85d43028d11209e58&p_signature=b95e00362b799da95380ab116026ade1&__pc2ps_ab=7164XVbr93kEf9ebx0OCzJEBUC%2FSHtSm0HlbQtXUZnwLWsjL7uvd3VtqGMu6%2BPCP1nEnkpjkthOA%2Fges4OjPt98pqpAcTnKrjR0H1SBkWqMgO7awH2MmJkS5erhN2ZKz%2Bqas%7C1646483138%7Cb95e00362b799da95380ab116026ade1%7C39698dd00b7d66b85d43028d11209e58
 * https://blog.csdn.net/weixin_37766087/article/details/94999833
 */

/**
 * 作用：将一段代码或者一个函数赋给一个对象，类似JS中var a= function(){}
 *
 * @author Administrator
 */
public class LambdaL<codeBlock> {
    /*    演变过程*/
//
    /**
     * 1.变量赋值如下
     */
    int i = 1;

/**   2.如何将一个代码块赋值给一个变量？*/
//    codeBlock= public void printString(String s){
//        System.out.println(s);
//    }

    /**
     * 3.这个变量什么类型？
     * lambda所有类型都是一个接口，且为函数式接口【函数式接口中只有一个接口需要被实现，可以用@FunctionalInterface进行约束，不是函数式接口会报错】
     */
    @FunctionalInterface
    interface LambdaInterface {
        void doSomeThing(String s);
//        Multiple non-overriding abstract methods found in interface pers.lxl.mylearnproject.javase.lambdal.LambdaL.LambdaInterface
//        void doSomeThing1(String s);
    }

    //    4.得到完整将代码块赋值给变量？
//    LambdaInterface codeBlock =public void doSomeThing(String s) {
//        System.out.println(s);
//    }


    /**
     * 5.简化：public 是多余的，
     * doSomeThing多余的,因为已经赋值给codeBlock变量了
     * void返回类型 和String s 的类型多余，因为编译器可自动判断
     * 只有一行代码可不要大括号，但是平时代码不可，不规范
     * for(int i=0;i<10;i++) System.out.println(i);
     * 大括号可忽略因为只有一行代码,,在参数和函数之间添加箭头符号，最后得到如下代码
     */
    LambdaInterface codeBlock = (s) -> System.out.println(s);


    /**
     * //函数式接口
     * //     一个只有一个抽象方法（不包含object中的方法）的接口。
     * //    @FunctionalInterface可以判断是否为函数式接口，不是会报编译错误
     */
    @FunctionalInterface
    interface eat {
        void eatFood();
    }

    /* 常用的自带函数式接口*/
    /**
     * 在jdk中通用的函数式接口如下（都在java.util.function包中）：
     * 没有输入参数，也没有输出
     */
    Runnable r = () -> System.out.printf("say hello");
    /**
     * 没有输入参数,有输出，
     */
    Supplier<String> sp = () -> "hello";
    /**
     * 有一个输入参数，没有输出
     */
    Consumer<String> cp = r -> System.out.printf(r);
    /**
     * 有一个输入参数 有一个输出参数
     */
    Function<Integer, String> func = r -> String.valueOf(r);
    /**
     * 有两个输入参数 有一个输出参数
     */
    BiFunction<Integer, Integer, String> biFunc = (a, b) -> String.valueOf(a + b);
    /**
     * 有两个输入参数 没有输出参数
     */
    BiConsumer<Integer, Integer> biCp = (a, b) -> System.out.printf(String.valueOf(a + b));

@Test
public void lambdaTest(){
    r.run();
    System.out.println();
    System.out.println(sp.get());
    cp.accept("一个输入参数");
    System.out.println();
    System.out.println(func.apply(1));
   System.out.println( biFunc.apply(1,2) );
    biCp.accept(2,2);
}


    public static void main(String[] args) {
/*lambda方式*/
        eat e = () -> System.out.printf("hello\n");
        e.eatFood();
/*匿名类方式*/
        eat e1 = new eat() {
            @Override
            public void eatFood() {
                System.out.printf("anoymous class\n");
            }
        };
        e1.eatFood();


    }


}
