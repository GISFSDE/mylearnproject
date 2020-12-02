package pers.lxl.mylearnproject.programbase.algorithm;

import java.util.Arrays;

public class HelloAlgorithm {
    static int []arr={1,5,9,6,4,8,2,3};

    public static void main(String[] args) {
        Sort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        Sort.quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        Sort.selectSort(arr);
        System.out.println(Arrays.toString(arr));
        Sort.insertSort(arr);
        System.out.println(Arrays.toString(arr));
        Sort.shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
