package pers.lxl.mylearnproject.javase.javabase;

import java.util.Arrays;

public class HelloWord {
    public static void main(String[] args) {
//        【基础】-------------------------------
        double d = 3.1415926;
//        pringtf格式化输出
        System.out.printf("%.2f\n", d);
//        【数组】-------------------------------
        int[] array = {21, 12, 5, 3, 2, 3, 556, 778, 1};
//        foreach变量直接取数组元素，而不是for中取的索引
        for (int n : array) {
            System.out.println(n);
        }
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
//       冒泡排序
        System.out.println("排序前："+ Arrays.toString(array));
        for (int i = 0; i <array.length-1 ; i++) {
            for (int j = 0; j <array.length-i-1 ; j++) {
                if (array[j]>array[j+1]){
                    int tmp=array[j];
                    array[j]=array[j+1];
                    array[j + 1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(array));













    }
}
