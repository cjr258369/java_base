package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-08-10 09:41:44
 * [1289] - 下降路径最小和  II
 * MinimumFallingPathSumIi.md
 */
 
//给你一个 n x n 整数矩阵 grid ，请你返回 非零偏移下降路径 数字和的最小值。
// 非零偏移下降路径 定义为：从 grid 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。 
//
// 示例 1： 
//输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
//输出：13
//解释：
//所有非零偏移下降路径包括：
//[1,5,9], [1,5,7], [1,6,7], [1,6,8],
//[2,4,8], [2,4,9], [2,6,7], [2,6,8],
//[3,4,8], [3,4,9], [3,5,7], [3,5,9]
//下降路径中数字和最小的是 [1,5,7] ，所以答案是 13 。
//
// 示例 2： 
//输入：grid = [[7]]
//输出：7
//
// 提示： 
// n == grid.length == grid[i].length 
// 1 <= n <= 200 
// -99 <= grid[i][j] <= 99 

public class MinimumFallingPathSumIi{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumFallingPathSumIi().new Solution();
        System.out.println("预期结果：13 , 运行结果：" + solution.minFallingPathSum3(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        System.out.println("预期结果：7 , 运行结果：" + solution.minFallingPathSum3(new int[][]{{7}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //切记，要养成习惯，无论任何时候，都不要直接修改输入，因为修改输入一着不慎，会很容易引起一些很难排查的问题！！
        //因此下方的暴力版本，重新修改了一版
        
        //直接暴力，三层循环：200 * 200 * 200
        //时间：O(n^3)
        //空间：O(1)
        public int minFallingPathSum(int[][] grid) {
            int n = grid.length, ans = Integer.MAX_VALUE;
            for(int i = 1; i < n; i++){
                for(int j = 0; j < n; j++){
                    int min = Integer.MAX_VALUE;
                    for(int k = 0; k < n; k++){
                        if(k == j) continue;
                        min = Math.min(min, grid[i - 1][k]);
                    }
                    grid[i][j] += min;
                }
            }
            for(int i = 0; i < n; i++){
                ans = Math.min(ans, grid[n - 1][i]);
            }
            return ans;
        }
        //暴力版本的修改版，不修改输出
        //复杂度分析
        // 时间复杂度：O(n^3)，其中 n 是 grid 的行数和列数。我们使用三重循环来求解答案，每一层循环的复杂度为 O(n)，第 0 层单独求解和最终答案遍历的时间复杂度均为 O(n)，因此总的时间复杂度为 O(n^3)。
        // 空间复杂度：O(n^2)。我们使用二维数组 dp[i][j] 来存储过程中的答案，实际上可以使用滚动数组优化至 O(n)。
        public int minFallingPathSum2(int[][] grid) {
            int n = grid.length, ans = Integer.MAX_VALUE, dp[][] = new int[n][n];
            for(int i = 0; i < n; i++){
                dp[0][i] = grid[0][i];
            }
            for(int i = 1; i < n; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for(int i = 1; i < n; i++){
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < n; k++){
                        if(k == j) continue;
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]);
                    }
                }
            }
            for(int i = 0; i < n; i++){
                ans = Math.min(ans, dp[n - 1][i]);
            }
            return ans;
        }
        
        //优化版本：
        // 每一行都依赖上一行除自己以外的最小值，如果自己正对着的上一行就是最小值，那么取次小值即可，否则直接取最小值就行了。
        // 因此，只需存3个值即可：最小值，最小值下标，次小值，这样一来，空间可以优化至O(1)，时间可以优化至 O(n^2)
        public int minFallingPathSum3(int[][] grid) {
            int n = grid.length, first_min_sum = 0, second_min_sum = 0, first_min_idx = -1;
            for(int i = 0; i < n; i++){
                int curr_first_min_sum = Integer.MAX_VALUE, curr_second_min_sum = Integer.MAX_VALUE, curr_first_min_idx = -1;
                
                for(int j = 0; j < n; j++){
                    int curr_sum = (j == first_min_idx ? second_min_sum : first_min_sum) + grid[i][j];
                    if(curr_sum < curr_first_min_sum){
                        curr_second_min_sum = curr_first_min_sum;
                        curr_first_min_sum = curr_sum;
                        curr_first_min_idx = j;
                    }else if(curr_sum < curr_second_min_sum){
                        curr_second_min_sum = curr_sum;
                    }
                }
                
                first_min_sum = curr_first_min_sum;
                first_min_idx = curr_first_min_idx;
                second_min_sum = curr_second_min_sum;
            }
            return first_min_sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}