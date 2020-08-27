package pers.lxl.mylearnproject.javase.collection;

import java.util.List;

//有序，0开始，元素可重复与null,同数组增删元素麻烦，所以有ArrayList
//ArrayList把添加和删除的操作封装起来，让我们操作List类似于操作数组，却不用关心内部元素如何移动。
//                      ArrayList	    LinkedList
//获取指定元素	        速度很快	    需要从头开始查找元素
//添加元素到末尾	    速度很快	    速度很快
//在指定位置添加/删除	需要移动元素	不需要移动元素
//内存占用	            少	            较大
public class ListL {
//    List<Integer> l = List.of(1,2,3);//Java9创建，传入null，会抛出NullPointerException
    //通过Iterator遍历List永远是最高效的方式
//list，Array互转
//Integer[] array = list.toArray(new Integer[list.size()]);
//List<Integer> list = List.of(array);

}
