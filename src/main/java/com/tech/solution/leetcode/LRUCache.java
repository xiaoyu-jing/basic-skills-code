package com.tech.solution.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/3/27
 * 算法：146.LRU缓存  ---- 哈希表 + 双向链表
 *
 * 双向链表 是带有前后指针的链表
 *
 * 【算法】
 *
 * LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
 * 1、双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
 * 2、哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
 *
 * 这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 O(1)O(1)O(1) 的时间内完成 get 或者 put 操作。具体的方法如下：
 * 1、对于 get 操作，首先判断 key 是否存在：
 *    1）如果 key 不存在，则返回 −1；
 *    2）如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
 *
 * 2、对于 put 操作，首先判断 key 是否存在：
 *    1）如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
 *    2）如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。
 *
 * 上述各项操作中，访问哈希表的时间复杂度为 O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为 O(1)。而将一个节点移到双向链表的头部，可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作，都可以在 O(1) 时间内完成。
 *
 */
public class LRUCache {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode(){}
        public DLinkedNode(int _key, int _value){
            this.key = _key;
            this.value = _value;
        }
    }

    private Map<Integer, DLinkedNode> cacheMap = new HashMap<Integer, DLinkedNode>();
    //链表容量计数器
    private int size;
    //链表容量
    private int capacity;
    //定义伪头节点、伪尾节点
    private DLinkedNode head,tail;

    public LRUCache(int capacity){
        this.size = 0;
        this.capacity = capacity;
        //使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        DLinkedNode node = cacheMap.get(key);
        if(node == null){
            return -1;
        }
        //如果Key存在，先通过哈希表定位，再移动到链表的头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value){
        DLinkedNode node = cacheMap.get(key);
        if(node == null){
            // 如果Key不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key,value);
            // 添加进哈希表
            cacheMap.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if(size > capacity){
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tailNode = removeTail();
                // 删除哈希表中对应的项
                cacheMap.remove(tailNode.key);
                --size;
            }
        }else {
            // 如果Key存在，先通过哈希表定位，再修改 value，并移动到链表头部
            moveToHead(node);
            node.value = value;
        }
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 添加节点时，以该节点的前后指针 为参考系，调整指针，并删除原依赖关系
     */
    private void addToHead(DLinkedNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 删除节点时，不以该节点为参考系，以 "该节点的前节点的后指针" 和 "该节点的后节点的前指针" 为参考系
     */
    private void removeNode(DLinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private DLinkedNode removeTail(){
        DLinkedNode tailNode = tail.prev;
        removeNode(tailNode);
        return tailNode;
    }

}
