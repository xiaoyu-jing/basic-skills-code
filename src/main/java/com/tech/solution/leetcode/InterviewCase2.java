package com.tech.solution.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

        /**
         * 这是个循环链表，不能加 toString() 方法，容易造成 内存溢出 （JAVA heap）
         */
        /*public String toString(){
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
        }*/
    }

    public static void main(String[] args){
        /**
         * 示例1   {3,2,0,-4}
         */
        //初始化循环链表
        ListNode node = initLinkedList(new int[]{3,2,0,-4}, 1);
        System.out.println("【示例1】方法1 哈希法-Map - 循环队列中是否存在环：" + checkCycle1(node));

        //初始化循环链表
        ListNode node2 = initLinkedList(new int[]{3,2,0,-4}, 1);
        System.out.println("【示例1】方法1 哈希法-Set - 循环队列中是否存在环：" + checkCycle2(node2));

        /**
         * 示例2  {1，2}
         */
        //初始化循环链表
        ListNode node3 = initLinkedList(new int[]{1,2}, 0);
        System.out.println("【示例2】方法1 哈希法-Set - 循环队列中是否存在环：" + checkCycle2(node3));

        /**
         * 示例3  {1}
         */
        //初始化循环链表
        ListNode node4 = initLinkedList(new int[]{1}, -1);
        System.out.println("【示例3】方法1 哈希法-Set - 循环队列中是否存在环：" + checkCycle2(node4));

        /**
         * 示例4  {1，2}
         */
        //初始化循环链表
        ListNode node5 = initLinkedList(new int[]{1,2}, 0);
        System.out.println("【示例4】方法2 快慢指针 - 循环队列中是否存在环：" + checkCycle3(node5)
                + ", 环的长度为：" + calculateCycleLength(node5));

        /**
         * 示例5  {1}
         */
        //初始化循环链表
        ListNode node6 = initLinkedList(new int[]{1}, -1);
        System.out.println("【示例5】方法2 快慢指针 - 循环队列中是否存在环：" + checkCycle3(node6)
                + ", 环的长度为：" + calculateCycleLength(node6));

        /**
         * 示例6   {3,2,0,-4}
         */
        //初始化循环链表
        ListNode node7 = initLinkedList(new int[]{3,2,0,-4}, 1);
        System.out.println("【示例6】方法2 快慢指针 - 循环队列中是否存在环：" + checkCycle3(node7)
                + ", 环的长度为：" + calculateCycleLength(node7));
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

    /**
     * 方法1: 哈希法 - Map
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
     * 方法1: 哈希法 - Set
     * @param node
     * @return
     */
    private static boolean checkCycle2(ListNode node){
        Set<ListNode> visited = new HashSet<>();
        ListNode head = node;
        while (head != null) {
            if(visited.contains(head)){
                return true;
            }
            visited.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 方法2 - 快慢指针 （直接找"相遇点"即可）
     *
     * 由于 slow 和 fast 都是 Node节点，slow != fast  用的就是 哈希法 比较的对象，所以只要 hash 值相同，一定是同一个节点，直接返回就行
     * 但 如果是 leetcoad 的另一道算法【寻找重复数】，本身提供的就是一个 数组，不是链表，数组中 存在很多数字，仅仅通过 slow == fast 一次判断 不足以
     * 得出 当前的相遇点就是 入环点。 所以对于 【寻找重复数】 这道算法题，需要先找 "相遇点"，再找"入环点"
     * @param node
     * @return
     */
    private static boolean checkCycle3(ListNode node){
        ListNode newNode = detectCycle(node);
        return newNode != null ? true : false;
    }

    /**
     * 获取入环节点
     * @param node
     * @return
     */
    private static ListNode detectCycle(ListNode node){
        ListNode slow = node;
        ListNode fast = node.next;
        while (slow != fast) {
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
     * @param node
     * @return
     */
    private static int calculateCycleLength(ListNode node){
        // 获取入环节点
        ListNode cycleNode = detectCycle(node);
        int cycleLength = 0;
        if(cycleNode == null){
            return cycleLength;
        }
        // 此循环的目的：将链表截取到从 入环点 开始   (备注： 2024-4-15 此 while 循环意义不大，可以删除，currentNode 直接 从 入环点 cycleNode 开始即可)
        ListNode currentNode = node;
        while (currentNode != null) {
            if(currentNode == cycleNode){
                break;
            }
            currentNode = currentNode.next;
        }

        // currentNode 为 入环点 头节点
        do {
            currentNode = currentNode.next;
            // 因为 do-while 循环中，直接从 next 节点开始的, 先 +1，后判断，所以cycleLength 初始化从 0 开始
            cycleLength++;
            if(currentNode == cycleNode){
                break;
            }
        } while (currentNode != null);

        return cycleLength;
    }

}
