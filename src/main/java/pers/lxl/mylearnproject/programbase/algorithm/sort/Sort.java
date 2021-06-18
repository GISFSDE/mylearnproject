package pers.lxl.mylearnproject.programbase.algorithm.sort;

import java.util.*;

/**
 * @author lxl
 */
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

    /**
     * 快速排序
     * 从数列中挑出一个元素，称为 "基准"（pivot）;
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     */
    public static void quickSort(int[] a, int low, int hight) {
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

    /**
     * 选择排序，选择最小的放前面
     */
    public static void selectSort(int[] a) {
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

    /**
     * 插入排序，依次将元素插入到合适的位置
     */
    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
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

    /**
     * 希尔排序，选择增量，增量两端比较交换，然后增量逐渐缩小
     */
    public static void shellSort(int[] arr) {
        int d = arr.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < arr.length; i = i + d) {
                    int temp = arr[i];
                    int j;
                    for (j = i - d; j >= 0 && arr[j] > temp; j = j - d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }
    }

    public static void shellSort1(int[] arr) {
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }

    /**
     * 归并排序，对半分别归并排序，两半一起排序并合并
     */
    public static int[] mergeSort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    protected static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }


    /**
     * 堆排序*/

    /**
     * 创建堆
     */
    private static void heapSort(int[] arr) {
        //1.创建堆
        //2.转换为大顶堆或者小顶堆
        //3.将堆顶与堆尾进行互换，并断开堆尾
        //4.重复2、3知道剩下一个堆顶元素
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            //将堆顶元素与末尾元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整堆
     *
     * @param arr    待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
    private static void adjustHeap(int[] arr, int parent, int length) {
        //将temp作为父节点
        int temp = arr[parent];
        //左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            //右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= arr[lChild]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            arr[parent] = arr[lChild];

            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }


    /**
     * 计数排序
     */
    public static void countSort(int[] array) {
        //获取数组的最大值，数组所有值都在0 - max之间
        int max = getMax(array);
        //创建一个max+1大小的数组用于表示从0 - max所有数字的重复次数
        int[] countArray = new int[max + 1];
        int[] resultArray = new int[array.length];
        //用于存储排好序的数组
        System.arraycopy(array, 0, resultArray, 0, array.length);
        //循环array数组
        for (int i = 0; i < array.length; i++) {
            //因为countArray的下标代表array中的数字，而值代表array中元素的出现次数，所以countArray[array[i]]++
            countArray[array[i]]++;
        }
        for (int i = 1; i < countArray.length; i++) {
            //将countArray中的每一个元素变成与前一个元素相加的和
            countArray[i] = countArray[i] + countArray[i - 1];
        }
        for (int i = 0; i < resultArray.length; i++) {
            //从array的最后一位开始依次放入resultArray中，放入的位置是 --countArray[array[i]]
            array[--countArray[resultArray[i]]] = resultArray[i];
        }
    }

    private static int getMax(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                throw new RuntimeException("数组中有数小于0");
            }
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * 桶排序
     */
    public static void bucketSort(int[] arr) {
        //分桶，这里采用映射函数f(x)=x/10。
        //输入数据为0~99之间的数字
        int bucketCount = 10;
        Integer[][] bucket = new Integer[bucketCount][arr.length];  //Integer初始为null,以与数字0区别。
        for (int i = 0; i < arr.length; i++) {
            int quotient = arr[i] / 10;   //这里即是使用f(x)
            for (int j = 0; j < arr.length; j++) {
                if (bucket[quotient][j] == null) {
                    bucket[quotient][j] = arr[i];
                    break;
                }
            }
        }
        //小桶排序
        for (int i = 0; i < bucket.length; i++) {
            //insertion sort
            for (int j = 1; j < bucket[i].length; ++j) {
                if (bucket[i][j] == null) {
                    break;
                }
                int value = bucket[i][j];
                int position = j;
                while (position > 0 && bucket[i][position - 1] > value) {
                    bucket[i][position] = bucket[i][position - 1];
                    position--;
                }
                bucket[i][position] = value;
            }

        }
        //输出
        for (int i = 0, index = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i].length; j++) {
                if (bucket[i][j] != null) {
                    arr[index] = bucket[i][j];
                    index++;
                } else {
                    break;
                }
            }
        }
    }


    /**基数排序*/
    /**
     * 高位优先法
     *
     * @param arr 待排序列，必须为自然数
     */
    private static void radixSort(int[] arr) {
        //待排序列最大值
        int max = arr[0];
        int exp;//指数

        //计算最大值
        for (int anArr : arr) {
            if (anArr > max) {
                max = anArr;
            }
        }

        //从个位开始，对数组进行排序
        for (exp = 1; max / exp > 0; exp *= 10) {
            //存储待排元素的临时数组
            int[] temp = new int[arr.length];
            //分桶个数
            int[] buckets = new int[10];

            //将数据出现的次数存储在buckets中
            for (int value : arr) {
                //(value / exp) % 10 :value的最底位(个位)
                buckets[(value / exp) % 10]++;
            }

            //更改buckets[i]，
            for (int i = 1; i < 10; i++) {
                buckets[i] += buckets[i - 1];
            }

            //将数据存储到临时数组temp中
            for (int i = arr.length - 1; i >= 0; i--) {
                temp[buckets[(arr[i] / exp) % 10] - 1] = arr[i];
                buckets[(arr[i] / exp) % 10]--;
            }

            //将有序元素temp赋给arr
            System.arraycopy(temp, 0, arr, 0, arr.length);
        }

    }

    public static void main(String[] args) throws Exception {
        int[] arr = {1, 5, 9, 6, 4, 8, 2, 3};
//        Sort.bubbleSort(arr);
        Sort.quickSort(arr, 0, arr.length - 1);
//        Sort.selectSort(arr);
//        Sort.insertSort(arr);
//        Sort.shellSort(arr);
//        Sort.shellSort1(arr);
//        Sort.heapSort(arr);
//        Sort.countSort(arr);
//        Sort.bucketSort(arr);
//          Sort.radixSort(arr);
//        Sort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
