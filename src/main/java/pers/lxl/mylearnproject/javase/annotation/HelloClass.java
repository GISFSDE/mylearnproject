package pers.lxl.mylearnproject.javase.annotation;

public class HelloClass {
    //常用注解---------------------------------------
//    @Override：让编译器检查该方法是否正确地实现了覆写；
//    @SuppressWarnings：告诉编译器忽略此处代码产生的警告。
    //这类注解不会被编译进入.class文件，它们在编译后就被编译器扔掉了
    //定义注解---------------------------------------
    //@interface语法来定义注解
//public @interface Report {
//    int type() default 0;
//    String level() default "info";
//    String value() default "";//default设定一个默认值（强烈推荐）
//一些注解可以修饰其他注解，这些注解就称为元注解（meta annotation）
//比如最常用@Target可以定义Annotation能够被应用于源码的哪些位置：
//类或接口：ElementType.TYPE；
//字段：ElementType.FIELD；
//方法：ElementType.METHOD；
//构造方法：ElementType.CONSTRUCTOR；
//方法参数：ElementType.PARAMETER。 }
//   另一个元注解 @Retention定义了Annotation的生命周期：
//    仅编译期：RetentionPolicy.SOURCE；
//    仅class文件：RetentionPolicy.CLASS；
//    运行期：RetentionPolicy.RUNTIME。
//    @Repeatable这个元注解可以定义Annotation是否可重复
    //@Inherited定义子类是否可继承父类定义的Annotation。
    // @Inherited仅针对@Target(ElementType.TYPE)类型的annotation有效，
    // 并且仅针对class的继承，对interface的继承无效：
    //完整定义注解：
    // @Target(ElementType.TYPE)
    //@Retention(RetentionPolicy.RUNTIME)
    //public @interface Report {
    //    int type() default 0;
    //    String level() default "info";
    //    String value() default "";
    //}
    //处理注解---------------------------------------
    //Java的注解本身对代码逻辑没有任何影响。根据@Retention的配置：
    //
    //SOURCE类型的注解在编译期就被丢掉了；
    //CLASS类型的注解仅保存在class文件中，它们不会被加载进JVM；
    //RUNTIME类型的注解会被加载进JVM，并且在运行期可以被程序读取。
    //如何使用注解完全由工具决定。SOURCE类型的注解主要由编译器使用，
    // 因此我们一般只使用，不编写。CLASS类型的注解主要由底层工具库使用，
    // 涉及到class的加载，一般我们很少用到。只有RUNTIME类型的注解不但要使用，还经常需要编写。
    //因为注解定义后也是一种class，所有的注解都继承自java.lang.annotation.Annotation，因此，读取注解，需要使用反射API。
    //Java提供的使用反射API读取Annotation的方法包括：
    //判断某个注解是否存在于Class、Field、Method或Constructor：
    //Class.isAnnotationPresent(Class)
    //Field.isAnnotationPresent(Class)
    //Method.isAnnotationPresent(Class)
    //Constructor.isAnnotationPresent(Class)
    //
}
