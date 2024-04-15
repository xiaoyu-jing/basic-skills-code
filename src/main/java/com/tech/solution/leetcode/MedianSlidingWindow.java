package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/4/9
 *
 * 算法：滑动窗口中位数
 * 难度：困难
 *
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 *
 * 例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 *
 * 示例：
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 *
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 */
public class MedianSlidingWindow {

    public static void main(String[] args){
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));

        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,2,3,4,2,3,1,4,2}, 3)));

        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,4,2,3}, 4)));

        /**
         * 在Java中，int 是一种有符号的32位整数类型，其值的范围是从 -2147483648 到 2147483647。这是因为int类型使用二进制补码（two's complement）表示法来存储负数。
         * 当你试图将一个两个最大的int值（2147483647）相加时，你实际上是在尝试存储超出int类型最大范围的值（2147483647 + 2147483647 = 4294967294），这会导致整数溢出（integer overflow）。
         * 在Java中，当整数溢出时，它不会抛出异常或错误，而是会“环绕”到int类型的范围内。这是通过模数运算实现的，模数是2^32（即4294967296）。所以，当你尝试存储4294967294到一个int变量时，你会得到-2（4294967294 % 4294967296 = -2）
         */
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{2147483647,2147483647}, 2)));

    }

    private static double[] medianSlidingWindow(int[] nums, int k){
        int mode = k % 2;
        // 从题目中推断，数组长度为8，k为3，返回的中位数个数为 8-3+1
        double[] medianArr = new double[nums.length - k + 1];
        if(mode == 0){
            // k 为偶数的处理逻辑
            // 循环结束的边界是 i <= nums.lenght - k, 如果再往后滑动 就数组越界了
            for(int i = 0; i <= nums.length - k; i++){
                // 定义单次滑动窗口数组
                int[] newArr = new int[k];   // TODO 创建数组的动作可以移到 for 循环的外面，因为每次 拷贝 数组的时候，都是从 0 拷贝，可以直接覆盖
                System.arraycopy(nums, i, newArr, 0, k);
                // 必须新建一个数组，然后对新数组排序，不能直接排原数组，会出现结果错乱的情况
                Arrays.sort(newArr);
                int midA = newArr.length / 2;
                int midB = midA - 1;
                // 直接用 int 除，会得到整型的结果，此处需要 强转为 double 即可，且 必须给每一个元素强转，防止 int值超过 2147483647 时，求和后不能得到正确的结果，得到的是 负数 （二进制补码）
                double mid = ((double)newArr[midA] + (double)newArr[midB]) / 2;
                medianArr[i] = mid;
            }
        } else {
            // k 为奇数的处理逻辑
            for(int j = 0; j <= nums.length - k; j++){
                int[] newArr = new int[k];  // TODO 创建数组的动作可以移到 for 循环的外面，因为每次 拷贝 数组的时候，都是从 0 拷贝，可以直接覆盖
                System.arraycopy(nums, j, newArr, 0, k);
                Arrays.sort(newArr);
                int mid = (newArr.length - 1) / 2;
                medianArr[j] = (double) newArr[mid];
            }
        }
        return medianArr;
    }
}
