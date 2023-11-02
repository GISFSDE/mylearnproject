package pers.lxl.mylearnproject.javase.oop;

// 因为无法执行抽象方法，因此这个类也必须申明为抽象类（abstract class）
// 使用abstract修饰的类就是抽象类。我们无法实例化一个抽象类
// 因为抽象类本身被设计成只能用于被继承，因此，抽象类可以强迫子类实现其定义的抽象方法，否则编译会报错。
// 因此，抽象方法实际上相当于定义了“规范”。
// 一个.java文件只能包含一个public类，但可以包含多个非public类。如果有public类，文件名必须和public类的名字相同。
public abstract class Tool {

    protected String name;
    protected double price;

    // class定义了方法，但没有具体执行代码，这个方法就是抽象方法
    public abstract void use();
}

class Phone extends Tool {
    @Override
    public void use() {
        System.out.println("I can call.");
    }
}

// 在抽象类中，抽象方法本质上是定义接口规范：即规定高层类的接口，从而保证所有子类都有相同的接口实现，这样，多态就能发挥出威力。
// 如果一个抽象类没有字段，所有方法全部都是抽象方法：
// 就可以把该抽象类改写为接口：interface
// 所谓interface，就是比抽象类还要抽象的纯抽象接口，因为它连字段都不能有。
// 因为接口定义的所有方法默认都是public abstract的
// 所以这两个修饰符不需要写出来（写不写效果都一样）
// 实现接口用implements,一个类不能继承多个类，但是可以实现多个接口，接口之间可继承使用extends

interface animal {
    // 因为interface是一个纯抽象类，所以它不能定义实例字段。但是，interface是可以有静态字段的，
    // 并且静态字段必须为final类型,public static final可省略，编译器会自动把该字段变为public static final类型。
    public static final String name = "wangcai";

    // 实现类可以不必覆写default方法。default方法的目的是，
// 当我们需要给接口新增一个方法时，会涉及到修改全部子类。
// 如果新增的是default方法，那么子类就不必全部修改，只需要在需要覆写的地方去覆写新增方法。
// default方法和抽象类的普通方法是有所不同的。因为interface没有字段，
// default方法无法访问字段，而抽象类的普通方法可以访问实例字段。
    default void eat() {
        System.out.println(getName() + "run");
    }

    void run();

    String getName();
}

class cat implements animal {
    @Override
    public void run() {

    }

    @Override
    public String getName() {
        return null;
    }
}

class Dog {
    public static String name;

    //    因为静态方法属于class而不属于实例，因此，静态方法内部，无法访问this变量，也无法访问实例字段，它只能访问静态字段
    public static void setName(String name) {
        // this.
        name = name;
    }
}

class Main1 {
    public static void main(String[] args) {
        // Tool tool=new Tool();
        Phone p = new Phone();
        Tool too1 = new Phone();// 尽量引用高层类型，避免引用实际子类型的方式，称之为面向抽象编程
        p.use();
        too1.use();

        // static  对于静态字段，无论修改哪个实例的静态字段，效果都是一样的：所有实例的静态字段都被修改了，原因是静态字段并不属于实例：
        Dog dog = new Dog();
        Dog dog1 = new Dog();
        dog.name = "wd";
        System.out.println(dog.name);
        System.out.println(dog1.name);
        dog1.name = "2wd";
        System.out.println(dog.name);
        System.out.println(dog1.name);
        Dog.name = "wwww";// 推荐写法
        System.out.println(Dog.name);// 推荐写法
        System.out.println(dog1.name);
        System.out.println(dog1.name);
    }


}