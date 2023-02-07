package pers.lxl.mylearnproject.javase.collection;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.lxl.mylearnproject.javase.oop.Person;
import pers.lxl.mylearnproject.programbase.logs.LogsLearn;

import java.util.*;
import java.util.Collections;

/**
 * JDK提供的工具类，同样位于java.util包中。它提供了一系列静态方法，能更方便地操作各种集合。
 */

public class CollectionsL {
    private static final Logger logger = LoggerFactory.getLogger(LogsLearn.class);

    public static void main(String[] args) {

//遍历
        Collection<Person> persons = new ArrayList<Person>();
        Iterator iterator = persons.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        logger.error("list" + StringUtils.join(list.toArray(), ","));
        Collections.reverse(list);
        logger.error("Collections.reverse :" + StringUtils.join(list.toArray(), ","));
        Collections.sort(list);
        logger.error("Collections.sort :" + StringUtils.join(list.toArray(), ","));
        //创建空集合
        //创建空List：List<T> emptyList()
        //创建空Map：Map<K, V> emptyMap()
        //创建空Set：Set<T> emptySet()

        //创建单元素集合
        //创建一个元素的List：List<T> singletonList(T o)
        //创建一个元素的Map：Map<K, V> singletonMap(K key, V value)
        //创建一个元素的Set：Set<T> singleton(T o)

        //排序sort()

        //洗牌shuffle()

        //不可变集合
        //封装成不可变List：List<T> unmodifiableList(List<? extends T> list)
        //封装成不可变Set：Set<T> unmodifiableSet(Set<? extends T> set)
        //封装成不可变Map：Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m)

        //线程安全集合
        //变为线程安全的List：List<T> synchronizedList(List<T> list)
        //变为线程安全的Set：Set<T> synchronizedSet(Set<T> s)
        //变为线程安全的Map：Map<K,V> synchronizedMap(Map<K,V> m)

    }
}
