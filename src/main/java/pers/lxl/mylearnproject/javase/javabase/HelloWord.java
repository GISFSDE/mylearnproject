package pers.lxl.mylearnproject.javase.javabase;

import pers.lxl.mylearnproject.programbase.algorithm.sort.Sort;

import java.util.Arrays;
/**存在继承的情况下，初始化顺序为：

 父类（静态变量、静态语句块）
 子类（静态变量、静态语句块）
 父类（实例变量、普通语句块）
 父类（构造函数）
 子类（实例变量、普通语句块）
 子类（构造函数）*/
public class HelloWord {
    public static void main(String[] args) {
//        【基础】-------------------------------
//1/4=0 why ?
        System.out.println(1/4);

        double d = 3.1415926;
//        pringtf格式化输出
        System.out.printf("%.2f\n", d);
//        【数组Array】-------------------------------
        int[] array = {21, 12, 5, 3, 2, 3, 556, 778, 1};
//        foreach变量直接取数组元素，而不是for中取的索引
        for (int n : array) {
            System.out.println(n);
        }
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
//       冒泡排序
        System.out.println("排序前：" + Arrays.toString(array));
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = 0; j < array.length - i - 1; j++) {
//                if (array[j] > array[j + 1]) {
//                    int tmp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = tmp;
//                }
//            }
//        }
        Sort.bubbleSort(array);
        String[] array2 = {"dwdw", "wwddd", "adwddddd"};
        Arrays.sort(array2);
        System.out.println(Arrays.toString(array));
       // System.out.println(array2);//直接打印数组名显示数组本身对应的哈希码（内存地址）
        System.out.println(Arrays.toString(array2));
//      【多维数组】-------------------------------
        int[][] dbarray = {{21, 232, 12}, {23, 32, 556}};
        int[][][] tbarray = {
                {{221, 454, 76, 8}, {21, 232, 12}, {23, 32, 556}},
                {{221, 454, 76, 8}, {21, 232, 12}, {23, 32, 556}},
                {{221, 454, 76, 8}, {21, 232, 12}, {23, 32, 556}}
        };
//        打印
        for (int[] arr : dbarray
        ) {
            for (int n : arr
            ) {
                System.out.print(n);
                System.out.print(",");
            }
            System.out.println();
        }
        System.out.println(Arrays.deepToString(dbarray)+"  "+Arrays.deepToString(tbarray));





    }
}
