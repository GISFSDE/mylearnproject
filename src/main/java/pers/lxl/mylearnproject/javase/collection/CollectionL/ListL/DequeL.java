package pers.lxl.mylearnproject.javase.collection.CollectionL.ListL;

import java.util.Deque;
import java.util.LinkedList;

//双端队列（Double Ended Queue）
/*                         Queue	                    Deque
添加元素到队尾	    add(E e) / offer(E e)	addLast(E e) / offerLast(E e)
取队首元素并删除	E remove() / E poll()	E removeFirst() / E pollFirst()
取队首元素但不删除	E element() / E peek()	E getFirst() / E peekFirst()
添加元素到队首	            无	            addFirst(E e) / offerFirst(E e)
取队尾元素并删除	        无	            E removeLast() / E pollLast()
取队尾元素但不删除	        无	            E getLast() / E peekLast()
Deque扩展至Queue可以用Queue所有方法但是最好用Deque的方法（增加可读性，deque.offer()==deque.offerLast()）*/
//实现类有ArrayDeque和LinkedList
public class DequeL {
    public static void main(String[] args) {
// 不推荐的写法:
        LinkedList<String> d1 = new LinkedList<>();
        d1.offerLast("z");
// 推荐的写法：
        Deque<String> d2 = new LinkedList<>();
        d2.offerLast("z");
       // 可见面向抽象编程的一个原则就是：尽量持有接口，而不是具体的实现类。
    }
}
