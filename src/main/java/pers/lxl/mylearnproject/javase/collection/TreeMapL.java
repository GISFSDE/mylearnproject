package pers.lxl.mylearnproject.javase.collection;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//接口SortedMap对key进行排序(比如字符串默认以字母进行排序)，实现类TreeMap
public class TreeMapL {
    public static void main(String[] args) {
//如果作为Key的class(String、Integer这些类已经实现了Comparable接口)没有实现Comparable接口，
// 那么，必须在创建TreeMap时同时指定一个自定义排序算法
        Map<Person,Integer> map=new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {//必须返回0,1，-1
                return o1.name.compareTo(o2.name);//compareTo字符串与对象进行比较。 按字典顺序比较两个字符串
            }
        });
        map.put(new Person("tom"),1);
        map.put(new Person("Bob"),2);
        map.put(new Person("Aob"),3);
        map.put(new Person("Gob"),4);
        for (Person key : map.keySet()) {
            System.out.println(key);//每个对象用此语句输出
        }
        System.out.println(map.get(new Person("Bob")));
    }
}
    class Person{
        public String name;
        Person(String name){
            this.name=name;
        }
        public String toString(){
            //①.必须被声明为public
            //②.返回类型为String
            //③.方法的名称必须为toString,且无参数
            //④.方法体中不要使用输出方法System.out.println()//会改变格式和内容
//            System.out.println("wd");
            return "{Person:" + name + "}";//如果不重写当p.toString就会输出对象的内存地址，自定义后按自定义方式输出
        }
    }

