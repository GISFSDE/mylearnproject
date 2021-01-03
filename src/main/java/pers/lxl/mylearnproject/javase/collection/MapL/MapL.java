package pers.lxl.mylearnproject.javase.collection.MapL;

import pers.lxl.mylearnproject.javase.oop.Student;

import java.util.HashMap;
import java.util.Map;

//Map,（key-value）,快速查找，key不重,重覆盖值，需要正确覆写equals()，hashCode()方法
public class MapL {
    public static void main(String[] args) {
        Student s = new Student("Kaa", 12, 21);
        Map<String, Student> map = new HashMap<>();
        Map<String, Student> map2 = new HashMap<>();
        map.put("Kaa", s);// 将"Kaa"和Student实例映射并关联,put相同的key会将value覆盖，key不能重复，value可以
        Student target = map.get("Kaa");// 通过key查找并返回映射的Student实例
        System.out.println(target == s);
        System.out.println(target.equals(s));
        System.out.println(target.getScore());
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("apple", 122);
        map1.put("name", 121);
        map1.put("wdd", 143);
        for (String key : map1.keySet()) {//iter+table快捷键
            Integer value = map1.get(key);
            System.out.println(value);//key会保证被遍历一次且仅遍历一次，但顺序完全没有保证
        }
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {//使用Map时，任何依赖顺序的逻辑都是不可靠的。
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "=" + value);
        }
        //key是一个自己写的类,正确使用Map必须保证：
        //作为key的对象必须正确覆写equals()方法，相等的两个key实例调用equals()必须返回true；
        //作为key的对象还必须正确覆写hashCode()方法，且hashCode()方法要严格遵循以下规范：
        //如果两个对象相等，则两个对象的hashCode()必须相等；
        //如果两个对象不相等，则两个对象的hashCode()尽量不要相等。

        //编写equals()和hashCode()遵循的原则是：
        //equals()用到的用于比较的每一个字段，都必须在hashCode()中用于计算；
        // equals()中没有使用到的字段，绝不可放在hashCode()中计算。
    }

}

