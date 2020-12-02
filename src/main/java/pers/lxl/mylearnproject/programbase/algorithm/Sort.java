package pers.lxl.mylearnproject.programbase.algorithm;

public class Sort {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //-1防止溢出
            for (int j = 0; j < arr.length - i - 1; j++) {
                //把最大的数放在后面
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

     static void quickSort(int[] a, int low, int hight) {
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        // 用子表的第一个记录做基准
        index = a[i];
        // 从表的两端交替向中间扫描
        while (i < j) {
            while (i < j && a[j] >= index) {
                j--;
            }
            // 用比基准小的记录替换低位记录
            if (i < j) {
                a[i++] = a[j];
            }
            while (i < j && a[i] < index) {
                i++;
            }
            // 用比基准大的记录替换高位记录
            if (i < j) {
                a[j--] = a[i];
            }
        }
        // 将基准数值替换回 a[i]
        a[i] = index;
        // 对低子表进行递归排序
        quickSort(a, low, i - 1);
        // 对高子表进行递归排序
        quickSort(a, i + 1, hight);

    }

    static void selectSort(int[] a) {
        int minIndex = 0;
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            minIndex = i;
            //无序区间最小数据数组下标
            for (int j = i + 1; j < a.length; j++) {
                //在无序区间找到最小下标并保存
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            //将最小元素放到本次循环的前端
            temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;


        }
    }


    static int[] insertSort(int []arr){
        for (int i = 1; i <arr.length ; i++) {
            for (int j = i; j >0 ; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    break;
                }

            }
        }
        return arr;
    }


    static void shellSort(int []arr){
        int d=arr.length;
        while(true){
            d=d/2;
            for (int x=0;x<d;x++){
                for (int i=x+d;i<arr.length;i=i+d){
                    int temp=arr[i];
                    int j;
                    for (j=i-d;j>=0&&arr[j]>temp;j=j-d){
                        arr[j+d]=arr[j];
                    }
                    arr[j+d]=temp;
                }
            }
            if(d==1){
                break;
            }
        }
    }

}
