package pers.lxl.mylearnproject.javase.testlearn;




/*单元测试就是针对最小的功能单元编写测试代码。Java程序最小的功能单元是方法，
因此，对Java程序进行单元测试就是针对单个Java方法的测试。
测试驱动开发，是指先编写接口，紧接着编写测试。编写完测试后，我们才开始真正编写实现代码。
在编写实现代码的过程中，一边写，一边测，什么时候测试全部通过了，那就表示编写的实现完成了
  编写接口
     │
     ▼
    编写测试
     │
     ▼
┌─> 编写实现
│    │
│ N  ▼
└── 运行测试
     │ Y
     ▼
    任务完成
    TDD*/
public class FactorialT {

    public static long fact(long n){
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        long r=1;
        for (int i = 0; i <=n ; i++) {
            r=r*i;
        }
        return r;
    }

    //最原始main测试，缺点如下：
    //1.测试代码不能分离factorial
    //2.没有打出期望结果
    //3.测试代码不通用

    public static void main(String[] args) {
        if (fact(10)== 3628800){
            System.out.println("pass");
        }else {
            System.out.println("fail");
        }
    }
}

