package com.tech.solution.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/3/20
 * 洋钱罐面试算法 - 循环链表中是否存在环
 */
public class InterviewCase2 {

    /**
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：返回索引为 1 的链表节点
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args){
        /**
         * 示例1   {3,2,0,-4}
         */
        //初始化循环链表
        ListNode node = initLinkedList(new int[]{3,2,0,-4}, 1);
        System.out.println("【示例1】方法1 - 循环队列中是否存在环：" + checkCycle1(node));

        //初始化循环链表
        ListNode node2 = initLinkedList(new int[]{3,2,0,-4}, 1);
        System.out.println("【示例1】方法2 - 循环队列中是否存在环：" + checkCycle2(node2));

        /**
         * 示例2  {1，2}
         */
        //初始化循环链表
        ListNode node3 = initLinkedList(new int[]{1,2}, 0);
        System.out.println("【示例2】方法2 - 循环队列中是否存在环：" + checkCycle2(node3));

        /**
         * 示例3  {1}
         */
        //初始化循环链表
        ListNode node4 = initLinkedList(new int[]{1}, -1);
        System.out.println("【示例3】方法2 - 循环队列中是否存在环：" + checkCycle2(node4));
    }

    /**
     * 初始化循环链表： 1、构造普通链表
     *               2、构造循环链表
     * @param head
     * @param pos
     * @return
     */
    private static ListNode initLinkedList(int[] head, int pos){
        //生成普通链表数据
        ListNode commonNode = buildLinkedList(head, 0);
        //构造尾节点，生成循环链表
        ListNode cycleLinkedListNode = buildTailNode(commonNode,pos);
        return cycleLinkedListNode;
    }

    /**
     * 构造链表  head = [3,2,0,-4]
     * @param head
     * @param count   计数位
     * @return
     */
    private static ListNode buildLinkedList(int[] head, int count){
        if(count == head.length){
            //末尾节点直接返回null
            return null;
        }
        ListNode node = new ListNode(head[count]);
        node.next = buildLinkedList(head,count + 1);
        return node;
    }

    /**
     * 构造尾节点
     * @param node
     * @param pos  第几位是循环节点（入环的位置）
     * @return
     */
    private static ListNode buildTailNode(ListNode node, int pos) {
        if(pos < 0){
            //pos < 0 表示没有环，直接返回
            return node;
        }
        //此处 currentNode 只是一个对象的引用，指向的还是 原node对象的地址
        ListNode currentNode = node;
        //特定节点的前一个节点（入环的目标节点）
        ListNode targetPrev = null;
        //递增计数器
        int countNum = 0;
        // 遍历链表寻找目标值
        while (currentNode != null) {
            if(countNum == pos){
                targetPrev = currentNode;
                break;
            }
            countNum++;
            currentNode = currentNode.next;
        }

        // 如果找到了目标节点，则将尾节点指向它
        if(targetPrev != null){
            //定义尾节点，tail引用指向 node节点，修改 tail 则表示修改 node
            //此处 tail 只是一个对象的引用，指向的还是 原node对象的地址   （对象的引用测试，可参考 ObjectReferenceTest.java）
            ListNode tail = node;
            while (tail.next != null) {
                tail = tail.next;
            }
            //此处修改了 tail引用的尾指针，相当于修改了 node 的尾指针
            tail.next = targetPrev;
        }

        return node;
    }

    /**
     * 方法1
     * @param node
     * @return
     */
    private static boolean checkCycle1(ListNode node){
        Map<ListNode,Integer> map = new HashMap<>();
        ListNode head = node;
        while (head.next != null) {
            if(map.get(head) == null){
                map.put(head,0);
            } else {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 方法2 - 快慢指针
     * @param node
     * @return
     */
    private static boolean checkCycle2(ListNode node){
        ListNode slow = node;
        ListNode fast = node.next;
        while (slow != fast) {
            if(fast == null || fast.next == null){
                return false;
            }
            //此处为重点！ （如果都各走一步，那永远都追不上）
            // 慢指针每次走一步
            slow = slow.next;
            // 快指针每次走两步
            fast = fast.next.next;
        }
        return true;
    }

}
