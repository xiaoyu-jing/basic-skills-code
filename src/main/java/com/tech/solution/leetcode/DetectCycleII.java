package com.tech.solution.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jing1560
 * @data 2024/4/15
 *
 * 算法：环形链表II
 *
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 */
public class DetectCycleII {

    public static void main(String[] args){

        /**
         * 示例1   {3,2,0,-4}
         */
        //初始化循环链表
        ListNode node = initLinkedList(new int[]{3,2,0,-4}, 1);
        System.out.println("【示例1】方法1 哈希法-Set - 循环队列中是否存在环：" + (detectCycleByHash(node) != null ? true : false));

        /**
         * 示例2  {1，2}
         */
        //初始化循环链表
        ListNode node2 = initLinkedList(new int[]{1,2}, 0);
        System.out.println("【示例2】方法1 哈希法-Set - 循环队列中是否存在环：" + (detectCycleByHash(node2) != null ? true : false));

        /**
         * 示例3  {1}
         */
        //初始化循环链表
        ListNode node3 = initLinkedList(new int[]{1}, -1);
        System.out.println("【示例3】方法1 哈希法-Set - 循环队列中是否存在环：" + (detectCycleByHash(node3) != null ? true : false));

        /**
         * 示例4  {1，2}
         */
        //初始化循环链表
        ListNode node4 = initLinkedList(new int[]{1,2}, 0);
        System.out.println("【示例4】方法2 快慢指针 - 循环队列中是否存在环：" + (detectCycleByDoublePointer(node4) != null ? true : false)
                + ", 环的长度为：" + calculateCycleLength(node4));

        /**
         * 示例5  {1}
         */
        //初始化循环链表
        ListNode node5 = initLinkedList(new int[]{1}, -1);
        System.out.println("【示例5】方法2 快慢指针 - 循环队列中是否存在环：" + (detectCycleByDoublePointer(node5) != null ? true : false)
                + ", 环的长度为：" + calculateCycleLength(node5));

        /**
         * 示例6   {3,2,0,-4}
         */
        //初始化循环链表
        ListNode node6 = initLinkedList(new int[]{3,2,0,-4}, 1);
        System.out.println("【示例6】方法2 快慢指针 - 循环队列中是否存在环：" + (detectCycleByDoublePointer(node6) != null ? true : false)
                + ", 环的长度为：" + calculateCycleLength(node6));

    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 解法一：通过哈希求解
     * @param head
     * @return
     */
    private static ListNode detectCycleByHash(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            if(nodeSet.contains(currentNode)){
                return currentNode;
            }
            nodeSet.add(currentNode);
            currentNode = currentNode.next;
        }
        return head;
    }

    /**
     * 解法二：快慢指针（双指针）
     * @param head
     * @return
     */
    private static ListNode detectCycleByDoublePointer(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if(fast == null || fast.next == null){
                return null;
            }
            //此处为重点！ （如果都各走一步，那永远都追不上）
            // 慢指针每次走一步
            slow = slow.next;
            // 快指针每次走两步
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 计算环的长度
     * @param head
     * @return
     */
    private static int calculateCycleLength(ListNode head){
        // 找到入环点
        ListNode inCycleNode = detectCycleByDoublePointer(head);
        int cycleLength = 0;
        if(inCycleNode == null){
            return cycleLength;
        }
        // 当前节点从 入环点 开始循环一轮，下次遇到 入环点，即表示 一圈结束
        ListNode currentNode = inCycleNode;
        do {
            currentNode = currentNode.next;
            // 因为 do-while 循环中，直接从 next 节点开始的, 先 +1，后判断，所以cycleLength 初始化从 0 开始
            cycleLength++;
            if(currentNode == inCycleNode){
                break;
            }
        } while (currentNode != null);

        return cycleLength;
    }

    //********************************** 下方为初始化数据 *********************************************

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
            //此处的赋值，只是移动了 currentNode 指针，而没有改变它原来指向的节点的数据，所以 原node 是不会发生变化的
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
            //此处修改了 tail引用的尾指针（且修改了尾指针原来的节点数据，原来是 null，改成具体值了），相当于修改了 node 的尾指针
            tail.next = targetPrev;
        }

        return node;
    }

}
