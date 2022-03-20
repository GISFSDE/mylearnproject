package pers.lxl.mylearnproject.javase.collection.MapL;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**接口SortedMap对key进行排序(比如字符串默认以字母进行排序)，实现类TreeMap
 * @author lxl*/
public class TreeMapL {
    public static void main(String[] args) {
//如果作为Key的class(String、Integer这些类已经实现了Comparable接口)没有实现Comparable接口，
// 那么，必须在创建TreeMap时同时指定一个自定义排序算法



//原始
//         Map<Person,Integer> map=new TreeMap<>(new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {//必须返回0,1，-1
//                return o1.name.compareTo(o2.name);//compareTo字符串与对象进行比较。 按字典顺序比较两个字符串
//            }
//        });
//简化1
//         Map<Person,Integer> map=new TreeMap<>((o1, o2) -> {//必须返回0,1，-1
//            return o1.name.compareTo(o2.name);//compareTo字符串与对象进行比较。 按字典顺序比较两个字符串
//        });
//简化2
        Map<Person,Integer> map=new TreeMap<>(Comparator.comparing(o -> o.name));

        map.put(new Person("tom"),1);
        map.put(new Person("Bob"),2);
        map.put(new Person("Aob"),3);
        map.put(new Person("Gob"),4);


        for (Person key : map.keySet()) {
            //每个对象用此语句输出
            System.out.println(key);
        }
        System.out.println(map.get(new Person("Bob")));

        //        多值可空
//        Exception in thread "main" java.lang.NullPointerException
//	at pers.lxl.mylearnproject.javase.collection.MapL.TreeMapL.lambda$main$0(TreeMapL.java:28)
//	at java.util.Comparator.lambda$comparing$77a9974f$1(Comparator.java:469)
//	at java.util.TreeMap.put(TreeMap.java:552)
//	at pers.lxl.mylearnproject.javase.collection.MapL.TreeMapL.main(TreeMapL.java:34)

        Map map1 = new TreeMap();
//        map1.put(null,null);
//        map1.put(null,"ww");
        map1.put("ww",null);
        map1.put("ww1",null);

        for (Object m:map1.entrySet()
             ) {
            System.out.println(m);
        }


    }


}
    class Person{
        public String name;
        Person(String name){
            this.name=name;
        }
        @Override
        public String toString(){
            //①.必须被声明为public
            //②.返回类型为String
            //③.方法的名称必须为toString,且无参数
            //④.方法体中不要使用输出方法System.out.println()//会改变格式和内容
            // System.out.println("wd");
            //如果不重写当p.toString就会输出对象的内存地址，自定义后按自定义方式输出
            return "{Person:" + name + "}";
        }
    }

