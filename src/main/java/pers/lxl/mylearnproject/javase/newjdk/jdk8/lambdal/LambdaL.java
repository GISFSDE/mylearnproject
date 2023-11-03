package pers.lxl.mylearnproject.javase.newjdk.jdk8.lambdal;

import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lambda Learn
 * reference：
 * <a href="https://blog.csdn.net/weixin_37766087/article/details/94999833">参考</a>
 * <a href="https://blog.csdn.net/lm324114/article/details/105870209">参考</a>
 * λ 匿名函数（Anonymous Function）
 * 作用：将一段代码或者一个函数赋给一个对象，类似JS中var a= function(){}
 * @author Administrator
 */
public class LambdaL<codeBlock> {
    /*    演变过程*/
//
    /**
     * 1.变量赋值如下
     */
    int i = 1;

/**   2.如何将一个代码块或者函数赋值给一个变量？*/
    // codeBlock= public void printString(String s){
    //     System.out.println(s);
    // }

    /**
     * 3.这个变量什么类型？
     * lambda所有类型都是一个接口，且为函数式接口【函数式接口中只有一个接口需要被实现，可以用@FunctionalInterface进行约束，不是函数式接口会报错】
     */
    @FunctionalInterface
    interface LambdaInterface {
        void doSomeThing(String s);
//      多个接口报错  Multiple non-overriding abstract methods found in interface pers.lxl.mylearnproject.javase.newjdk.jdk8.lambdal.LambdaL.LambdaInterface
//        void doSomeThing1(String s);
    }

    //    4.得到完整将代码块赋值给变量？
    // LambdaInterface codeBlock =public void doSomeThing(String s) {
    //     System.out.println(s);
    // }


    /**
     * 5.简化：public 是多余的，
     * doSomeThing多余的,因为已经赋值给codeBlock变量了
     * void返回类型 和String s 的类型String多余，因为编译器可自动判断
     * 只有一行代码可不要大括号，但是平时代码如下不可，不规范
     * for(int i=0;i<10;i++) System.out.println(i);
     * 大括号可忽略因为只有一行代码,在参数和函数之间添加箭头符号，最后得到如下代码
     */
    LambdaInterface codeBlock = (s) -> System.out.println(s);


    /**
     * //函数式接口
     * //     一个只有一个抽象方法（不包含object中的方法）的接口。但在判断是否是函数式接口的时候要排除Object中的方法，
     * //    @FunctionalInterface可以判断是否为函数式接口，不是会报编译错误
     */
    @FunctionalInterface
    interface eat {
        void eatFood();
    }

    eat eat = System.out::println;
    // 这个不是是函数式接口
    // interface eat
    // {
    //     default void eatFood()
    //     {
    //         System.out.println("hello");
    //     };
    // }

    // 这个是一个函数式接口
    // interface eat
    // {
    //     void eatFood();
    //     String toString();
    // }
    /**<h1>基本语法</h1>: (parameters) -> expression 或 (parameters) ->{ statements; }
     *
     Lambda表达式由三部分组成：
     parameters：类似方法中的形参列表，这里的参数是函数式接口里的参数。这里的参数类型可以明确的声明也可不声明而由JVM隐含的推断。另外当只有一个推断类型时可以省略掉圆括号。
     ->：可理解为“被用于”的意思
     方法体：可以是表达式也可以代码块，是函数式接口里方法的实现。代码块可返回一个值或者什么都不反回，这里的代码块块等同于方法的方法体。如果是表达式，也可以返回一个值或者什么都不反回。
     * 常用的自带函数式接口,在jdk中通用的函数式接口如下（都在java.util.function包中）：
     */

    /**
     * 没有输入参数，也没有输出
     */
    Runnable r = () -> System.out.printf("say hello");
    // Runnable rOld = new Runnable() {
    //     @Override
    //     public void run() {
    //
    //     }
    // };
    /**
     * 没有输入参数,有输出，
     */
    Supplier<String> sp = () -> "hello";
    /**
     * 有一个输入参数，没有输出
     */
    Consumer<String> cp = r -> System.out.printf(r);
    /**
     * 有一个输入参数 有一个输出参数
     */
    Function<Integer, String> func = r -> String.valueOf(r);
    /**
     * 有两个输入参数 有一个输出参数
     */
    BiFunction<Integer, Integer, String> biFunc = (a, b) -> String.valueOf(a + b);
    /**
     * 有两个输入参数 没有输出参数
     */
    BiConsumer<Integer, Integer> biCp = (a, b) -> System.out.printf(String.valueOf(a + b));

    // Predicate<T>//接收一个参数，返回一个boolean的结果
    @Test
    public void lambdaTest() {
        r.run();
        System.out.println();
        System.out.println(sp.get());
        cp.accept("一个输入参数");

        System.out.println(func.apply(1));
        Function<Integer, Integer> A = i -> i + 1;
        Function<Integer, Integer> B = i -> i * i;
        System.out.println("F1:" + B.apply(A.apply(5)));
        // compose接收一个Function参数，返回时先用传入的逻辑执行apply，然后使用当前Function的apply
        System.out.println("F1:" + B.compose(A).apply(5));
        System.out.println("F2:" + A.apply(B.apply(5)));
        // andThen跟compose正相反，先执行当前的逻辑，再执行传入的逻辑。
        System.out.println("F2:" + B.andThen(A).apply(5));

        System.out.println(biFunc.apply(1, 2));
        biCp.accept(2, 2);

        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        List<Integer> list = new ArrayList<>();
        for (int i : numbers) {
            list.add(i);
        }
        Predicate<Integer> p1 = i -> i > 5;
        Predicate<Integer> p2 = i -> i < 20;
        Predicate<Integer> p3 = i -> i % 2 == 0;
        List test = list.stream().filter(p1.and(p2).and(p3)).collect(Collectors.toList());
        System.out.println(test.toString());
    }


    /**
     * stream()优点
     * 无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
     * 为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
     * 惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
     * 可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
     * 什么是中间操作和终止操作
     * 中间型操作
     * 中间型操作就是返回值依旧是stream类型的方法。api如下：
     * 串行流性能(未实验)：万条以下：for>foreach>stream
     *             万条以上 stream>foreach>for
     *             其他：
     *
     * API	功能说明	无状态操作
     * filter()	按照条件过滤符合要求的元素， 返回新的stream流。	是
     * map()	将已有元素转换为另一个对象类型，一对一逻辑，返回新的stream流	是
     * peek()	对stream流中的每个元素进行逐个遍历处理，返回处理后的stream流	是
     * flatMap()	将已有元素转换为另一个对象类型，一对多逻辑，即原来一个元素对象可能会转换为1个或者多个新类型的元素，返回新的stream流	是
     * limit()	仅保留集合前面指定个数的元素，返回新的stream流	否
     * skip()	跳过集合前面指定个数的元素，返回新的stream流	否
     * concat()	将两个流的数据合并起来为1个新的流，返回新的stream流	否
     * distinct()	对Stream中所有元素进行去重，返回新的stream流	否
     * sorted()	对stream中所有的元素按照指定规则进行排序，返回新的stream流	否
     * takeWhile()	JDK9新增，传入一个断言参数当第一次断言为false时停止，返回前面断言为true的元素。	否
     * dropWhile()	JDK9新增，传入一个断言参数当第一次断言为false时停止，删除前面断言为true的元素。	否
     *
     *
     * 终结型操作
     * 终结型操作与中间型相反，返回值是非Stream类型的。api如下：
     * API	功能说明
     * count()	返回stream处理后最终的元素个数
     * max()	返回stream处理后的元素最大值
     * min()	返回stream处理后的元素最小值
     * findFirst()	找到第一个符合条件的元素时则终止流处理
     * findAny()	找到任何一个符合条件的元素时则退出流处理，这个对于串行流时与findFirst相同，对于并行流时比较高效，任何分片中找到都会终止后续计算逻辑
     * anyMatch()	返回一个boolean值，类似于isContains(),用于判断是否有符合条件的元素
     * allMatch()	返回一个boolean值，用于判断是否所有元素都符合条件
     * noneMatch()	返回一个boolean值， 用于判断是否所有元素都不符合条件
     * *collect()	将流转换为指定的类型，通过Collectors进行指定
     * *reduce()	将一个Stream中的所有元素反复结合起来，得到一个结果
     * toArray()	将流转换为数组
     * iterator()	将流转换为Iterator对象
     * foreach()	无返回值，对元素进行逐个遍历，然后执行给定的处理逻辑
     **/
    @Test
    public void middleAndEndOperate() {
        // 中间操作
        Stream st = Arrays.asList(1, 2, 3, 4, 5).stream().filter(x -> {
            System.out.print(x);
            return x > 3;
        });
        // 终止操作没有便不会输出
        // st.forEach(t-> System.out.print(t));
    }


    // 常用操作
    //         中间操作:
    //         过滤 filter
    //         去重 distinct
    //         排序 sorted
    //         截取 limit、skip
    //         转换 map/flatMap
    //         其他 peek

    //         终止操作:
    //         循环 forEach
    //         计算 min、max、count、 average
    //         匹配 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny
    //         汇聚 reduce
    //         收集器 toArray collect
    @Test
    public void commonlyUsedOperate() {
        List<Student> studentList = new ArrayList<>();
        //
        studentList.add(new Student("黎  明", 20, new BigDecimal(80), 1));
        studentList.add(new Student("郭敬明", 22, new BigDecimal(90), 2));
        studentList.add(new Student("明  道", 21, new BigDecimal(65.5), 3));
        studentList.add(new Student("郭富城", 30, new BigDecimal(90.5), 4));
        studentList.add(new Student("刘诗诗", 20, new BigDecimal(75), 1));
        studentList.add(new Student("成  龙", 60, new BigDecimal(88), 5));
        studentList.add(new Student("郑伊健", 60, new BigDecimal(86), 1));
        studentList.add(new Student("刘德华", 40, new BigDecimal(81), 1));
        studentList.add(new Student("古天乐", 50, new BigDecimal(83), 2));
        studentList.add(new Student("赵文卓", 40, new BigDecimal(84), 2));
        studentList.add(new Student("吴奇隆", 30, new BigDecimal(86), 4));
        studentList.add(new Student("言承旭", 50, new BigDecimal(68), 1));
        studentList.add(new Student("郑伊健", 60, new BigDecimal(86), 1));
        studentList.add(new Student("黎  明", 20, new BigDecimal(80), 1));
        studentList.add(new Student("李连杰", 65, new BigDecimal(86), 4));
        studentList.add(new Student("周润发", 69, new BigDecimal(58), 1));
        studentList.add(new Student("徐若萱", 28, new BigDecimal(88), 6));
        studentList.add(new Student("许慧欣", 26, new BigDecimal(86), 8));
        studentList.add(new Student("陈慧琳", 35, new BigDecimal(64), 1));
        studentList.add(new Student("关之琳", 45, new BigDecimal(50), 9));
        studentList.add(new Student("温碧霞", 67, new BigDecimal(53), 2));
        studentList.add(new Student("林青霞", 22, new BigDecimal(56), 3));
        studentList.add(new Student("李嘉欣", 25, new BigDecimal(84), 1));
        studentList.add(new Student("彭佳慧", 26, new BigDecimal(82), 5));
        studentList.add(new Student("陈紫涵", 39, new BigDecimal(88), 1));
        studentList.add(new Student("张韶涵", 41, new BigDecimal(90), 6));
        studentList.add(new Student("梁朝伟", 58, new BigDecimal(74), 1));
        studentList.add(new Student("梁咏琪", 65, new BigDecimal(82), 7));
        studentList.add(new Student("范玮琪", 22, new BigDecimal(83), 1));
        // // 循环
        // studentList.stream().forEach(s -> System.out.println(s.getStuName()));
        // // 过滤
        // studentList.stream().filter(student -> student.getAge()>60).forEach(student -> System.out.println(student.getAge()));
        // studentList.stream().filter(student -> student.getScore().compareTo(new BigDecimal(80)) > 0).forEach(student -> System.out.println(student.getAge()));
        // //去重
        // studentList.stream().distinct().forEach(x -> System.out.println(x.getStuName()));
        // //单条件排序和多条件排序
        // studentList.stream().sorted(Comparator.comparing(Student::getScore)).forEach(x -> System.out.println(x.getStuName()));
        //
        // //多条件排序
        // studentList.stream().sorted(Comparator.comparing(Student::getScore).thenComparing(Student::getStuName)).forEach(x -> System.out.println(x.getStuName()));
        //
        // //分组 x->x.getAge()等同于Student::getAge
        // System.out.println(studentList.stream().collect(Collectors.groupingBy(Student::getAge,Collectors.counting())));
        //
        // // 分页
        // studentList.stream().skip(10).limit(5).forEach(x -> System.out.println(x.getStuName()));
        // //具体的分页
        // int pageIndex=1;
        // int pageSize=5;
        // studentList.stream().skip((pageIndex-1)*pageSize).limit(pageSize).forEach(x -> System.out.println(x.getStuName()));

        // // map转换 mapToInt,mapToDouble,flatMap(把子数组的值放到数组里面)
        // studentList.stream().map(Student::getScore).forEach(x -> System.out.println(x));
        // studentList.stream().flatMap(x-> Arrays.stream(x.getStuName().split(""))).forEach(x -> System.out.println(x));

        // //min、max、count、 average 统计函数
        // studentList.stream().max(Comparator.comparing(x -> x.getAge())).ifPresent(x-> System.out.println(x.getAge()));
        // studentList.stream().min(Comparator.comparing(x -> x.getAge())).ifPresent(x-> System.out.println(x.getAge()));
        // System.out.println(studentList.stream().count());
        studentList.stream().mapToDouble(x -> x.getScore().doubleValue()).average().ifPresent(x-> System.out.println(x));

        // // anyMatch、noneMatch、 allMatch、 findFirst、 findAny
        // // anyMatch: 操作用于判断Stream中的是否有满足指定条件的元素。如果最少有一个满足条件返回true，否则返回false。
        // // noneMatch: 与anyMatch相反。
        // // allMatch: 是判断所有元素是不是都满足表达式。
        // // findFirst: 操作用于获取含有Stream中的第一个元素的Optional，如果Stream为空，则返回一个空的Optional。若Stream并未排序，可能返回含有Stream中任意元素的Optional。
        // // findAny: 操作用于获取含有Stream中的某个元素的Optional，如果Stream为空，则返回一个空的Optional。由于此操作的行动是不确定的，其会自由的选择Stream中的任何元素。
        // // 在并行操作中，在同一个Stram中多次调用，可能会不同的结果。在串行调用时，都是获取的第一个元素， 默认的是获取第一个元素,并行是随机的返回。
        // System.out.println(studentList.stream().anyMatch(r -> r.getStuName().contains("伟")));
        // System.out.println(studentList.stream().allMatch(r -> r.getStuName().contains("伟")));
        // System.out.println(studentList.stream().noneMatch(r -> r.getStuName().contains("伟")));
        // System.out.println(studentList.stream().findFirst().get().getStuName());
        // System.out.println(studentList.stream().findAny().get().getStuName());
        //
        // for (int i=0;i<10;i++)
        // {
        //     System.out.println(studentList.stream().parallel().findAny().get().getStuName());
        // }

        // // reduce
        // // 是一个循环，有两个参数
        // // 第一次执行的时候x是第一个值，y是第二个值。
        // // 在第二次执行的时候，x是上次返回的值，y是第三个值 …. 直到循环结束为止。
        // Stream.of(1, 5, 10, 8).reduce((x, y) -> {
        //     System.out.println("x : " + x);
        //     System.out.println("y : " + y);
        //     System.out.println("x+y : " +x);
        //
        //     System.out.println("--------");
        //     return x + y;
        // });
        // //指定了初始值
        // Stream.of(1, 5, 10, 8).reduce(100,(x, y) -> {
        //     System.out.println("x : " + x);
        //     System.out.println("y : " + y);
        //     System.out.println("x+y : " +x);
        //     System.out.println("--------");
        //     return x + y;
        // });

        // // toArray、collect
        // // toArray和collect是两个收集器，toArray是把数据转换成数组，collect是转成其他的类型。这里就不在讨论了。
        // System.out.println(studentList.stream().collect(Collectors.groupingBy(x->x.getAge(),Collectors.counting())));

        // parallel() 把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。串行流则相反，并行流的底层其实就是ForkJoin框架的一个实现。
        // 留意装箱。自动装箱和拆箱操作会大大降低性能。Java8中有原始类型流（IntStream、LongStream、DoubleStream）来避免这种操作，但凡有可能都应该使用这些原始流。
        // 有些操作本身在并行流上的性能就比顺序流差。特别是limit、findFirst等依赖于元素顺序的操作，它们在并行流上执行的代价非常大。例如，findAny会比findFirst性能好，因为它不一定要顺序来执行。你总是可以调用unordered方法来把顺序流变成无须流。那么，如果你需要流中的n个元素而不是专门的前n个的话，对无序并行流调用limit可能会比单个有序流（比如数据源是List）更高效。
        // 还要考虑流的操作流水线的总计算成本。设N是要处理的元素的总数，Q是一个元素通过流水线的大致处理成本，则N*Q就是这个对成本的一个粗略的定型估计。Q值较高就意味着使用并行流时性能好的可能性比较大。
        // 对于较小的数据量，选择并行流几乎从来不都是一个好的选择。并行处理少数几个元素的好处还抵不上并行化造成的额外开销。
        // 要考虑流背后的数据结构是否易于分解。例如，ArrayList的拆分效率比LinkedList高很多，因为前者用不着遍历就可以平均拆分，而后者则必须遍历。另外，用range工厂方法创建的原始类型流也可以快速分解。最后，你可以自己实现Spliterator来完全掌握分解过程。
        // 流自身的特点，以及流水线中的中间操作修改流的方式，都可能会改变分解过程的性能。例如，一个SIZED流可以分成大小相等的两部分，这样每个部分都可以比较高效地并行处理，但筛选操作可能丢弃的元素个数却无法预测，导致流本身的大小未知。
        // https://blog.csdn.net/YCJ_xiyang/article/details/83652954
        // sequential() 将一个并行流转成顺序的流
        // stream.parallel() .filter(…) .sequential() .map(…) .parallel() .reduce();
    }

    public static void main(String[] args) {
        /*lambda方式*/
        eat e = () -> System.out.printf("hello\n");
        e.eatFood();
        /*匿名类方式*/
        eat e1 = new eat() {
            @Override
            public void eatFood() {
                System.out.printf("anoymous class\n");
            }
        };
        e1.eatFood();


    }

    @Test
    public void compare() {
        String[] array = new String[]{"Apple", "Orange", "Banana", "Lemon"};
        // 1. java 单方法接口
        Arrays.sort(array, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        System.out.println(array.toString());
        System.out.println(Arrays.toString(array));
        System.out.println(String.join(", ", array));
        // 2. 在Lambda中只要写出方法定义：
        // Arrays.sort(array, (s1, s2) -> {
        //     return s1.compareTo(s2);
        // });
        // s1与s2是参数，类型可以省略由编译器自动推断出。-> {} 表示方法体。
        // 如果只有一行可以省略return。
        // (s1, s2) -> { s1.compareTo(s2)}
        Arrays.sort(array, (s1, s2) -> {
            return s1.compareTo(s2);
        });
        System.out.println(String.join(", ", array));
        // 3.方法引用
        Arrays.sort(array, String::compareTo);
        System.out.println(String.join(", ", array));
    }

    // 可以将构造方法当作参数传入，用于构建新的对象。
    // 调用构造函数 `Person::new`，字符串作为参数，生成Person对象。
    @Test
    public void constrctor() {
        // jdk9
        // List<String> names = List.of("Bob", "Alice", "Tim");
        List<String> names = new ArrayList<>();
        names.add("Bob");
        names.add("Alice");
        names.add("Tim");
        List<Person> persons = names.stream().map(Person::new).collect(Collectors.toList());
        System.out.println(persons);
    }

    class Person {
        String name;

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString() {
            return "Person:" + this.name;
        }
    }

    public void application() {
        Person std = new Person();
        String result = "init";
        if (std != null) {
            if (std.getName() != null) {
                result = std.getName().toUpperCase();
            } else {
                result = "";
            }
        } else {
            result = "";
        }
        System.out.println(result);

        // std.map(x->x.getName()).map(x->x.toUpperCase()).orElse("");
    }


    public class Student {

        public Student(String stuName, int age, BigDecimal score, int clazz) {
            this.stuName = stuName;
            this.age = age;
            this.score = score;
            this.clazz = clazz;
        }

        private String stuName;
        private int age;
        private BigDecimal score;
        private int clazz;

        public String getStuName() {
            return stuName;
        }

        public void setStuName(String stuName) {
            this.stuName = stuName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public BigDecimal getScore() {
            return score;
        }

        public void setScore(BigDecimal score) {
            this.score = score;
        }

        public int getClazz() {
            return clazz;
        }

        public void setClazz(int clazz) {
            this.clazz = clazz;
        }
    }


}
