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
 * 给定一个长度为 n 的链表 head
 * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
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
        // 由于不知道 int 数组的长度，所以先将结果放入 List 中
        List<Integer> list = new ArrayList<>();
        ListNode currentNode = head;
        // 第一层循环表示 当前节点
        while (currentNode != null) {
            ListNode cycleNode = currentNode.next;
            // 第二层循环表示 后续节点 与 当前节点比较
            while (cycleNode != null) {
                if(cycleNode.val > currentNode.val){
                    list.add(cycleNode.val);
                    break;
                }
                cycleNode = cycleNode.next;
            }
            // 如果第二层循环没有找到合适的值，表示不存在比当前节点大的数字，直接添加0
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
