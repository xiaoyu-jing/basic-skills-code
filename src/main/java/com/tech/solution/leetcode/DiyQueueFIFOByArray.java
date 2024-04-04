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
 * 注意：以下算法为 使用 数组 实现的先进先出队列
 *
 * FIFO ： 先进先出
 */
public class DiyQueueFIFOByArray {

    public static void main(String[] args){
        DiyQueueFIFOByArray diyQueueFIFOByArray = new DiyQueueFIFOByArray();
        diyQueueFIFOByArray.add(1);
        diyQueueFIFOByArray.add(2);
        diyQueueFIFOByArray.add(3);
        diyQueueFIFOByArray.add(4);
        diyQueueFIFOByArray.add(5);
        System.out.println("添加值：" + diyQueueFIFOByArray.toString()); // 1,2,3,4,5

        System.out.println("删除：" + diyQueueFIFOByArray.remove());  // 1
        System.out.println("删除：" + diyQueueFIFOByArray.remove());  // 2
        System.out.println("删除值之后的数组：" + diyQueueFIFOByArray.toString()); // 3,4,5

        diyQueueFIFOByArray.add(6);
        diyQueueFIFOByArray.add(7);
        diyQueueFIFOByArray.add(8);
        diyQueueFIFOByArray.add(9);
        diyQueueFIFOByArray.add(10);
        diyQueueFIFOByArray.add(11);
        diyQueueFIFOByArray.add(12);
        diyQueueFIFOByArray.add(13);
        diyQueueFIFOByArray.add(14);
        diyQueueFIFOByArray.add(15);
        diyQueueFIFOByArray.add(16);
        diyQueueFIFOByArray.add(17);
        diyQueueFIFOByArray.add(18);
        System.out.println("扩容后的数组：" + diyQueueFIFOByArray.toString());

        diyQueueFIFOByArray.add(19);
        diyQueueFIFOByArray.add(20);
        diyQueueFIFOByArray.add(21);
        System.out.println("扩容后的数组再添加值：" + diyQueueFIFOByArray.toString());
    }

    Object[] elements;
    // 定义数组头指针
    private int head;
    // 定义数组尾指针
    private int tail;

    public DiyQueueFIFOByArray(){
        elements = new Object[16];
    }

    private void add(Object obj){
        // tail初始值为0
        elements[tail] = obj;
        // tail指针向右移动
        tail = (tail + 1) & (elements.length - 1);
        // tail 向右移动到末尾，再次移动时，按位与 操作会将 tail 的数值变成 0，此时与 head 一致，开始动态扩容
        if(head == tail){
            doubleCapacity();
        }
    }

    private Object remove(){
        // 每次都是删除头节点，head 一直都是 0
        Object obj = elements[head];
        Object[] newArray = new Object[elements.length];
        System.arraycopy(elements,0,newArray,0,elements.length);
        // elements 用来遍历； newArray 用来改值
        for(int i = 1; i < elements.length; i++){
            newArray[i-1] = newArray[i];
        }
        elements = newArray;
        // 每移除一次元素，tail 指针向左移动一位
        tail = (tail - 1) & (elements.length - 1);
        return obj;
    }

    private void doubleCapacity(){
        assert head == tail;
        System.out.println("扩容前的数组：" + this.toString());
        int n = elements.length;
        Object[] newArray = new Object[n << 1];
        System.arraycopy(elements,0,newArray,0,n);
        elements = newArray;
        // 头节点的指针不变，用于删除头节点时使用
        head = 0;
        // 尾节点的指针指向 数组末尾
        tail = n;
    }

    public String toString(){
        Object[] nums = this.elements;
        StringBuilder builder = new StringBuilder();
        for(Object obj : nums){
            if(builder.length() > 0){
                builder.append(",");
            }
            builder.append(obj);
        }
        return builder.toString();
    }
}
