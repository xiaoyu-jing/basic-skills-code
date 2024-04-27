package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/27
 *
 * 算法：反转链表II
 *
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * 提示：
 * 1) 链表中节点数目为 n
 * 2) 1 <= n <= 500
 * 3) -500 <= Node.val <= 500
 * 4) 1 <= left <= right <= n
 */
public class ReverseBetween {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        public String toString(){
            StringBuilder builder = new StringBuilder();
            ListNode currentNode = this;
            while (currentNode != null) {
                if(builder.length() > 0){
                    builder.append(",");
                }
                builder.append(currentNode.val);
                currentNode = currentNode.next;
            }
            return builder.toString();
        }
    }

    public static void main(String[] args){
        ListNode initNode = initNode(new int[]{1,2,3,4,5}, 0);
        System.out.println(reverseBetween(initNode, 2, 4));

        ListNode initNode1 = initNode(new int[]{5}, 0);
        System.out.println(reverseBetween(initNode1, 1, 1));
    }

    /**
     * 反转链表
     * @param head
     * @param left
     * @param right
     * @return
     */
    private static ListNode reverseBetween(ListNode head, int left, int right) {
        List<Integer> list = new ArrayList<>();
        ListNode currentNode = head;
        while(currentNode != null){
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }
        if(list.isEmpty()){
            return head;
        }
        // 定义一个指定区间大小的数组
        int[] arr = new int[right - left + 1];
        // 用于反转数组的迭代计数器
        int iteratorNum = right - left;
        for(int i = left - 1; i < right; i++){
            // list 递增循环， iteratorNum 迭代计数器 递减循环
            // 将反转后的数据 塞入 新数组中
            arr[iteratorNum] = list.get(i);
            iteratorNum--;
        }

        // 用于替换 list 中数据的迭代计数器
        int replaceIteratorNum = 0;
        for(int i = 0; i < list.size(); i++){
            // 在指定 left 到 right 的区间内 才进行数据赋值
            if(i >= left - 1 && i <= right - 1){
                list.set(i, arr[replaceIteratorNum++]);
            }
        }
        return buildNode(list, 0);
    }

    /**
         构建链表
     */
    private static ListNode buildNode(List<Integer> list, int count){
        if(count == list.size()){
            return null;
        }
        ListNode node = new ListNode(list.get(count));
        node.next = buildNode(list, count + 1);
        return node;
    }

    /**
     * 数据初始化
     * @param nums
     * @param count
     * @return
     */
    private static ListNode initNode(int[] nums, int count){
        if(count == nums.length){
            return null;
        }
        ListNode node = new ListNode(nums[count]);
        node.next = initNode(nums, count + 1);
        return node;
    }
}
