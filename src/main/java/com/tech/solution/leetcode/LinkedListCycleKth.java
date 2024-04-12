package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/3
 *
 * 字节 算法：链表向右循环K个数
 *
 * 示例：
 * 输入：node = [2,3,-2,4]  k = 2
 * 输出：node = [-2,4,2,3]
 */
public class LinkedListCycleKth {

    public static class LinkedNode {
        int value;
        LinkedNode next;
        public LinkedNode(int _value){
            this(_value,null);
        }
        public LinkedNode(int _value, LinkedNode _next){
            this.value = _value;
            this.next = _next;
        }

        public String toString(){
            StringBuilder builder = new StringBuilder();
            LinkedNode tempNode = this;
            while (tempNode != null){
                if(builder.length() > 0){
                    builder.append(",");
                }
                builder.append(tempNode.value);
                tempNode = tempNode.next;
            }
            return builder.toString();
        }
    }

    public static void main(String[] args){
        LinkedNode node = initNode(new int[]{2,3,-2,4}, 0);
        System.out.println(cycleKthSolution1(node, 2).toString());
    }

    /**
     * 初始化节点数据
     * @param nums
     * @param count
     * @return
     */
    private static LinkedNode initNode(int[] nums, int count){
        if(count == nums.length){
            return null;
        }
        LinkedNode initNode = new LinkedNode(nums[count]);
        initNode.next = initNode(nums, count + 1);
        return initNode;
    }

    /**
     * 将链表数据 转为 List，将List进行循环，再将结果组装为链表
     * @param node
     * @param k
     * @return
     */
    private static LinkedNode cycleKthSolution1(LinkedNode node, int k){

        List<Integer> list = new ArrayList<>();
        LinkedNode currentNode = node;
        while (currentNode != null){
            list.add(currentNode.value);
            currentNode = currentNode.next;
        }
        if(list.size() <= 0 || k <= 0){
            return node;
        }
        /**
         * 在同一个list上既遍历又修改，无影响；
         * 但如果既遍历又修改数组结构（如删除） 就会报错ConcurrentModificationException）
         */
        // 第一层循环表示 移动的轮次，移动 k 轮，也就是 向右移动 k 个数
        for(int i = 0; i < k; i++){
            int tailTemp = 0;
            // 第二层循环 用来向右移动数字
            for(int j = list.size() - 1; j >=-1; j--){
                if(j == list.size() - 1){
                    // tailTemp 用来存储最后一位数字
                    tailTemp = list.get(list.size()-1);
                    continue;
                }
                if(j == -1){
                    // 将最后一位数字 塞到 list的头部
                    list.set(j + 1, tailTemp);
                    continue;
                }
                list.set(j + 1, list.get(j));
            }
        }

        //处理 list，组装 Node
        LinkedNode newNode = buildLinkedNode(list,0);

        return newNode;
    }

    private static LinkedNode buildLinkedNode(List<Integer> newList, int count){
        if(count == newList.size()){
            return null;
        }
        LinkedNode newNode = new LinkedNode(newList.get(count));
        newNode.next = buildLinkedNode(newList, count + 1);

        return newNode;
    }
}
