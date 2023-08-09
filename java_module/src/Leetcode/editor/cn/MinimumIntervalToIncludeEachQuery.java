package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2023-07-18 11:04:49
 * [1851] - 包含每个查询的最小区间
 * MinimumIntervalToIncludeEachQuery.md
 */
 
//给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始于 left[i] 、结束于 right[i]（包含两侧取值，闭区间）。区间的 长度 定义为区间中包含的整数数目，更正式地表达是 right[i] - left[i] + 1 。
// 再给你一个整数数组 queries 。第 j 个查询的答案是满足 left[i] <= queries[j] <= right[i] 的 长度最小区间 i 的长度 。如果不存在这样的区间，那么答案是 -1 。 
//
// 以数组形式返回对应查询的所有答案。 
//
// 示例 1： 
//输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
//输出：[3,3,1,4]
//解释：查询处理如下：
//- Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。
//- Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 - 2 + 1 = 3 。
//- Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。
//- Query = 5 ：区间 [3,6] 是包含 5 的最小区间，答案为 6 - 3 + 1 = 4 。
//
// 示例 2： 
//输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
//输出：[2,-1,4,6]
//解释：查询处理如下：
//- Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。
//- Query = 19：不存在包含 19 的区间，答案为 -1 。
//- Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。
//- Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为 25 - 20 + 1 = 6 。
//
// 提示： 
// 1 <= intervals.length <= 10⁵ 
// 1 <= queries.length <= 10⁵ 
// intervals[i].length == 2 
// 1 <= lefti <= righti <= 10⁷ 
// 1 <= queries[j] <= 10⁷ 

public class MinimumIntervalToIncludeEachQuery{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumIntervalToIncludeEachQuery().new Solution();
        System.out.println("预期结果：[3,3,1,4] , 运行结果：" + Arrays.toString(solution.minInterval(new int[][]{{1, 4}, {2, 4}, {3, 6}, {4, 4}}, new int[]{2, 3, 4, 5})));
        System.out.println("预期结果：[2,-1,4,6] , 运行结果：" + Arrays.toString(solution.minInterval(new int[][]{{2,3}, {2,5}, {1,8}, {20,25}}, new int[]{2,19,5,22})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(mlogm+nlogn)，其中 m 和 n 分别为 intervals 和 queries 的长度。排序需要 O(mlogm+nlogn)，最多执行 m 次入队和出队操作，需要 O(mlogm)。
        // 空间复杂度：O(m+n)。保存 qindex 需要 O(m)，保存 pq 需要 O(n)。
        public int[] minInterval(int[][] intervals, int[] queries) {
            Integer[] queryIndex = new Integer[queries.length];
            for(int i = 0; i < queryIndex.length; i++){
                queryIndex[i] = i;
            }
            Arrays.sort(queryIndex, (a, b) -> queries[a] - queries[b]);
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            int idx = 0, res[] = new int[queries.length];
            Arrays.fill(res, -1);
            for(int qi : queryIndex){
                while(idx < intervals.length && queries[qi] >= intervals[idx][0]){
                    queue.offer(new int[]{intervals[idx][1] - intervals[idx][0] + 1, intervals[idx][0], intervals[idx][1]});
                    idx++;
                }
                while(!queue.isEmpty() && queries[qi] > queue.peek()[2]){
                    queue.poll();
                }
                if(!queue.isEmpty()){
                    res[qi] = queue.peek()[0];
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}