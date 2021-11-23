package pers.lxl.mylearnproject.javase.coreclass;
/*
* 我们可以通过static final定义常量，但是在使用时编译器无法检查每个值的合理性
* 为了让编译器能自动检查某个值在枚举的集合内，并且，不同用途的枚举需要不同的类型来标记，不能混用，
* 我们可以使用enum来定义枚举类：*/
public class EnumerationL {
    public static void main(String[] args) {
        Weekday day = Weekday.FRI;
        //int day=1;//编译器会自动检查出类型错误,不可能引用到非枚举的值,不同类型的枚举不能互相比较或者赋值
        if (day==Weekday.MON||day==Weekday.FRI){
            System.out.println("You should work");
        }
        System.out.println(Weekday.FRI.name());
        System.out.println(Weekday.FRI);
        System.out.println(Weekday.FRI.ordinal());//返回定义的常量的顺序，从0开始计数
    }
}
//enum定义的枚举类是一种引用类型,比较用equals（）
//引用类型比较，要始终使用equals()方法，但enum类型可以例外。
//这是因为enum类型的每个常量在JVM中只有一个唯一实例，所以可以直接用==比较：
enum Weekday{
    SUN,MON,TUE,WED,THU,FRI,SAT;
}


/*枚举类有三个实例，故调用三次构造方法*/
enum AccountType
{
    SAVING, FIXED, CURRENT;
    private AccountType()
    {
        System.out.println("It is a account type");
    }
}
class EnumOne
{
    public static void main(String[]args)
    {
        System.out.println(AccountType.FIXED);
    }
}