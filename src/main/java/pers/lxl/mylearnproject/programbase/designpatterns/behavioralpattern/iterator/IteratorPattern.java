package pers.lxl.mylearnproject.programbase.designpatterns.behavioralpattern.iterator;

import java.util.*;
public class IteratorPattern {
    public static void main(String[] args) {
        Aggregate ag = new ConcreteAggregate();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");
        System.out.print("聚合的内容有：");
        Iterator it = ag.getIterator();
        while (it.hasNext()) {
            Object ob = it.next();
            System.out.print(ob.toString() + "\t");
        }
        Object ob = it.first();
        System.out.println("\nFirst：" + ob.toString());
    }
}
//抽象聚合  定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
interface Aggregate {
    public void add(Object obj);
    public void remove(Object obj);
    public Iterator getIterator();
}
//具体聚合  实现抽象聚合类，返回一个具体迭代器的实例。
class ConcreteAggregate implements Aggregate {
    private List<Object> list = new ArrayList<Object>();
    @Override
    public void add(Object obj) {
        list.add(obj);
    }
    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }
    @Override
    public Iterator getIterator() {
        return (new ConcreteIterator(list));
    }
}
//抽象迭代器
interface Iterator {
    Object first();
    Object next();
    boolean hasNext();
}
//具体迭代器
class ConcreteIterator implements Iterator {
    private List<Object> list = null;
    private int index = -1;
    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }
    @Override
    public boolean hasNext() {
        if (index < list.size() - 1) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Object first() {
        index = 0;
        Object obj = list.get(index);
        ;
        return obj;
    }
    @Override
    public Object next() {
        Object obj = null;
        if (this.hasNext()) {
            obj = list.get(++index);
        }
        return obj;
    }
}
