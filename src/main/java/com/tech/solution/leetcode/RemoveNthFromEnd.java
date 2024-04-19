package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/19
 *
 * 算法：删除链表的倒数第N个结点
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
public class RemoveNthFromEnd {

    public static void main(String[] args){
        ListNode initNode = initBuildeNode(new int[]{1,2,3,4,5}, 0);
        System.out.println(removeNthFromEnd(initNode, 2).toString());

        ListNode initNode1 = initBuildeNode(new int[]{1}, 0);
        ListNode resultNode = removeNthFromEnd(initNode1, 1);
        System.out.println(resultNode == null ? "[]" : resultNode.toString());

        ListNode initNode2 = initBuildeNode(new int[]{1,2}, 0);
        System.out.println(removeNthFromEnd(initNode2, 1).toString());
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
            while (node != null) {
                if(builder.length() > 0){
                    builder.append(",");
                }
                builder.append(node.val);
                node = node.next;
            }
            return builder.toString();
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        List<Integer> list = new ArrayList<>();
        ListNode currentNode = head;
        while(currentNode != null){
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }
        if(!list.isEmpty()){
            list.remove(list.size() - n);
        }
        return buildeNode(list, 0);
    }

    private static ListNode buildeNode(List<Integer> list, int count){
        if(count == list.size()){
            return null;
        }
        ListNode node = new ListNode(list.get(count));
        node.next = buildeNode(list, count + 1);
        return node;
    }

    /**
     * 数据初始化
     * @param nums
     * @param count
     * @return
     */
    private static ListNode initBuildeNode(int[] nums, int count){
        if(count == nums.length){
            return null;
        }
        ListNode node = new ListNode(nums[count]);
        node.next = initBuildeNode(nums, count + 1);
        return node;
    }
}
