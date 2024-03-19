package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/19
 * 数组中第K个最大元素（要求时间复杂度为 O(n)）
 */
public class FindKthLargest {

    public static void main(String[] args){
        findKth();
    }

    public static void findKth(){
        int[] arr = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        //写法1： 时间复杂度为 O(nlogn)
        int result1 = Arrays.stream(arr).sorted().skip(arr.length - k).min().getAsInt();
        System.out.println("写法1：" + result1);

        //写法2：时间复杂度为 O(nlogn)
        Arrays.sort(arr);
        System.out.println("写法2：" + arr[arr.length - k]);

        //写法3： 桶排序  O(n)
        int num[] = {3,2,3,1,2,4,5,5,6};
        //-10^4 <= nums[i] <= 10^4  10^4是10000，前后各10000，一共20000
        int[] buckets = new int[20001];
        for(int i = 0; i < num.length; i++){
            // 此处取10000，是因为 每一段都是 10^4  10^4是10000
            buckets[num[i] + 10000]++;
        }
        for(int i = 20000; i >= 0; i--){
            k = k - buckets[i];
            if(k <= 0){
                System.out.println("写法3：" + (i - 10000));
                break;
            }
        }
    }
}
