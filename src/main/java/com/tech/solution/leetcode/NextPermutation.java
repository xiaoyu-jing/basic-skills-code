package com.tech.solution.leetcode;

import java.util.*;

/**
 * @author jing1560
 * @data 2024/4/7
 *
 * 算法：下一个排列
 *
 */
public class NextPermutation {

    public static void main(String[] args){
//        System.out.println(Arrays.toString(nextPermutationSolution1(new int[]{3,2,1})));

        System.out.println(Arrays.toString(nextPermutationSolution1(new int[]{1,5,1})));
    }

    private static int[] nextPermutationSolution1(int[] nums){
        List<List<Integer>> res = generateAllPermutation(nums);
        List<Integer> nextList = new ArrayList<>();
        if(res.size() > 0){
            //List<List<Integer>> sortResList = sortList(res);
            int[] newArr = new int[nums.length];
            System.arraycopy(nums,0, newArr,0, nums.length);
            Arrays.sort(newArr);
            List<List<Integer>> sortResList = generateAllPermutation(newArr);
            /*Set<List<Integer>> tempSet = new TreeSet<>();
            sortResList.stream().forEach(list -> tempSet.add(list));
            List<List<Integer>> resultList = new ArrayList<>();
            tempSet.stream().forEach(set -> resultList.add(set));*/

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

    private static List<List<Integer>> sortList(List<List<Integer>> res) {
        // [[3, 2, 1], [3, 1, 2], [2, 3, 1], [2, 1, 3], [1, 3, 2], [1, 2, 3]]
        List<String> list = new ArrayList<>();
        for(int i = 0; i < res.size(); i++){
            /*for(int j = i; j < res.get(i).size(); j++){
                Collections.sort();
            }*/
            list.add(res.get(i).toString());
            System.out.println(res.get(i).toString());
        }
        Collections.sort(list);
        System.out.println(list);
        return null;
    }

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
}
