package com.tech.solution.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/2/8
 *
 * 算法：从前序与中序遍历序列构造二叉树
 */
public class BuildTreeSolution2 {

    public static int rootIndex = 0;

    public static void main(String[] args){
        buildTree();
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

    private static void buildTree(){
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};

        if(preOrder == null || inOrder == null || preOrder.length != inOrder.length){
            return;
        }

        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<inOrder.length; i++){
            map.put(inOrder[i],i);
        }

        TreeNode node = buildTree(map,0,preOrder.length-1,preOrder);
        System.out.println(node);
    }

    private static TreeNode buildTree(Map<Integer,Integer> map, int left, int right, int[] preOrder){
        if(left <= right){
            int rootValue = preOrder[rootIndex];
            TreeNode root = new TreeNode(rootValue);
            // rootIndex为根节点计数器，由于是先序遍历，所以每次+1，表示取到的就是根节点
            rootIndex++;
            root.left = buildTree(map,left, map.get(rootValue) -1, preOrder);
            root.right = buildTree(map,map.get(rootValue) +1, right, preOrder);

            return root;
        } else {
            return null;
        }
    }
}
