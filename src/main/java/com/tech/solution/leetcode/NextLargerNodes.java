package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/10
 *
 * 算法：链表中的下一个更大节点
 *
 * 示例 1：
 * 输入：head = [2,1,5]
 * 输出：[5,5,0]
 *
 * 示例 2：
 * 输入：head = [2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 */
public class NextLargerNodes {

    public static void main(String[] args){
        ListNode initNode = buildNode(new int[]{2,1,5}, 0);
        System.out.println("链表{2,1,5}中的下一个更大节点为：" + Arrays.toString(nextLargerNodes(initNode)));

        ListNode initNode1 = buildNode(new int[]{2,7,4,3,5}, 0);
        System.out.println("链表{2,7,4,3,5}中的下一个更大节点为：" + Arrays.toString(nextLargerNodes(initNode1)));
    }

    private static ListNode buildNode(int[] nums, int count) {
        if(count == nums.length){
            return null;
        }
        ListNode root = new ListNode(nums[count]);
        root.next = buildNode(nums, count+1);
        return root;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static int[] nextLargerNodes(ListNode head){
        List<Integer> list = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            ListNode cycleNode = currentNode.next;
            while (cycleNode != null) {
                if(cycleNode.val > currentNode.val){
                    list.add(cycleNode.val);
                    break;
                }
                cycleNode = cycleNode.next;
            }
            if(cycleNode == null){
                list.add(0);
            }
            currentNode = currentNode.next;
        }
        if(list.size() > 0){
            int[] resultArr = new int[list.size()];
            for(int i = 0; i < list.size(); i++){
                resultArr[i] = list.get(i);
            }
            return resultArr;
        }
        return new int[1];
    }
}
