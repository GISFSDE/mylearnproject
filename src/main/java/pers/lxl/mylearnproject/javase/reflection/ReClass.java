package pers.lxl.mylearnproject.javase.reflection;
/*不知对象情况调用对象方法等，通过Class实例获取class信息的方法称为反射（Reflection）*/
public class ReClass {

        //    如何获取一个class的Class实例？有三个方法：
//    方法一：直接通过一个class的静态变量class获取：
//        Class cls = String.class;
//    方法二：如果我们有一个实例变量，可以通过该实例变量提供的getClass()方法获取：
//        String s = "Hello";
//        Class cls1 = s.getClass();
//    方法三：如果知道一个class的完整类名，可以通过静态方法Class.forName()获取：
//        Class cls2 = Class.forName("java.lang.String");
//用instanceof不但匹配指定类型，还匹配指定类型的子类。而用==判断class实例可以精确地判断数据类型，但不能作子类型比较

        public static void main(String[] args) {
            printClassInfo("".getClass());
            printClassInfo(Runnable.class);
            printClassInfo(java.time.Month.class);
            printClassInfo(String[].class);
            printClassInfo(int.class);
        }

        static void printClassInfo(Class cls) {
            System.out.println("Class name: " + cls.getName());
            System.out.println("Simple name: " + cls.getSimpleName());
            if (cls.getPackage() != null) {
                System.out.println("Package name: " + cls.getPackage().getName());
            }
            System.out.println("is interface: " + cls.isInterface());
            System.out.println("is enum: " + cls.isEnum());
            System.out.println("is array: " + cls.isArray());
            System.out.println("is primitive: " + cls.isPrimitive());
        }

}
