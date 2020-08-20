package pers.lxl.mylearnproject.javase.oop;

public class Main {
    public static void main(String[] args) {
        //Construction Method
        Person p1 = new Person("lxl1");
        Person p2 = new Person("lxl2",21);
        System.out.println(p1.getName()+p1.getAge());
        System.out.println(p2.getName()+p2.getAge());
        //Method
        Person li=new Person();
        Person xiao=new Person();
        li.setName("lxl");
        li.setName("xiao","mei");
        System.out.println(li.getName()+xiao.getName());


    }
}
