package pers.lxl.mylearnproject.javase.oop;

public class Main {
    public static void main(String[] args) {
        //Construction Method
        Person p1 = new Person("lxl1");
        Person p2 = new Person("lxl2", 21);
        System.out.println(p1.getName() + p1.getAge());
        System.out.println(p2.getName() + p2.getAge());
        //Method Overloading
        Person li = new Person();
        Person xiao = new Person();
        li.setName("lxl");
        li.setName("xiao", "mei");
        System.out.println(li.getName() + xiao.getName());
        //Inherit
        Student s1 = new Student("lxl", 12, 122);
        //Upcasting向上转型(子类类型安全地变为父类类型的赋值)
        Person p = new Student("lxl", 12, 122);
        //Downcasting
        Student ss1 = (Student) p;
//        Student ss2=(Student) p1;//ClassCastException不能把父类变为子类，因为子类功能比父类多，多的功能无法凭空变出来
//        instanceof操作符，可以先判断一个实例究竟是不是某种类型,可用于向下转型之前判断
//        Java 14开始，判断instanceof后，可以直接转型为指定变量，避免再次强制转型
//        System.out.println(ss1 instanceof Person);
//        System.out.println(ss1 instanceof Student);
        //覆写（Override）Override和Overload不同的是，如果方法签名如果不同，就是Overload，Overload方法是一个新方法；如果方法签名相同，并且返回值也相同，就是Override。
        //方法返回值不同，也是不同的方法。在Java程序中，出现这种情况，编译器会报错。
          Person pp = new Student();
          p.run();
//       实际类型为Student，引用类型为Person的变量，实际上调用的方法是Student的run()方法
//       Java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
//       这个非常重要的特性在面向对象编程中称之为多态。它的英文拼写非常复杂：Polymorphic。
        //Polymorphic(多态是指，针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法)




    }
}
