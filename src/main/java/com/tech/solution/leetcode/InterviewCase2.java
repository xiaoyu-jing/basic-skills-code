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

    /**
     * 数据初始化
     * @param head
     * @param pos     第几位是循环节点（入环的位置）
     * @param count   计数位
     * @param nodeMap   node临时存放节点
     * @return
     */
    private static ListNode generatorData(int[] head, int pos, int count,Map<Integer,ListNode> nodeMap){
        if(count == head.length && pos == -1){
            //无循环节点，末尾节点直接返回null
            return null;
        }
        if(count == head.length && pos != -1){
            return nodeMap.get(pos);
        }
        ListNode node = new ListNode(head[count]);

        node.next = generatorData(head,pos,count + 1, nodeMap);
        nodeMap.put(count,node);

        return node;
    }

    public static void main(String[] args){
        /**
         * 示例1   {3,2,0,-4}
         */
        Map<Integer,ListNode> nodeMap = new HashMap<>();
        ListNode node = generatorData(new int[]{3,2,0,-4}, 1,0, nodeMap);
        //构造尾节点
        ListNode newNode = buildTailNode(node,1);
        System.out.println("【示例1】方法1 - 循环队列中是否存在环：" + checkCycle1(newNode));
        nodeMap.clear();

        ListNode node2 = generatorData(new int[]{3,2,0,-4}, 1,0, nodeMap);
        //构造尾节点
        ListNode newNode2 = buildTailNode(node2,1);
        System.out.println("【示例1】方法2 - 循环队列中是否存在环：" + checkCycle2(newNode2));
        nodeMap.clear();

        /**
         * 示例2  {1，2}
         */
        ListNode node3 = generatorData(new int[]{1,2}, 0,0, nodeMap);
        //构造尾节点
        ListNode newNode3 = buildTailNode(node3,0);
        System.out.println("【示例2】方法2 - 循环队列中是否存在环：" + checkCycle2(newNode3));
        nodeMap.clear();


        /**
         * 示例3  {1}
         */
        ListNode node4 = generatorData(new int[]{1}, -1,0, nodeMap);
        //构造尾节点
        ListNode newNode4 = buildTailNode(node4,-1);
        System.out.println("【示例3】方法2 - 循环队列中是否存在环：" + checkCycle2(newNode4));
        nodeMap.clear();
    }

    /**
     * 构造尾节点
     * @param node
     * @param pos  第几位是循环节点（入环的位置）
     * @return
     */
    private static ListNode buildTailNode(ListNode node, int pos) {
        if(pos < 0){
            return node;
        }
        ListNode currentNode = node;
        //特定节点的前一个节点
        ListNode targetPrev = null;
        //递增计数器
        int countNum = 0;
        while (currentNode != null) {
            if(countNum == pos){
                targetPrev = currentNode;
                break;
            }
            countNum++;
            currentNode = currentNode.next;
        }

        if(targetPrev != null){
            //定义尾节点
            ListNode tail = node;
            while (tail.next != null) {
                tail = tail.next;
            }
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
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}
