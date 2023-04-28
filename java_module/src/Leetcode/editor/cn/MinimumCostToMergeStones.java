package Leetcode.editor.cn;

/**
 * 2023-04-04 09:02:11
 * [1000] - 合并石头的最低成本
 * MinimumCostToMergeStones.md
 */
 
//有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。  每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。 
// 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。 
//
// 示例 1： 
// 输入：stones = [3,2,4,1], K = 2
//输出：20
//解释：
//从 [3, 2, 4, 1] 开始。
//合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
//合并 [4, 1]，成本为 5，剩下 [5, 5]。
//合并 [5, 5]，成本为 10，剩下 [10]。
//总成本 20，这是可能的最小值。
// 
// 示例 2： 
// 输入：stones = [3,2,4,1], K = 3
//输出：-1
//解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。.
//
// 示例 3： 
// 输入：stones = [3,5,1,2,6], K = 3
//输出：25
//解释：
//从 [3, 5, 1, 2, 6] 开始。
//合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
//合并 [3, 8, 6]，成本为 17，剩下 [17]。
//总成本 25，这是可能的最小值。
//
// 提示： 
// 1 <= stones.length <= 30 
// 2 <= K <= 30 
// 1 <= stones[i] <= 100 

public class MinimumCostToMergeStones{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumCostToMergeStones().new Solution();
        System.out.println("预期结果：20 , 运行结果：" + solution.mergeStones(new int[]{3,2,4,1}, 2));
        System.out.println("预期结果：-1 , 运行结果：" + solution.mergeStones(new int[]{3,2,4,1}, 3));
        System.out.println("预期结果：25 , 运行结果：" + solution.mergeStones(new int[]{3,5,1,2,6}, 3));
        System.out.println("预期结果：0 , 运行结果：" + solution.mergeStones(new int[]{2}, 2));
        //不能用贪心的案例，局部最优不是全局最优，贪心的结果是：8 + 14 + 20 = 42，全局最优的结果是：10 + 10 + 20 = 40
        System.out.println("预期结果：40 , 运行结果：" + solution.mergeStones(new int[]{6,4,4,6}, 2));

        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int mergeStones(int[] stones, int k) {
            int n = stones.length;
            if ((n - 1) % (k - 1) != 0) return -1;
            int[][] dp = new int[n + 1][n + 1];
            int[] sum = new int[n + 1];
            for (int i = 1; i <= n; ++i) sum[i] = sum[i - 1] + stones[i - 1];
            for (int len = k; len <= n; ++len) { // 枚举区间长度
                for (int i = 1; i + len - 1 <= n; ++i) { // 枚举区间起点
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int p = i; p < j; p += k - 1) {  // 枚举分界点
                        dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j]);
                    }
                    if ((j - i) % (k - 1) == 0) dp[i][j] += sum[j] - sum[i - 1];
                }
            }
            return dp[1][n];    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}