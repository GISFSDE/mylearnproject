package pers.lxl.mylearnproject.javase.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 不知对象情况调用对象方法等，通过Class实例获取class信息的方法称为反射（Reflection）
 *
 * @author lxl
 */
public class ReClass {


    public static void main(String[] args) {
/** 1.获取一个class的Class实例的三个方法：
 方法一：class的静态变量.class：
 Class cls = String.class;
 方法二：实例变量.getClass()：
 String s = "Hello";
 Class cls1 = s.getClass();
 方法三：静态方法Class.forName(class的完整类名)：
 只会让静态代码块执行
 Class cls2 = Class.forName("java.lang.String");
 用instanceof不但匹配指定类型，还匹配指定类型的子类。而用==判断class实例可以精确地判断数据类型，但不能作子类型比较*/
        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);
        printClassInfo(int.class);
//        ReflectLearn reflectLearn=new ReflectLearn();
        printClassInfo(ReflectLearn.class);
    }

    static void printClassInfo(Class cls) {

//            先获取类之后才能获取类信息

//        获取类信息
        System.out.println("[" + cls.getSimpleName() + "]  begin ==================================");
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());


//        方法信息
//        .newInstance() 无参构造，不存在抛出异常java.lang.InstantiationException
        try {
            System.out.println("无参构造：   "+cls.newInstance());
        } catch (InstantiationException e) {
            System.out.println("没有无参构造造成InstantiationException");
        } catch (IllegalAccessException e) {
            System.out.println("没有无参构造造成IllegalAccessException");
        }
        System.out.println("所有实例方法：   "+cls.getDeclaredMethods());
//        System.out.println("根据名字获取方法：   "+ Arrays.toString(cls.getDeclaredMethods("methodShow")));
        System.out.println("所有构造方法：   "+ Arrays.toString(cls.getDeclaredConstructors()));



//        属性信息
        System.out.println("public Fields: " + Arrays.toString(cls.getFields()));
        System.out.println("Fields public Name: " + cls.getName());
        System.out.println("All Fields: " + Arrays.toString(cls.getDeclaredFields()));
        try {
            System.out.println("Get Fields By Name: " + Arrays.toString(new Field[]{cls.getDeclaredField("publicFiled")}));
        } catch (NoSuchFieldException e) {
            System.out.println("没有这样一个属性报NoSuchFieldException");
        }


        System.out.println("[" + cls.getSimpleName() + "]  end ==================================" + "\n");
    }

}

class ReflectLearn {
    public String publicFiled;
    private String privateFiled;


    public int methodShow(){
        return 1;
    }
}
