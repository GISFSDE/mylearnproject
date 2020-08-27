package pers.lxl.mylearnproject.javase.genericity;

import java.util.ArrayList;
import java.util.List;

public class HelloClass {
    public static void main(String[] args) {
        //泛型编写模板代码来适应任意类型，使用ArrayList时，如果不定义泛型类型时，泛型类型实际上就是Object
        ArrayList<String> strList = new ArrayList<String>();
        ArrayList<Integer> strList1 = new ArrayList<Integer>();//泛型不能使基本类型
        //向上转型
        List<String> list = new ArrayList<String>();
        // 可以省略后面的Number，编译器可以自动推断泛型类型：
        List<Number> ln = new ArrayList<>();
        ln.add(new Integer(12));
        ln.add(new Double(12.22));
        System.out.println(ln.toString());
        //除了ArrayList<T>使用了泛型，还可以在接口中使用泛型。例如，Arrays.sort(Object[])可以对任意数组进行排序，但待排序的元素必须实现Comparable<T>这个泛型接口：
        //编写泛型：特定类型--》T，可以多个，泛型类型<T>不能用于静态方法，可改为另外一个泛型类型
//        public class Pair<T> {
//            private T first;
//            private T last;
//            public Pair(T first, T last) {
//                this.first = first;
//                this.last = last;
//            }
//            public T getFirst() {
//                return first;
//            }
//            public T getLast() {
//                return last;
//            }
//        }
//        Java语言的泛型实现方式是擦拭法（Type Erasure）。指虚拟机对泛型其实一无所知，所有的工作都是编译器做的。Java的泛型是由编译器在编译时实行的，编译器内部永远把所有类型T视为Object处理，但是，在需要转型的时候，编译器会根据T的类型自动为我们实行安全地强制转型。
        //局限一：<T>不能是基本类型，例如int，因为实际类型是Object，Object类型无法持有基本类型：
        //局限二：无法取得带泛型的Class。
        //局限三：无法判断带泛型的类型
        //局限四：不能实例化T类型
    }
}
