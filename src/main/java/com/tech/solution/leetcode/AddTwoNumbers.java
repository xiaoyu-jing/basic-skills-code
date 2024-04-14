package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/14
 *
 * 算法：两数相加
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 */
public class AddTwoNumbers {

    public static void main(String[] args){
        // 入参是 new 了 String类型的数组，是为了 直接复用 builderNode 方法。 理论上最好是 new int[]
        ListNode node1 = builderNode(new String[]{"2","4","3"}, 0);
        ListNode node2 = builderNode(new String[]{"5","6","4"}, 0);
        System.out.println(addTwoNumbers(node1,node2).toString());

        ListNode node11 = builderNode(new String[]{"0"}, 0);
        ListNode node22 = builderNode(new String[]{"0"}, 0);
        System.out.println(addTwoNumbers(node11,node22).toString());

        ListNode node111 = builderNode(new String[]{"9","9","9","9","9","9","9"}, 0);
        ListNode node222 = builderNode(new String[]{"9","9","9","9"}, 0);
        System.out.println(addTwoNumbers(node111,node222).toString());
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

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Num = calculateReverseValue(l1);
        int l2Num = calculateReverseValue(l2);
        int sum = l1Num + l2Num;
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(sum));
        String reverseSum = builder.reverse().toString();
        String[] resultArr = reverseSum.split("");
        ListNode sumNode = builderNode(resultArr, 0);
        return sumNode;
    }
    // 计算翻转数字
    private static int calculateReverseValue(ListNode node){
        StringBuilder builder = new StringBuilder();
        while (node != null) {
            builder.append(node.val);
            node = node.next;
        }
        return Integer.valueOf(builder.reverse().toString());
    }

    private static ListNode builderNode(String[] resultArr, int count){
        if(count == resultArr.length){
            return null;
        }
        ListNode node = new ListNode(Integer.valueOf(resultArr[count]));
        node.next = builderNode(resultArr, count + 1);
        return node;
    }
}
