package com.tech.solution.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/4/28
 *
 * 算法：154.复杂链表的复制  （本题与 138.随机链表的复制 相同）
 *
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 *
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 */
public class CopyRandomList {

    public static void main(String[] args){
        Node initNode = initBuildNode();
        System.out.println(copyRandomList(initNode));
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public String toString(){
            StringBuilder builder = new StringBuilder();
            Node currentNode = this;

            Map<Integer, Integer> map = new HashMap<>();
            int index = 0;
            // 获取 random 的索引值
            while (currentNode != null) {
                map.put(currentNode.val, index++);
                currentNode = currentNode.next;
            }

            // 构建节点样式
            currentNode = this;
            while (currentNode != null) {
                if(builder.length() > 0){
                    builder.append(",");
                }
                builder.append("[");
                builder.append(currentNode.val);
                builder.append(",");
                builder.append(currentNode.random == null ? "null" :  map.get(currentNode.random.val));
                builder.append("]");
                currentNode = currentNode.next;
            }

            return builder.toString();
        }
    }

    /**
     * 解法：哈希表
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        if(head == null) return null;
        Node currentNode = head;
        Map<Node, Node> hmap = new HashMap<>();
        // 初始化哈希表，复制链表的各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while(currentNode != null){
            hmap.put(currentNode, new Node(currentNode.val));
            currentNode = currentNode.next;
        }
        // 还原 currentNode 为原头节点
        currentNode = head;
        // 构建新链表的引用指向（构建新节点的 next 和 random 引用指向）
        while(currentNode != null){
            hmap.get(currentNode).next = hmap.get(currentNode.next);
            hmap.get(currentNode).random = hmap.get(currentNode.random);
            currentNode = currentNode.next;
        }
        // 返回新链表的头节点
        return hmap.get(head);
    }

    /**
     * head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * @return
     */
    public static Node initBuildNode(){
        // random 引用指向初始化
        Map<Integer, Integer> hmap_random = new HashMap<>();
        hmap_random.put(7, -1);
        hmap_random.put(13,0);
        hmap_random.put(11,4);
        hmap_random.put(10,2);
        hmap_random.put(1,0);

        Map<Integer, Node> hmap = new HashMap<>();
        int[] nums = new int[]{7,13,11,10,1};
        // 值 与 节点映射关系初始化
        for(int i = 0; i < nums.length; i++){
            hmap.put(nums[i], new Node(nums[i]));
        }

        // 构建新链表的引用指向（构建新节点的 next 和 random 引用指向）
        for(int i = 0; i < nums.length; i++){   // 由于最后一个节点也存在 random 值，所以此处必须 i < nums.length，不能 i < nums.length - 1，要能取到最后一位
            // 构建新节点的 next 引用指向
            if(i + 1 == nums.length){  // 由于 for循环能取到最后一位，如果直接 nums[i + 1]，就会报数组越界，所以此处要加这样的判断
                hmap.get(nums[i]).next = null;
            } else {
                hmap.get(nums[i]).next = hmap.get(nums[i + 1]);
            }
            // 构建新节点的 random 引用指向
            int randomValue = hmap_random.get(nums[i]);
            if(randomValue == -1){
                // random 值为 -1，表示为 null
                hmap.get(nums[i]).random = null;
            } else {
                hmap.get(nums[i]).random = hmap.get(nums[randomValue]);
            }
        }
        return hmap.get(nums[0]);
    }
}
