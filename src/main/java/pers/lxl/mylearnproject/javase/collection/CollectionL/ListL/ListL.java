package pers.lxl.mylearnproject.javase.collection.CollectionL.ListL;

import java.util.*;

/**有序，0开始，元素可重复与null,同数组增删元素麻烦，所以有ArrayList
ArrayList把添加和删除的操作封装起来，让我们操作List类似于操作数组，却不用关心内部元素如何移动。
                      ArrayList	        LinkedList                     Vector
获取指定元素	        速度很快	    需要从头开始查找元素
添加元素到末尾	        速度很快	    速度很快
在指定位置添加/删除	    需要移动元素    不需要移动元素
内存占用	            少	            较大
 * @author lxl*/
public class ListL {
    public static void main(String[] args) {
        //    新建
        //适合查改，线程不安，底层数组(动态扩容50%)。
        // public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
        List<Integer> list = new ArrayList<>();
        //适合增删，线程不安，底层双向链表（JDK1.6双向循环链表）
        List list1 = new LinkedList<>();
        //  底层数组，线程安全
        List list2 = new Vector();
        //增
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(2);
        list.add(5);
        list.add(4);
        list.add(4);
        //删
        list.remove(3);
        //改
        list.set(5, 6);
        //查
        System.out.println("list1的值为" + list.get(1));
        Iterator it = list.iterator();
        //hasNext（）判断是否还有下一个元素
        //Iterator 的特点是只能单向遍历，但是更加安全，因为它可以确保，在当前遍历的集合元素被更改的时候，就会抛出 ConcurrentModificationException 异常。
        while (it.hasNext()) {
            //next（）
            Object obj = it.next();
            System.out.println(obj);
        }
//List<Integer> l = List.of(1,2,3);//Java9创建，传入null，会抛出NullPointerException
//通过Iterator遍历List永远是最高效的方式
//-----list，Array互转-----
//Integer[] array = list.toArray(new Integer[list.size()]);
//List<Integer> list = List.of(array);



//一种最常见的错误代码如下：
//运行以上错误代码会报 ConcurrentModificationException 异常。这是因为当使用 foreach(for(Integer i : list)) 语句时，会自动生成一个iterator 来遍历该 list，但同时该 list 正在被 Iterator.remove() 修改。Java 一般不允许一个线程在遍历 Collection 时另一个线程修改它。
//        for(Integer i : list){
//            list.remove(i);
//        }

    }

}
