package pers.lxl.mylearnproject.javase.coreclass;

/*
 *基本类型：byte，short，int，long，boolean，float，double，char
 * 引用类型：所有class和interface类型
 * 引用类型可以赋值为null，表示空，但基本类型不能赋值为null
 * 如何把一个基本类型视为对象（引用类型）????---->包装类型
 * 基本类型	对应的引用类型
boolean	java.lang.Boolean
byte	java.lang.Byte
short	java.lang.Short
int*****java.lang.Integer
long	java.lang.Long
float	java.lang.Float
double	java.lang.Double
char****java.lang.Character*/
public class PackagingType {
    public static void main(String[] args) {
        int i = 11;
        // 通过new操作符创建Integer实例(不推荐使用,会有编译警告):
        Integer n1 = new Integer(i);
        // 通过静态方法valueOf(int)创建Integer实例:
        Integer n2 = Integer.valueOf(i);
        // 通过静态方法valueOf(String)创建Integer实例:
        Integer n3 = Integer.valueOf("100");
        System.out.println(n3.intValue());
        //Auto Boxing/Unboxing发生在编译阶段，目的是为了少写代码
        Integer n = 100; // 编译器自动使用Integer.valueOf(int)
        int x = n; // 编译器自动使用Integer.intValue()
//        对两个Integer实例进行比较要特别注意：绝对不能用==比较，因为Integer是引用类型，必须使用equals()比较

    }
}
