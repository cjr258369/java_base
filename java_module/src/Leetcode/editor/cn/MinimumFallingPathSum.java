package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-07-13 15:04:09
 * [931] - 下降路径最小和
 * MinimumFallingPathSum.md
 */
 
//给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
// 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
//
// 示例 1：
//输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
//输出：13
//解释：如图所示，为和最小的两条下降路径
//
// 示例 2： 
//输入：matrix = [[-19,57],[-40,-5]]
//输出：-59
//解释：如图所示，为和最小的下降路径
//
// 提示： 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 100 
// -100 <= matrix[i][j] <= 100 

public class MinimumFallingPathSum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumFallingPathSum().new Solution();
        System.out.println("预期结果：13 , 运行结果：" + solution.minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}}));
        System.out.println("预期结果：-59 , 运行结果：" + solution.minFallingPathSum(new int[][]{{-19,57},{-40,-5}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length, ans = Arrays.stream(matrix[0]).min().getAsInt();
            for(int i = 1; i < n; i++){
                for(int j = 0; j < n; j++){
                    matrix[i][j] += Math.min(matrix[i - 1][Math.max(j - 1, 0)], Math.min(matrix[i - 1][j], matrix[i - 1][Math.min(j + 1, n - 1)]));
                    if(i == n - 1) ans = Math.min(ans, matrix[i][j]);
                }
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}