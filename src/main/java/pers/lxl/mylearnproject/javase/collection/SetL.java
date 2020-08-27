package pers.lxl.mylearnproject.javase.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*Set存储不重复元素，相当于之存储key的Map,常用Set用于去除重复元素。
* */
public class SetL {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Set<String> set1 = new TreeSet<>();
        //HashSet是无序的，因为它实现了Set接口，并没有实现SortedSet接口；
        //TreeSet是有序的，因为它实现了SortedSet接口。
        System.out.println(set.add("ww"));
        System.out.println(set.add("va1"));
        System.out.println(set.add("da"));
        System.out.println(set.add("aa1"));
        System.out.println(set.add("ba2"));
        System.out.println(set1.add("ww"));
        System.out.println(set1.add("va1"));
        System.out.println(set1.add("da"));
        System.out.println(set1.add("aa1"));
        System.out.println(set1.add("ba2"));
        System.out.println(set.add("da"));//不可重复添加
        System.out.println(set.contains("w"));
        System.out.println(set.contains("W"));
        System.out.println(set.contains("ww"));
        System.out.println(set.remove("d"));
        System.out.println(set.remove("ww"));
        System.out.println(set);
        System.out.println(set1);
    }
}
