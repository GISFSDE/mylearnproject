package pers.lxl.mylearnproject.programbase.algorithm.find;

import pers.lxl.mylearnproject.programbase.algorithm.sort.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**查找算法*/
public class Find {
/**顺序查找
 * 线性查找，直接遍历，时间复杂度为O(n)*/
public static int sequenceSearch(int[] nums,int key){
    for(int i=0;i<nums.length;i++){
        int now=nums[i];
        if(now==key){
            return i;
        }
    }
    return -1;
}

/**二分查找
 *前提条件是需要有序表顺序存储，对于静态查找表，一次排序后不再变化，折半查找能得到不错的效率。频繁执行插入或删除操作的数据集，不建议使用 */
public static int binarySearch(int[] nums,int key){
    int length=nums.length;
    Sort.quickSort(nums, 0, length-1);
    //begin=0,end=length-1
    int begin=0;
    int end=length-1;
    //循环，直到end<begin,返回-1
    while(begin<=end){
        int mid=(begin+end)/2;
        int now=nums[mid];
        if(now==key){
            //如果相同，返回index
            return mid;
        }
        if(now<key){
            //如果mid<key,那么begin=mid+1
            begin=mid+1;
        }
        if(now>key){
            //如果mid>key,那么end=mid-1
            end=mid-1;
        }
    }
    return -1;
}
/**插值查找
 * 基于二分查找，将查找点改为自适应，时间复杂度均为O(log2 (log2 n))*/
public static int insertionSearch(int[] nums,int key){
    int length=nums.length;
    Sort.quickSort(nums, 0, length-1);
    //begin=0,end=length-1
    int begin=0;
    int end=length-1;
    //循环，直到end<begin,返回-1
    while(begin<=end){
        int mid=begin+(key-nums[begin])/(nums[end]-nums[begin])*(end-begin);
        int now=nums[mid];
        if(now==key){
            //如果相同，返回index
            return mid;
        }
        if(now<key){
            //如果mid<key,那么begin=mid+1
            begin=mid+1;
        }
        if(now>key){
            //如果mid>key,那么end=mid-1
            end=mid-1;
        }
    }
    return -1;
}
/**斐波那契查找
 *黄金比例又称黄金分割，其比值约为1:0.618或1.618:1。
 * 斐波那契数列：1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89…….（从第三个数开始，后边每一个数都是前两个数的和）。
 * 然后我们会发现，随着斐波那契数列的递增，前后两个数的比值会越来越接近0.618，
 * 利用这个特性，我们就可以将黄金比例运用到查找技术中。时间复杂度为O(log2 n) */
public static int fibonacciSearch(int[] nums,int key){
    int orginalLength=nums.length;
    Sort.quickSort(nums, 0, orginalLength-1);
    //然后建立斐波那契数列，用list不断加入，直到>orginalLength
    List<Integer> fiboList=new ArrayList<>();
    fiboList.add(0);
    fiboList.add(1);
    int i=2;
    while(true){
        Integer now=fiboList.get(i-2)+fiboList.get(i-1);
        fiboList.add(now);
        i++;
        if(now>orginalLength){
            break;
        }
    }
    //斐波那契数列的最后一位F(k),newLength=F(k)-1>=orginalLength
    int k=fiboList.size()-1;
    int newLength=fiboList.get(k)-1;
    //将原来排序后的数组加入长度为newLength的数组中， 多出来空位的用最后一位填满
    int[] newNums=new int[newLength];
    for(i=0;i<newLength;i++){
        if(i<orginalLength){
            newNums[i]=nums[i];
        }
        else{
            newNums[i]=nums[orginalLength-1];
        }
    }

    //begin=0,end=newLength-1,k=上面对应的k
    int begin=0;
    int end=newLength-1;
    //循环，直到end<begin,返回-1
    while(begin<=end){
        int mid=begin+fiboList.get(k-1)-1;
        int now=nums[mid];
        if(now==key){
            //如果相同，返回index,如果index>=orginalLength,返回orginalLength-1
            if(mid>=orginalLength){
                return orginalLength-1;
            }
            return mid;
        }
        if(now<key){
            //如果mid<key,那么begin=mid+1，k=k-2
            begin=mid+1;
            k=k-2;
        }
        if(now>key){
            //如果mid>key,那么end=mid-1,k=k-1
            end=mid-1;
            k=k-1;
        }
    }
    return -1;
}
/**二叉树查找
 * 二叉查找树是先对待查找的数据进行生成树，确保树的左分支的值小于右分支的值，然后在就行和每个节点的父节点比较大小，查找最适合的范围。这个算法的查找效率很高，但是如果使用这种查找方法要首先创建树。
 * 二叉查找树（BinarySearch Tree，也叫二叉搜索树，或称二叉排序树Binary Sort Tree）或者是一棵空树，或者是具有下列性质的二叉树：
 * 1）若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 2）若任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 3）任意节点的左、右子树也分别为二叉查找树。
 * 二叉查找树性质：对二叉查找树进行中序遍历，即可得到有序的数列。
 * 根据二叉查找树的性质，可以从根，根据大小比较到左右孩子，搜索到对应的节点*/



/**分块查找
 * 经常增加或者减少的数据，是二分查找和顺序查找的改进
 * 无需有序，同时比顺序查找快
 * 思想：将n个数据元素"按块有序"划分为m块（m ≤ n）。每一块中的结点不必有序，
 * 但块与块之间必须"按块有序"；即第1块中任一元素的关键字都必须小于第2块中任一元素的关键字
 * step1 先选取各块中的最大关键字构成一个索引表；
 * step2 查找分两个部分：先对索引表进行二分查找或顺序查找，以确定待查记录在哪一块中；然后，在已确定的块中用顺序法进行查找。*/
public static boolean blockSearch(int[] nums,int key){
    int length=nums.length;
    int max=nums[0];
    int min=nums[0];
    //遍历nums数组，得到min和max
    for(int i=0;i<length;i++){
        int now=nums[i];
        if(now>max){
            max=now;
        }
        if(now<min){
            min=now;
        }
    }
    //区块数量blockNum=length的开方
    int blockNum=(int)Math.sqrt(length);
    //根据blockNum分块,建立index数组，代表每个区块的min数字
    //interval=(max-min)/blockNum
    //分为min，min+interal*1,...min+interal*(blockNum-1)
    int[] index=new int[blockNum];
    double interval=(max-min)/(double)blockNum;
    for(int i=0;i<blockNum;i++){
        index[i]=(int)(min+interval*i);
    }


    //初始化list数组及其中的arraylist
    List<ArrayList<Integer>> list=new ArrayList<>();
    for(int i=0;i<blockNum;i++){
        list.add(new ArrayList<>());
    }

    for(int i=0;i<length;i++){
        int now=nums[i];
        //每个数对应的区块的index用二分法在index数组中查询
        int indexNow=getIndex(index, now);
        //在对应区块中加入数字
        list.get(indexNow).add(now);
    }

    //数对应的区块的index用二分法在index数组中查询
    int indexNow=getIndex(index, key);
    //在区块中顺序查找
    for(Integer now:list.get(indexNow)){
        if(now==key){
            return true;
        }
    }


    return false;
}

    /** 得到key对应index里的位置<br>
     * index[i+1]>key>=index[i]
     * @param index
     * @param key
     * @return 如果找不到，返回0
     */
    public static int getIndex(int[] index,int key){
        int begin=0;
        int end=index.length-1;
        //循环，直到end<begin,返回-1
        while(begin<=end){
            int mid=(begin+end)/2;
            int now=index[mid];
            if(mid==index.length-1&&key>=now){
                //如果是最后一个，返回index
                return mid;
            }
            if(key>=now&&key<index[mid+1]){
                //index[i+1]>key>=index[i]
                return mid;
            }
            if(now<key){
                //如果mid<key,那么begin=mid+1
                begin=mid+1;
            }
            if(now>key){
                //如果mid>key,那么end=mid-1
                end=mid-1;
            }
        }
        return 0;

    }

    /**哈希查找
     * */
    public static int hashSearch(int[] nums,int key){
        int length=nums.length;
        HashMap<Integer, Integer> map=new HashMap<>();
        for(int i=0;i<length;i++){
            map.put(nums[i], i);
        }
        Integer index=map.get(key);
        if(index==null){
            index=-1;
        }
        return index;
    }

    public static void main(String[] args) {
        /*总结*/
//        平均时间复杂度	         最差查找时间     	    查找前提	    前提时间复杂度	    插入新值时间
//顺序查找	O(n)	              O(n)	                无	        无	            O(1)
//二分查找	O(log2 n)	          O(log2 n)	            排序	        O(nlog2 n)	    O(n)
//插值查找	O(log2 (log2 n))	    O(log2 (log2 n))    排序	        O(nlog2 n)	    O(n)
//斐波那契查找 O(log2 n)	             O(log2 n)	    排序+斐波那契数列	O(nlog2 n)	    O(n)
//二叉树查找	O(log2 n)	            O(n)	         创建二叉树	    O(nlog2 n)	    O(log2 n)
//2-3树(k在2-3之间） O(logk n)	    O(logk n)	    创建2-3树	    O(nlogk n)	    O(logk n)
//红黑树   	O(log2 n)	             O(2log2 n)	    创建红黑树	    O(nlog2 n)	    O(log2 n)
//B树和B+树（m为阶数）	O(log m/2 n/2)	O(log m/2 n/2)	创建B树和B+树	    O(nlog m n)	    O(log m n)
//区块查找(b为块数）	O(log2 b +n/b)	O(log2 b +n/b)	创建区块	        O(nlog2 b)	    O(log2 b)
//哈希查找(k为一格的链表长度）	O(1)	O(k)	        创建哈希表	        O(n)	    O(1)

        int[] nums= {1,2,8,9,6,1,4};
//        System.out.println(sequenceSearch(nums,4));
//        System.out.println(binarySearch(nums,4));//排序后的位置
//        System.out.println(insertionSearch(nums,4));
//        System.out.println(fibonacciSearch(nums,4));
//        System.out.println(blockSearch(nums,4));
//        System.out.println(blockSearch(nums,13));
        System.out.println(hashSearch(nums,4));
    }

}
