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
 * 注意：以下算法为模拟 ArrayDeque 底层实现原理，手动实现一个队列。
 *      该算法类似栈，队列的元素是 先进后出 的
 *
 * LIFO ： 后进先出
 */
public class DiyQueueLIFO {

    public static void main(String[] args){
        DiyQueueLIFO queue = new DiyQueueLIFO();
        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        queue.addFirst(5);
        System.out.println("addFirst：" + queue.toString()); // null,null,null,null,null,null,null,null,null,null,null,5,4,3,2,1
        queue.addLast(11);
        queue.addLast(12);
        System.out.println("addLast：" + queue.toString()); // 11,12,null,null,null,null,null,null,null,null,null,5,4,3,2,1
        queue.removeFirst();
        System.out.println("removeFirst：" + queue.toString()); // 11,12,null,null,null,null,null,null,null,null,null,null,4,3,2,1
        queue.removeLast();
        System.out.println("removeLast：" + queue.toString()); // 11,null,null,null,null,null,null,null,null,null,null,null,4,3,2,1
        // 测试动态扩容
        queue.addFirst(5);
        queue.addFirst(6);
        queue.addFirst(7);
        queue.addFirst(8);
        queue.addFirst(9);
        queue.addFirst(30);
        queue.addFirst(31);
        queue.addFirst(32);
        queue.addLast(12);
        queue.addLast(13);
        queue.addLast(14);
        //queue.addLast(15);
        System.out.println("动态扩容后：" + queue.toString());
    }

    //数组空间
    Object[] elements;
    //头指针的位置
    private int head;
    //尾指针的位置
    private int tail;

    public DiyQueueLIFO(){
        elements = new Object[16];
    }

    private void add(Object obj){
        addFirst(obj);
    }

    /**
     * 往队列头部添加元素
     * @param obj
     */
    private void addFirst(Object obj){
        if(obj == null)
            throw new NullPointerException();
        // 添加元素，头指针 head 减一，往左移动
        // 两者进行 与& 操作（二进制补码）后，新值赋值给 head
        head = (head - 1) & (elements.length - 1);
        elements[head] = obj;
        // 判断是否需要扩容
        if(head == tail){
            doubleCapacity();
        }
    }

    /**
     * 往队列的尾部添加元素
     * @param obj
     */
    private void addLast(Object obj){
        if(obj == null)
            throw new NullPointerException();
        // 先给 tail 位置添加元素
        elements[tail] = obj;
        /**
         * 添加元素后，尾指针 tail 加一，往右移动
         * 两者进行 与& 操作（二进制补码）后，新值赋值给 tail
         */
        if((tail = (tail + 1) & (elements.length - 1)) == head){
            // 首尾相等时 需要扩容
            doubleCapacity();
        }
    }

    /**
     * 删除头时，先删再+1
     */
    private void removeFirst(){
        elements[head] = null;
        // 使用 按位与操作 的好处：指针移动到队列尾部 或者 头部，当再次向后移动时，指针会自动跑到队列的另一头，实现循环效果
        head = (head + 1) & (elements.length - 1);
    }

    /**
     * 删除尾时，先减一再删
     */
    private void removeLast(){
        // 使用 按位与操作 的好处：指针移动到队列尾部 或者 头部，当再次向后移动时，指针会自动跑到队列的另一头，实现循环效果
        tail = (tail - 1) & (elements.length - 1);
        elements[tail] = null;
    }

    /**
     * 动态扩容
     */
    private void doubleCapacity(){
        System.out.println("动态扩容前：" + this.toString());

        //断言判断 head 必须等于 tail 时 才进行扩容
        assert head == tail;
        // h 为 当前头节点的所在位置
        int h = head;
        int n = elements.length;
        // r 为 头节点右侧的 元素个数
        int r = n - h;
        // n 左移一位，表示乘以2，表示原容量扩大1倍
        Object[] newArray = new Object[n << 1];
        // 复制 原数组的 头节点的 右侧 r 长度的 元素们 到 新数组 从 0 开始的 r 长度
        // h 表示 原数组中从 h 开始复制； 0 表示 复制到 新数组的 0号位置； r 表示 从原数组复制的数据长度
        System.arraycopy(elements, h, newArray, 0, r);

        // 复制 原数组的 尾节点的 左侧 剩余长度的 元素们 到 新数组 从 r 开始的 剩余长度
        // 0 表示 原数组中从 0 开始复制； r 表示 复制到 新数组的 r号位置； h 表示 从原数组复制的数据长度
        System.arraycopy(elements, 0, newArray, r, h);
        // 将 elements 的引用指向 新数组
        elements = newArray;
        // 初始化 head 为0； tail 为 n
        head = 0;
        tail = n;
    }

    public String toString(){
        Object[] nums = this.elements;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if(builder.length() > 0){
                builder.append(",");
            }
            builder.append(nums[i]);
        }
        return builder.toString();
    }
}
