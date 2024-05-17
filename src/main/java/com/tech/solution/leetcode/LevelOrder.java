package com.tech.solution.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jing1560
 * @data 2024/5/16
 *
 * 算法：二叉树的层序遍历
 *
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 */
public class LevelOrder {

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

    public static void main(String[] args){
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};
        TreeNode node = buildNode(preOrder, inOrder);
        System.out.println(levelOrder(node));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        TreeNode currentNode = root;
        buildMap(currentNode, map, 1);
        if(!map.isEmpty()){
            Map<Integer, List<TreeNode>> groupMap =
                    map.entrySet()
                            .stream()
                            .collect(Collectors.groupingBy(
                                    Map.Entry::getValue,
                                    Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
            Iterator iterator = groupMap.entrySet().iterator();
            List<List<Integer>> resList = new ArrayList<>();
            while(iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                List<TreeNode> nodeList = (List<TreeNode>)entry.getValue();
                List<Integer> nodeValueList = new ArrayList<>();
                nodeList.stream().forEach(node -> {
                    if(node != null){
                        nodeValueList.add(node.val);
                    }
                });
                resList.add(nodeValueList);
            }
            return resList;
        }
        return new ArrayList<>();
    }

    //private static int count = 1;
    private static void buildMap(TreeNode node, Map<TreeNode, Integer> map, int count){
        if(node == null){
            return;
        }
        map.put(node, count);
        buildMap(node.left, map, count++);
        buildMap(node.right, map, count++);
    }

    private static int countLeft = 1;
    private static void buildLeftNodeMap(TreeNode node, Map<TreeNode, Integer> map){
        if(node == null){
            return;
        }
        countLeft++;
        map.put(node, countLeft);
        buildLeftNodeMap(node.left, map);
    }

    private static int countRight = 1;
    private static void buildRightNodeMap(TreeNode node, Map<TreeNode, Integer> map){
        if(node == null){
            return;
        }
        countRight++;
        map.put(node, countRight);
        buildRightNodeMap(node.right, map);
    }

    /********************* 数据构造 ******************************/

    private static TreeNode buildNode(int[] preOrder, int[] inOrder){
        if(preOrder == null || inOrder == null || preOrder.length != inOrder.length){
            return null;
        }
        // 中序遍历作为标准，放在Map中
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i = 0; i < inOrder.length; i++){
            inMap.put(inOrder[i], i);
        }
        TreeNode root = buildTree(inMap, 0, preOrder.length - 1, preOrder);
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
