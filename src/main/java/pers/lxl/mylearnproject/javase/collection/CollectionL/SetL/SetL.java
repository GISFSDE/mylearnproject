package pers.lxl.mylearnproject.javase.collection.CollectionL.SetL;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**Set存储不重复元素，相当于之存储key的Map,常用Set用于去除重复元素。
 * @author lxl
 */
public class SetL {
    public static void main(String[] args) {
        //HashSet是不保证有序的，因为它实现了Set接口，并没有实现SortedSet接口；
        Set<String> set = new HashSet<>();
//        HashSet 的⼦类，能够按照添加的顺序遍历，基于双向链表
        Set<String> set2 = new LinkedHashSet<>();
        //TreeSet是有序的，因为它实现了SortedSet接口，基于红黑树。
        Set<String> set1 = new TreeSet<>();
        set.add("5");
        set.add("2");
        set.add("1");
        set.add("a");
        set.add("3");
        set.add("4");

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
