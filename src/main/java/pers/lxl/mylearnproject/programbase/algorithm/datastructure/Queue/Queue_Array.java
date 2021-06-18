package pers.lxl.mylearnproject.programbase.algorithm.datastructure.Queue;

import java.util.Queue;

/**
 * 队列：先进先出
 */
public abstract class Queue_Array implements Queue {
    //数组的默认容量
    public static final int CAPACITY = 1000;
    //数组的实际容量
    protected int capacity;
    //对象数组
    protected Object[] Q;
    //队首元素的位置
    protected int f = 0;
    //队尾元素的位置
    protected int r = 0;

    //构造方法（空队列）
    public Queue_Array() {
        this(CAPACITY);
    }

    //按指定容量创建对象
    public Queue_Array(int cap) {
        capacity = cap;
        Q = new Object[capacity];
    }

    //查询当前队列的规模
    public int getSize() {
        return (capacity - f + r) % capacity;
    }

    //判断队列是否为空
    @Override
    public boolean isEmpty() {
        return (f == r);
    }

    //入队
    public void enqueue(Object obj) {
        if (getSize() == capacity - 1) {
            System.out.println("Queue overflow.");
        }
        Q[r] = obj;
        r = (r + 1) % capacity;
    }

    //出队
    public Object dequeue() {
        Object elem;
        if (isEmpty()) {
            System.out.println("意外：队列空");
        }
        elem = Q[f];
        Q[f] = null;
        f = (f + 1) % capacity;
        return elem;
    }

    //取（并不删除）队首元素
    public Object front() {
        if (isEmpty()) {
            System.out.println("意外：队列空");
        }
        return Q[f];
    }

    //遍历（不属于ADT）
    public void Traversal() {
        for (int i = f; i < r; i++) {
            System.out.print(Q[i] + " ");
        }
        System.out.println();
    }
}