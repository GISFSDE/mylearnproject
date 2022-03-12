package pers.lxl.mylearnproject.programbase.newjdk;
/**新版JDKx新特性*/
public class NewJava {
//    lambda   从将一个值赋给一个变量增加为将一块代码赋给一个变量，而lambda本身就是那块代码。
//    只有一个函数接口需要被实现的接口称之为函数是接口
//    lambda变化过程 https://gitee.com/mychimei/picture/raw/master/c8ea15ce36d3d5399cf621bd58615a57372ab0f6.jpeg
   /** lambda只能引用标记为final 的外层局部变量，可以不声明为final但是后面不能修改（隐式final）
    * lambda中不允许定义与局部变量名相同的参数或局部变量*/
public static void main(String args[]){
    NewJava newJava = new NewJava();

    // 类型声明
    MathOperation addition = (int a, int b) -> a + b;

    // 不用类型声明
    MathOperation subtraction = (a, b) -> a - b;

    // 大括号中的返回语句
    MathOperation multiplication = (int a, int b) -> { return a * b; };

    // 没有大括号及返回语句
    MathOperation division = (int a, int b) -> a / b;

    System.out.println("10 + 5 = " + newJava.operate(10, 5, addition));
    System.out.println("10 - 5 = " + newJava.operate(10, 5, subtraction));
    System.out.println("10 x 5 = " + newJava.operate(10, 5, multiplication));
    System.out.println("10 / 5 = " + newJava.operate(10, 5, division));

    // 不用括号
    GreetingService greetService1 = message ->
            System.out.println("Hello " + message);

    // 用括号
    GreetingService greetService2 = (message) ->
            System.out.println("Hello " + message);

    greetService1.sayMessage("Runoob");
    greetService2.sayMessage("Google");
}

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }


}
