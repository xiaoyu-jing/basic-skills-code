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
     * @param pos     第几位是循环节点
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
        Map<Integer,ListNode> nodeMap = new HashMap<>();
        ListNode node = generatorData(new int[]{3,2,0,-4}, 1,0, nodeMap);
        //构造尾节点
        ListNode newNode = buildTailNode(node,1);
        System.out.println("方法1 - 循环队列中是否存在环：" + checkCycle1(newNode));
        nodeMap.clear();

        ListNode node2 = generatorData(new int[]{3,2,0,-4}, 1,0, nodeMap);
        System.out.println("方法2 - 循环队列中是否存在环：" + checkCycle2(node2));
        nodeMap.clear();
    }

    private static ListNode buildTailNode(ListNode node, int pos) {
        if(pos < 0){
            return node;
        }
        Map<Integer,ListNode> map = new HashMap<>();
        ListNode tempNode = node;
        ListNode tailNode = null;
        int countNum = 0;
        while (tempNode.next != null) {
            if(countNum == pos){
                tailNode = tempNode;
            }
            countNum++;
            tempNode = tempNode.next;
        }
        while (true) {
            if(node == null){
                node = tailNode;
                break;
            }
            node = node.next;
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
            if(map.get(head.val) == null){
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
            fast = slow.next.next;
        }
        return true;
    }

}
