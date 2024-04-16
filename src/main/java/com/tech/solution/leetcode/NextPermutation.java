package com.tech.solution.leetcode;

import java.util.*;

/**
 * @author jing1560
 * @data 2024/4/7
 *
 * 算法：下一个排列
 *
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 *    例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 1） 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 2） 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 3） 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 */
public class NextPermutation {

    public static void main(String[] args){
//        System.out.println(Arrays.toString(nextPermutationSolution1(new int[]{3,2,1})));

//        System.out.println("解法一：{1,5,1} 的下一个排列为：" + Arrays.toString(nextPermutationSolution1(new int[]{1,5,1})));

//        System.out.println("解法二：{1,5,1} 的下一个排列为：" + Arrays.toString(nextPermutationSolution2(new int[]{1,5,1})));
        System.out.println("解法二：{6,5,3,4,2,1} 的下一个排列为：" + Arrays.toString(nextPermutationSolution2(new int[]{6,5,3,4,2,1})));
    }

    /**
     * 解法一：先通过深度优先遍历计算出所给数组的全排列组合，然后将全排列组合按自然排序，然后取出 所给数组 的下一个排列
     *
     * 如果 所给数组 位于 全排列组合的最后一位，那下一个排列就是 全排列组合的第一位
     * @param nums
     * @return
     */
    private static int[] nextPermutationSolution1(int[] nums){
        List<List<Integer>> res = generateAllPermutation(nums);
        List<Integer> nextList = new ArrayList<>();
        if(res.size() > 0){
            int[] newArr = new int[nums.length];
            System.arraycopy(nums,0, newArr,0, nums.length);
            Arrays.sort(newArr);
            List<List<Integer>> sortResList = generateAllPermutation(newArr);

            for(int i = 0; i < sortResList.size(); i++){
                boolean flag = true;
                for(int j = 0; j < sortResList.get(i).size(); j++){
                    if(sortResList.get(i).get(j) != nums[j]){
                        flag = false;
                    }
                }
                if(flag){
                    // nums 在 res 中的索引为 i
                    if(i + 1 < sortResList.size()){
                        nextList = sortResList.get(i + 1);
                    } else {
                        nextList = sortResList.get(0);
                    }
                    break;
                }
            }
        }
        for(int k = 0; k < nextList.size(); k++){
            nums[k] = nextList.get(k);
        }
        return nums;
    }

    /**
     * 获取全排列的所有可能
     * @param nums
     * @return
     */
    private static List<List<Integer>> generateAllPermutation(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, path, used, res);
        return res;
    }

    private static void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res){
        if(depth == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]){
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            dfs(nums, depth + 1, path, used, res);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 解法二：交换
     *
     * [6,5,3,4,2,1] --> [6,5,3,1,2,4] --> [6,5,4,1,2,3]
     * @param nums
     * @return
     */
    private static int[] nextPermutationSolution2(int[] nums){
        if(nums.length == 0){
            return nums;
        }
        int len = nums.length;
        //从后向前遍历
        for(int i = len - 1; i >= 0; i--){
            //如果i为0，说明数组从后到前是递增（654321）的,没有更大的了
            //直接重排序变成一个递减的（123456）符合题意
            if(i == 0){
                Arrays.sort(nums);
                return nums;
            }else if(nums[i] > nums[i-1]){
                // 如果后者比前者大
                // 一旦出现后一个数字nums[i]比前一个大，说明存在更大的整数。 比如 [1,2,3]中的 3 > 2，则 132 > 123
                // 对nums[i]及后面的数组排序，从小到大。 （因为要寻找 下一个排列，所以 紧挨着 nums[i] 后面的数字一定是个最小序列）
                Arrays.sort(nums, i, len);
                for(int j = i; j < len; j++){
                    //由于从i开始后面已经排序
                    //那么保证获得比 nums[i-1] 大的数，是 [i,...,len-1] 中最大的,交换即可
                    if(nums[j] > nums[i-1]){
                        swap(nums,j,i-1);
                        return nums;
                    }
                }
            }
        }
        return nums;
    }

    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
