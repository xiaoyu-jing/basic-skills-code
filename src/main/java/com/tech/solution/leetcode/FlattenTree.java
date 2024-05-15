package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/5/15
 *
 * 算法：二叉树展开为链表
 *
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 1）展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 2）展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 */
public class FlattenTree {

    public class TreeNode {
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

    public static void main(String[] args){
        flatten(null);
        System.out.println();
    }

    private static void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrderBuilder(root, list);
        //链表组装
        for(int i = 1; i < list.size(); i++){
            TreeNode prev = list.get(i - 1);
            TreeNode currentNode = list.get(i);
            prev.left = null;
            prev.right = currentNode;
        }
    }

    private static void preOrderBuilder(TreeNode root, List<TreeNode> list){
        if(root != null){
            list.add(root);
            //左子树数据获取
            preOrderBuilder(root.left, list);
            //右子树数据获取
            preOrderBuilder(root.right, list);
        }
    }
}
