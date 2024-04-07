package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/6
 *
 * [字节]
 * 算法：手写一个跳表（SkipList）
 * 难度：困难
 *
 * 参考文章：https://mp.weixin.qq.com/s/WnG6DiiUptlmg6NixCZQuw
 *
 * 跳表中存储的是正整数，并且存储的是不重复的
 */
public class DiySkipList {

    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private static final double P_FACTOR = 0.25;
    // 带头链表
    private Node head = new Node();

    public DiySkipList(){}

    // 定义好 跳表 的节点
    public class Node {
        // 存储的数据值
        private int data = -1;
        // 每一个节点，都带着一个 指针数组，最大16层的一个跳表结构，多级索引结构
        private Node forwards[] = new Node[MAX_LEVEL];
        // 当前数据节点的最高层数
        private int maxLevel = 0;

        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    /**
     * 随机函数 -- 生成随机的层数
     *
     * 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
     * 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
     * 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
     *        50%的概率返回 1
     *        25%的概率返回 2
     *      12.5%的概率返回 3 ...
     *
     *
     * 跳表同样也是很多层，新增一个数据时，最底层的链表需要插入数据，
     * 然后，考虑是否需要在上面几层中加入数据做索引 ？ 这个就靠随机函数了。
     * 例如: 如果跳表中插入数据id=6，且随机函数返回第三层（有25%的概率），那就需要在跳表的最底层到第三层都插入数据。
     * 跳表跟B+树不一样，跳表是否新增层数，纯粹靠随机函数，不太关心平衡的问题。
     *
     * @return
     */
    private int randomLevel() {
        int level = 1;

        while (Math.random() < P_FACTOR && level < MAX_LEVEL)
            level += 1;
        return level;
    }

    /**
     * 新增数据
     * @param value
     */
    public void insert(int value){
        // 首先生成一个随机的层数
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;

        Node update[] = new Node[level];
        for(int i = 0; i < level; i++){
            update[i] = head;
        }

        // 记录每一层的 前驱节点
        Node p = head;
        for(int i = level - 1; i >= 0; i--){
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            // 在搜索路径上使用 update 保存节点
            update[i] = p;
        }

        // 每一层的前驱节点，插入当前节点
        for(int i = 0; i < level; i++){
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        // update node hight
        if(levelCount < level){
            levelCount = level;
        }
    }

    /**
     * 查询数据
     * @param value
     * @return
     */
    public Node find(int value){
        Node p = head;
        // 从上层开始，一层一层的往下找
        for(int i = levelCount - 1; i >= 0; i--){
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if(p.forwards[0] != null && p.forwards[0].data == value){
            return p.forwards[0];
        }else {
            return null;
        }
    }

    public static void main(String[] args){
        DiySkipList skipList = new DiySkipList();
        skipList.insert(8);
        skipList.insert(3);
        skipList.insert(5);
        skipList.insert(1);
        System.out.println(skipList.head);

        Node node = skipList.find(5);
        System.out.println("5 是否存在跳表中：" + node.toString());
    }

}
