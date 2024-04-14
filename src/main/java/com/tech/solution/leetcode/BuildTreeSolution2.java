package com.tech.solution.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/2/8
 *
 * 算法：从前序与中序遍历序列构造二叉树
 *
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 *  示例 1:
 *  输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *  输出: [3,9,20,null,null,15,7]
 *
 *  示例 2:
 *  输入: preorder = [-1], inorder = [-1]
 *  输出: [-1]
 */
public class BuildTreeSolution2 {

    public static void main(String[] args){
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};
        System.out.println(buildTree(preOrder,inOrder));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val,TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static TreeNode buildTree(int[] preOrder, int[] inOrder){
        if(preOrder == null || inOrder == null || preOrder.length != inOrder.length){
            return null;
        }
        // 中序遍历作为标准，放在Map中
        Map<Integer,Integer> inMap = new HashMap<>();
        for(int i = 0; i < inOrder.length; i++){
            inMap.put(inOrder[i], i);
        }
        TreeNode root = buildTree(inMap,0,preOrder.length - 1, preOrder);
        return root;
    }

    public static int rootIndex = 0;

    private static TreeNode buildTree(Map<Integer,Integer> inMap, int left, int right, int[] preOrder){
        if(left <= right){
            int rootValue = preOrder[rootIndex];
            TreeNode root = new TreeNode(rootValue);
            // rootIndex为根节点计数器，由于是先序遍历，所以每次+1，表示取到的就是根节点
            rootIndex++;
            root.left = buildTree(inMap, left,  inMap.get(rootValue) - 1, preOrder);
            root.right = buildTree(inMap, inMap.get(rootValue) + 1, right, preOrder);
            return root;
        } else {
            return null;
        }
    }
}
