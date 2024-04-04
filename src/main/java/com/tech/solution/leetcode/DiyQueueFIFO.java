package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/4
 *
 * 算法：设计一个队列，要求底层用数组，支持动态扩容
 *
 * 该算法分析：队列是有顺序的，在java 中能用来做队列的有 LinkedList、Deque 等， ArrayDeque 是一个数组类型的双端队列
 *
 * 特点1：队列是 有顺序的
 * 特点2: 队列的特点是 元素先进先出（栈的特点是 元素先进后出）
 * 特点3：要求底层用数组，可以模拟ArrayDeque的底层实现方式
 * 特点4：支持动态扩容
 *
 * 注意：以下算法为 使用链表实现的先进先出队列
 *
 * FIFO ： 先进先出
 */
public class DiyQueueFIFO {

    public static void main(String[] args){
        DiyQueueFIFO queueFIFO = new DiyQueueFIFO();
        queueFIFO.push(1);
        queueFIFO.push(2);
        queueFIFO.push(3);
        queueFIFO.push(4);
        queueFIFO.push(5);
        System.out.println("数据入队：" + queueFIFO.head.toString()); // 1,2,3,4,5

        queueFIFO.pop();
        System.out.println("数据出队：" + queueFIFO.head.toString());  // 2,3,4,5
    }

    public DiyQueueFIFO(){}

    static class LinkedNode {
        int value;
        LinkedNode next;
        public LinkedNode(int _value){
            this(_value,null);
        }
        public LinkedNode(int _value, LinkedNode _next){
            this.value = _value;
            this.next = _next;
        }

        public String toString(){
            LinkedNode node = this;
            StringBuilder builder = new StringBuilder();
            while (node != null) {
                if(builder.length() > 0){
                    builder.append(",");
                }
                builder.append(node.value);
                node = node.next;
            }
            return builder.toString();
        }
    }

    LinkedNode head;

    public void push(int value){
        if(head == null){
            head = new LinkedNode(value);
        } else {
            LinkedNode newNode = new LinkedNode(value);
            //寻找尾节点
            LinkedNode tail = head;
            while (tail.next != null){
                tail = tail.next;
            }
            //尾节点的Next 赋值为 新的节点
            tail.next = newNode;
        }
    }

    public void pop(){
        head = head.next;
    }
}
