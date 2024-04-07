package com.tech.solution.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jing1560
 * @data 2024/1/15
 */
public class LeetCodeCommon {

    public static void main(String[] args){
         //longSubStringTest();

        //三数之和
        //threeSum();

        //全排列
        System.out.println("{1,2,3} 全排列：" + permute(new int[]{1,2,3}));
        System.out.println("{3,2,1} 全排列：" + permute(new int[]{3,2,1}));
        System.out.println("{2,3,1} 全排列：" + permute(new int[]{2,3,1}));
        System.out.println("{1,5,1} 全排列：" + permute(new int[]{1,5,1}));
        System.out.println("{1,1,5} 全排列：" + permute(new int[]{1,1,5}));

        //Arrays.asList方法的 bug测试
//        arrayListTest();

    }

    public static void longSubStringTest(){
        String str = "abcdefg";
        String substring = str.substring(0,3);
        System.out.println(substring);

        System.out.println(str.substring(2));

        String[] setStr = str.split("");
        Set<String> setArray = new HashSet<>();
        Arrays.stream(setStr).forEach(ss -> setArray.add(ss));
    }

    /**
     * 三数之和
     * @return
     */
    public static List<List<Integer>> threeSum() {

        int[] nums = {-1,0,1,2,-1,-4};

        List<List<Integer>> threeSumList = new ArrayList<>();
        Arrays.sort(nums);
        if(nums.length < 3){
            return threeSumList;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            Integer first = nums[i];
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            Set<Integer> set = new HashSet<>();
            for(int j=i+1;j< nums.length;j++){
                Integer third = nums[j];
                Integer second = -(first + third);
                if(set.contains(second)){
                    threeSumList.add(new ArrayList<>(Arrays.asList(first,third,-(first + third))));

                    //这个while记不住的话，可以不写
                    while(j<nums.length-1 && nums[j] == nums[j + 1]){
                        j++;
                    }

                }
                set.add(third);
            }
        }
        return threeSumList;
    }

    /**
     * 全排列
     * @return
     */
    public static List<List<Integer>> permute(int[] nums){

        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if(len == 0){
            return  res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums,len,0,path,used,res);
        return res;
    }

    /**
     * 深度优先遍历
     * @param nums
     * @param len
     * @param depth
     * @param path
     * @param used
     * @param res
     */
    private static void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        //当递归的层数等于数组元素个数时，结束递归
        if(depth == len){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=0; i<len; i++){
            if(used[i]){
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums,len,depth + 1,path,used,res);
            //递归之后，需要回到上一层节点，之前做了什么操作，之后就要撤销什么操作（这就是 回溯）
            path.removeLast();
            used[i] = false;
        }
    }

    /**
     * Arrays.asList方法的 bug测试
     *
     * 坑 1：不能直接使用 Arrays.asList 来转换基本类型数组
     * 坑 2：Arrays.asList 返回的 List 不支持增删操作
     * 坑 3：对原始数组的修改会影响 Arras.asList 返回的那个 List
     */
    private static void arrayListTest(){
        int[] array1 = {1,2,3,4,5};
        //List<int[]> arrayList = Arrays.asList(array1);
        List arrayList = Arrays.asList(array1);
        System.out.printf("arrayList:%s ,size:%s ,class:%s%n", arrayList, arrayList.size(),arrayList.get(0).getClass());

        //Java8 包装转换
        List<Integer> collect = Arrays.stream(array1).boxed().collect(Collectors.toList());
        System.out.printf("【Java8】collect:%s ,size:%s ,class:%s%n", collect, collect.size(),collect.get(0).getClass());

        Integer[] array2 = {1,2,3,4,5};
        List arrayList2 = Arrays.asList(array2);
        System.out.printf("arrayList2:%s ,size:%s ,class:%s%n", arrayList2, arrayList2.size(),arrayList2.get(0).getClass());

        List<Integer> arrayList3 = new ArrayList<>();
        arrayList3.add(1);
        arrayList3.add(2);
        System.out.printf("arrayList3:%s ,size:%s ,class:%s%n", arrayList3, arrayList3.size(),arrayList3.get(0).getClass());


        String[] array4 = {"1","2","3","4"};
        List arrayList4 = Arrays.asList(array4);
        //增加数据 --- 报错
        try {
            arrayList4.add(6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(String.format("增加数据  array4:%s, arrayList4:%s", Arrays.toString(array4),arrayList4));

        String[] array5 = {"1","2","3","4"};
        List arrayList5 = Arrays.asList(array5);
        //编辑数据 --- 数组 和 List 均被修改
        array5[0] = "8";
        System.out.println(String.format("编辑数据  array5:%s, arrayList5:%s", Arrays.toString(array5),arrayList5));

    }

}
