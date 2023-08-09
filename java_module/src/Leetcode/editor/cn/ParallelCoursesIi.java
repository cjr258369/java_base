package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2023-06-16 10:35:52
 * [1494] - 并行课程 II
 * ParallelCoursesIi.md
 */
 
//给你一个整数 n 表示某所大学里课程的数目，编号为 1 到 n ，数组 relations 中， relations[i] = [xi, yi] 表示一个先修课的关系，也就是课程 xi 必须在课程 yi 之前上。同时你还有一个整数 k 。
// 在一个学期中，你 最多 可以同时上 k 门课，前提是这些课的先修课在之前的学期里已经上过了。
// 请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。 
//
// 示例 1： 
//输入：n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
//输出：3 
//解释：上图展示了题目输入的图。在第一个学期中，我们可以上课程 2 和课程 3 。然后第二个学期上课程 1 ，第三个学期上课程 4 。
//
// 示例 2： 
//输入：n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
//输出：4 
//解释：上图展示了题目输入的图。一个最优方案是：第一学期上课程 2 和 3，第二学期上课程 4 ，第三学期上课程 1 ，第四学期上课程 5 。
//
// 示例 3： 
//输入：n = 11, relations = [], k = 2
//输出：6
//
// 提示： 
// 1 <= n <= 15 
// 1 <= k <= n 
// 0 <= relations.length <= n * (n-1) / 2 
// relations[i].length == 2 
// 1 <= xi, yi <= n 
// xi != yi 
// 所有先修关系都是不同的，也就是说 relations[i] != relations[j] 。 
// 题目输入的图是个有向无环图。 

public class ParallelCoursesIi{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ParallelCoursesIi().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.minNumberOfSemesters(4, new int[][]{{2,1}, {3, 1}, {1, 4}}, 2));
        System.out.println("预期结果：4 , 运行结果：" + solution.minNumberOfSemesters(5, new int[][]{{2,1}, {3, 1}, {4, 1}, {1, 5}}, 2));
        System.out.println("预期结果：11 , 运行结果：" + solution.minNumberOfSemesters(11, new int[][]{}, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //https://leetcode.cn/problems/parallel-courses-ii/solution/zi-ji-zhuang-ya-dpcong-ji-yi-hua-sou-suo-oxwd/


        private int[] pre1, memo;
        private int k, u;
        
        public int minNumberOfSemesters(int n, int[][] relations, int k) {
            this.k = k;
            pre1 = new int[n];
            for (int[] r : relations)
                pre1[r[1] - 1] |= 1 << (r[0] - 1); // r[1] 的先修课程集合，下标改从 0 开始

            u = (1 << n) - 1; // 全集
            memo = new int[1 << n];
            Arrays.fill(memo, -1); // -1 表示没有计算过
            return dfs(u);    
        }

        private int dfs(int i) {
            if (i == 0) return 0; // 空集
            if (memo[i] != -1) return memo[i]; // 之前算过了
            int i1 = 0, ci = u ^ i; // i 的补集
            for (int j = 0; j < pre1.length; j++)
                if ((i >> j & 1) > 0 && (pre1[j] | ci) == ci) // pre1[j] 在 i 的补集中，可以学（否则这学期一定不能学）
                    i1 |= 1 << j;
            if (Integer.bitCount(i1) <= k)  // 如果个数小于 k，则可以全部学习，不再枚举子集
                return memo[i] = dfs(i ^ i1) + 1;
            int res = Integer.MAX_VALUE;
            for (int j = i1; j > 0; j = (j - 1) & i1) // 枚举 i1 的子集 j
                if (Integer.bitCount(j) == k)
                    res = Math.min(res, dfs(i ^ j) + 1);
            return memo[i] = res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}