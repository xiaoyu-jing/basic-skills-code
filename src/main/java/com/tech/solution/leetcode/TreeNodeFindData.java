package com.tech.solution.leetcode;

import java.util.*;

/**
 * @author jing1560
 * @data 2024/3/28
 *
 * 算法：二叉搜索树中第K小的元素
 */
public class TreeNodeFindData {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static int kthSmallest(TreeNode node, int k){
        List<Integer> list = new ArrayList<>();
        int kthSmallValue = 0;
        dfs(node,list);

        if(list.size() > 0){
            Collections.sort(list);
            kthSmallValue = list.get(k - 1);
        }
        return kthSmallValue;
    }

    private static void dfs(TreeNode node, List<Integer> list) {
        list.add(node.val);
        if(node.left != null){
            dfs(node.left,list);
        }
        if(node.right != null){
            dfs(node.right,list);
        }
    }

    public static void main(String[] args){
        TreeNode treeNode = buildTreeNode(new int[]{5,3,2,1,4,6}, new int[]{1,2,3,4,5,6});
        System.out.println(kthSmallest(treeNode,3));

        TreeNode treeNode1 = buildTreeNode(new int[]{5,3,2,1,4,6}, new int[]{1,2,3,4,5,6});
        System.out.println(kthSmallest(treeNode1,1));
    }

    //************************************** 下方代码为 二叉树构造 【数据初始化】*********************************************

    /**
     * 通过先序遍历和中序遍历构造二叉树
     * @param preOrder
     * @param inOrder
     * @return
     */
    private static TreeNode buildTreeNode(int[] preOrder, int[] inOrder){
        if(preOrder == null || inOrder == null || preOrder.length != inOrder.length){
            return null;
        }
        Map<Integer,Integer> inMap = new HashMap<>();
        for(int i = 0; i < inOrder.length; i++){
            inMap.put(inOrder[i],i);
        }
        int[] rootIndex = {0};
        TreeNode node = dfs(preOrder,inMap,0,preOrder.length - 1, rootIndex);
        return node;
    }

    /**
     * 注：下面 rootIndex 的使用方法比较特殊，请参照 BuildTreeSolution2.java 中的另一个示例 一起学习
     *
     * 1、方法中传递 int[] ,相当于是一个对象的引用，对象引用的值修改会同步修改方法外部的 rootIndex值；
     * 2、但方法如果直接传递 int 基本数据类型，相当于传递的是该值的一个副本，rootIndex 值的修改，在不同方法中是隔离的。
     *    比如在 node.left = dfs() 方法中 递增了 rootIndex，进入 node.right = dfs() 方法中之后，rootIndex 还是原始值。 理论上 rootIndex 的值是要全局可见的
     *
     * 针对以上问题，有两种解决方法：
     * 1、将 rootIndex 作为 int 基本类型，定义成 类的成员变量（也称为实例变量），在 dfs() 方法外面定义
     *    private int rootIndex = 0;
     * 2、方法入参上传递一个 int[] 数组对象，对象中值的修改也是全局可见的
     */

    //private static int rootIndex = 0;

    private static TreeNode dfs(int[] preOrder,Map<Integer,Integer> inMap,int preLeft, int preRight, int[] rootIndex){
        if(preLeft <= preRight){
            int rootValue = preOrder[rootIndex[0]++];
            TreeNode node = new TreeNode(rootValue);
            node.left = dfs(preOrder, inMap, preLeft, inMap.get(rootValue) - 1, rootIndex);
            node.right = dfs(preOrder, inMap, inMap.get(rootValue) + 1, preRight, rootIndex);
            return node;
        } else {
            return null;
        }
    }

}
