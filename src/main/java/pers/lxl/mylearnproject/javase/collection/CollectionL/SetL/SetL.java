package pers.lxl.mylearnproject.javase.collection.CollectionL.SetL;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**Set存储不重复元素，相当于只存储key的Map,常用Set用于去除重复元素。
 * @author lxl
 */
public class SetL {
    public static void main(String[] args) {
        //HashSet是不保证有序的，因为它实现了Set接口，并没有实现SortedSet接口；
        Set<String> set = new HashSet<>();
//        HashSet 的⼦类，能够按照添加的顺序遍历，基于双向链表
        Set<String> set2 = new LinkedHashSet<>();
        //TreeSet是有序的，因为它实现了SortedSet接口，基于红黑树，线程不安全
        Set<String> set1 = new TreeSet<>();
        set.add("5");
        set.add("2");
        set.add("1");
        set.add("a");
//        多空单存
        set.add(null);
        set.add(null);
        set.add(null);
        set.add("4");

//TreeSet默认元素不能为空，除非自定义Comparator
//  Exception in thread "main" java.lang.NullPointerException
//	at java.util.TreeMap.compare(TreeMap.java:1294)
//	at java.util.TreeMap.put(TreeMap.java:538)
//	at java.util.TreeSet.add(TreeSet.java:255)
//	at pers.lxl.mylearnproject.javase.collection.CollectionL.SetL.SetL.main(SetL.java:27)
//        set1.add(null);
        set1.add("1");
        set1.add("3");
        set1.add("2");
        set1.add("5");
        set1.add("4");

        //不可重复添加[覆盖]
        set.add("1");

        System.out.println(set.contains("2"));
        System.out.println(set.remove("2"));
        System.out.println("HashSet:"+set);
        System.out.println("TreeSet:"+set1);
    }
}
