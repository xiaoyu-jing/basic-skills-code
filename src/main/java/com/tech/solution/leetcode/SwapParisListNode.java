package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/26
 *
 * 算法：两两交换链表中的节点
 *
 * 示例：输入：{1,2,3,4}；
 *      输出：{2,1,4,3}
 */
public class SwapParisListNode {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        /**
         * 原本没有该方法，手工 写一个格式化为 String 的方法
         * @return
         */
        public String toString(){
            StringBuilder builder = new StringBuilder();
            ListNode tempNode = this;
            while (tempNode != null){
                if(builder.length() > 0){
                    builder.append(",");
                }
                builder.append(tempNode.val);
                tempNode = tempNode.next;
            }
            return builder.toString();
        }
    }

    public static void main(String[] args){
        ListNode node = swapParis(buildNode(new int[]{1,2,3,4}, 0));
        System.out.println(node.toString());

    }

    private static ListNode swapParis(ListNode head){
        if(head != null && head.next == null){
            return head;
        }

        //此处必须要建一个引用，currentNode用于交换数据 和 变更， currentNode变化的值最终都会自动变更到 head上
        //所以最后直接输出 head 就行
        //此处的玩法可以 参考 InterviewCase2.java 文件
        ListNode currentNode = head;      // TODO   这一行是超重点！！！！！！！！
        while(currentNode != null && currentNode.next != null){
            int tempValue = currentNode.next.val;  //值发生变化
            currentNode.next.val = currentNode.val;  //值发生变化
            currentNode.val = tempValue;  //值发生变化
            currentNode = currentNode.next.next;
        }
        return head;
    }

    private static ListNode buildNode(int[] arr, int index){
        if(index == arr.length){
            return null;
        }
        ListNode node = new ListNode(arr[index]);
        node.next = buildNode(arr,index+1);
        return node;
    }
}
