package pers.lxl.mylearnproject.programbase.newjdk;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * JDK8 新特性stream（）
 * java.util.stream
 * 以声明的方式处理数据，类似用sql从数据库查询数据的方式来提供对集合的运算和表达的高阶抽象
 * 其将要处理的数据看成一种流，流在管道中传输，其可以再管道的节点中进行筛选排序聚合等一系列操作
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 * 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 * 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 * 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 * 和以前的Collection操作不同， Stream操作还有两个基础的特征：
 * Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。这样做可以对操作进行优化，比如延迟执行(laziness)和短路(short-circuiting)。
 * 内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。Stream提供了内部迭代的方式，通过访问者模式(Visitor)实现。
 * 参考：
 * https://www.runoob.com/java/java8-streams.html
 * https://blog.csdn.net/QiuHaoqian/article/details/120942134
 * @author lxl
 */
public class StreamL {
    public static void main(String[] args) throws IOException {


//      =======生成流=======

//        1、通过集合生成，应用中最常用的一种
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream = integerList.stream();
//        2、通过数组生成
//        通过Arrays.stream方法生成流，并且该方法生成的流是数值流【即IntStream】而不是 Stream。补充一点使用数值流可以避免计算过程中拆箱装箱，提高性能。
//        Stream API提供了mapToInt、mapToDouble、mapToLong三种方式将对象流【即Stream 】转换成对应的数值流，同时提供了boxed方法将数值流转换为对象流.
        int[] intArr = {1, 2, 3, 4, 5, 6};
        IntStream streamArr = Arrays.stream(intArr);
//        3、通过值生成，通过Stream的of方法生成流，通过Stream的empty方法可以生成一个空流.
        Stream<Integer> streams = Stream.of(1, 2, 3, 4, 5, 6);
//        4、通过文件生成,通过Files.line方法得到一个流，并且得到的每个流是给定文件中的一行.
        Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset());
//        5、通过函数生成
//        iterate方法接受两个参数，第一个为初始化值，第二个为进行的函数操作，因为iterator生成的流为无限流，通过limit方法对流进行了截断，只生成5个偶数。
        Stream<Integer> streamIterate = Stream.iterate(0, n -> n + 2).limit(5);
//        generate方法接受一个参数，方法参数类型为Supplier ，由它为流提供值。generate生成的流也是无限流，因此通过limit对流进行了截断。
        Stream<Double> streamGenerate = Stream.generate(Math::random).limit(5);







//      =======操作流=========
//       1、中间操作
//        一个流可以后面跟随零个或多个中间操作。其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。
//        这类操作都是惰性化的，仅仅调用到这类方法，并没有真正开始流的遍历，真正的遍历需等到终端操作时，
//        常见的中间操作：filter、map、distinct、limit、skip、flatMap、allMatch、anyMatch、noneMatch等。
//       2、终端操作
//        一个流有且只能有一个终端操作，当这个操作执行后，流就被关闭了，无法再被操作，因此一个流只能被遍历一次，若想在遍历需要通过源数据在生成流。
//        终端操作的执行，才会真正开始流的遍历。如下面即将介绍的 count、collect、findFirst、findAny、reduce、averagingxxx、summarizingxxx 、foreach、joining 、groupingBy 、partitioningBy    等。
        //stream() − 为集合创建串行流。
        //parallelStream() − 为集合创建并行流。
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        List<String> filteredParallelStream = strings.parallelStream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

//      2.聚合操作
//      迭代数据foreach(),limit() 获取指定数量的流,sorted() 对流进行排序。
        Random random = new Random();
//        limit的参数值必须 >=0，否则将会抛出异常
        random.ints().limit(10).sorted().forEach(System.out::println);
//       map() 映射每个元素到对应的结果
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//      获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
//      filter() 通过设置的条件过滤出元素,结合lambda
//      获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
//        并行（parallel）程序
//        parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
//      获取空字符串的数量


//        3.Collectors
//        Collectors 类实现归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

//        4.统计
//        一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());


    }
}
