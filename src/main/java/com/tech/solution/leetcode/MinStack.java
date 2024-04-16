package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/31
 *
 * 算法：最小栈
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 *  1）MinStack() 初始化堆栈对象。
 *  2）void push(int val) 将元素val推入堆栈。
 *  3）void pop() 删除堆栈顶部的元素。
 *  4）int top() 获取堆栈顶部的元素。
 *  5）int getMin() 获取堆栈中的最小元素。
 *
 *
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * --------------------------------【扩展知识】--------------------------------
 * LinkedList 是一个双端队列，等价于 Deque、Stack， JAVA中已经不建议使用 Stack，推荐使用性能更好的 Deque
 *
 * LinkedList、Deque、Stack 三者 都有 peek() 方法，都是表示返回栈顶元素，但是不移除数据
 * Deque 的peek方法有两个：peekFirst() --- Returns: the head of this deque, or null if this deque is empty
 *                       peekLast() --- Returns: the tail of this deque, or null if this deque is empty
 *
 *  该题的思路：
 * 1、思路1 --- 建一个辅助栈
 *        数据栈 和 辅助栈 同时插入数据，辅助栈存储当前栈的最小值
 *        每次push数据，比较 当前值 与 辅助栈的栈顶数据 大小，
 *          1）如果当前值 小于 辅助栈栈顶数据，则将 当前值 同时压入 数据栈和辅助栈中
 *          2）如果当前值 大于 辅助栈栈顶数据，则当前值只压入 数据栈
 *        每次 pop 数据，比较 数据栈栈顶数据 和 辅助栈栈顶数据
 *          1）如果两个值相等，则 同时POP数据栈栈顶数据 和 辅助栈栈顶数据
 *          2）如果两个值不相等，则 只pop 数据栈栈顶数据
 * 2、思路2 --- 栈中数据 与  最小值存在同一个 Node 中
 *
 *  下方代码为【思路2】
 */
public class MinStack {

    class Node {
        //当前值
        int value;
        //最小值
        int min;
        //下一个值
        Node next;
        public Node(int _value, int _min){
            this(_value, _min, null);
        }
        public Node(int _value, int _min, Node _next){
            this.value = _value;
            this.min = _min;
            this.next = _next;
        }
    }

    public MinStack(){}

    private Node head;

    public void push(int value){
        if(head == null){
            head = new Node(value,value);
        }else {
            // push新元素时，将 head 节点 作为新 Node 节点的 next 节点
            head = new Node(value,Math.min(head.min, value),head);
        }
    }

    public void pop(){
        head = head.next;
    }

    // 获取栈顶元素
    public int top(){
        return head.value;
    }

    // 获取栈中最小值
    public int getMinValue(){
        return head.min;
    }

    public static void main(String[] args){
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMinValue());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMinValue());
    }
}
