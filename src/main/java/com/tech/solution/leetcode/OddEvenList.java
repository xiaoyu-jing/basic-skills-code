package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/10
 *
 * 算法：奇偶链表
 *
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。【重点！！！！】
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 *
 * 示例 1:
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,3,5,2,4]
 *
 * 示例 2:
 * 输入: head = [2,1,3,5,6,4,7]
 * 输出: [2,3,6,7,1,5,4]
 */
public class OddEvenList {

    public static void main(String[] args){
        ListNode initNode = buildInitNode(new int[]{1,2,3,4,5}, 0);
        System.out.println("链表{1,2,3,4,5} 的奇偶链表为：" + oddEvenList(initNode).toString());

        ListNode initNode1 = buildInitNode(new int[]{2,1,3,5,6,4,7}, 0);
        System.out.println("链表{2,1,3,5,6,4,7} 的奇偶链表为：" + oddEvenList(initNode1).toString());
    }

    private static ListNode buildInitNode(int[] nums, int count) {
        if(count == nums.length){
            return null;
        }
        ListNode root = new ListNode(nums[count]);
        root.next = buildInitNode(nums, count+1);
        return root;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        public String toString(){
            StringBuilder builder = new StringBuilder();
            ListNode node = this;
            while (node != null){
                if(builder.length() > 0){
                    builder.append(",");
                }
                builder.append(node.val);
                node = node.next;
            }
            return builder.toString();
        }
    }

    private static ListNode oddEvenList(ListNode head){
        List<Integer> jiList = new ArrayList<>();
        List<Integer> ouList = new ArrayList<>();
        // 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数   所以初始值从 1 开始
        int count = 1;
        while (head != null) {
            if(count % 2 == 0){
                ouList.add(head.val);
            } else {
                jiList.add(head.val);
            }
            head = head.next;
            count++;
        }
        jiList.addAll(ouList);
        if(jiList.size() > 0){
            return buildResultNode(jiList, 0);
        }
        return null;
    }

    private static ListNode buildResultNode(List<Integer> list, int newCount){
        if(newCount == list.size()){
            return null;
        }
        ListNode node = new ListNode(list.get(newCount));
        node.next = buildResultNode(list, newCount + 1);
        return node;
    }
}
