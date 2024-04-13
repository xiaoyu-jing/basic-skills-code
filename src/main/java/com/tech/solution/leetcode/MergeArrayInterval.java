package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/13
 *
 * 算法：合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class MergeArrayInterval {

    public static void main(String[] args){
        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        printArr(merge(intervals));

        int[][] intervals1 = {{1,4}, {4,5}};
        printArr(merge(intervals1));
    }

    /**
     * 算法思路：
     *
     * 我们用数组 merged 存储最终的答案。
     * 首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
     *
     * 1）如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
     * 2）否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
     *
     * @param intervals
     * @return
     */
    private static int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return new int[0][2];
        }
        // 对原数组中的区间按左端点进行升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // merged 用于存放合并后的数组，由于不知道数组最终的大小，所以直接用List存储
        List<int[]> merged = new ArrayList<int[]>();
        for(int i = 0; i < intervals.length; i++){
            int L = intervals[i][0];
            int R = intervals[i][1];
            if(merged.isEmpty() || merged.get(merged.size() - 1)[1] < L){
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    private static void printArr(int[][] intervals){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int[] childArr : intervals){
            if(builder.length() > 2){
                builder.append(",");
            }
            builder.append(Arrays.toString(childArr));
        }
        builder.append("]");
        System.out.println(builder.toString());
    }
}
