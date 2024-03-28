package com.tech.solution.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/2/8
 *
 * 算法：从前序与中序遍历序列构造二叉树
 *
 * 较为复杂，查看简单版： BuildTreeSolution2.java
 */
public class BuildTreeSolution1 {

    public static void main(String[] args){
        buildTree();
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 构造二叉树
     */
    public static void buildTree(){
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};

        if(preOrder == null || inOrder == null || preOrder.length != inOrder.length){
            return;
        }

        int preLen = preOrder.length;
        int inLen = inOrder.length;
        Map<Integer,Integer> inMap = new HashMap<>();
        for(int i=0; i<inOrder.length; i++){
            inMap.put(inOrder[i],i);
        }

        TreeNode treeNode = buildTree(preOrder, 0, preLen-1, inMap, 0, inLen-1);
        System.out.println(treeNode);

    }

    /**
     * @param preorder 前序遍历序列
     * @param preLeft  前序遍历序列子区间的左边界，可以取到
     * @param preRight 前序遍历序列子区间的右边界，可以取到
     * @param inMap      在中序遍历序列里，数值与下标的对应关系
     * @param inLeft   中序遍历序列子区间的左边界，可以取到
     * @param inRight  前序遍历序列子区间的右边界，可以取到
     * @return
     */
    private static TreeNode buildTree(int[] preorder,int preLeft, int preRight, Map<Integer,Integer> inMap, int inLeft, int inRight) {
        //递归结束的条件
        if(preLeft > preRight || inLeft > inRight){
            return null;
        }
        //构造根节点
        int root = preorder[preLeft];
        TreeNode node = new TreeNode(root);
        int rootIndex = inMap.get(root);

        node.left = buildTree(preorder, preLeft+1, rootIndex-inLeft+preLeft, inMap, inLeft,rootIndex-1);
        node.right = buildTree(preorder,rootIndex - inLeft + preLeft + 1, preRight, inMap, rootIndex+1, inRight);

        return node;
    }
}
