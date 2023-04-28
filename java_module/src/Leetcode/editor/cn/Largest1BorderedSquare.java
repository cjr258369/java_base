package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-02-17 09:14:56
 * [1139] - 最大的以 1 为边界的正方形
 * Largest1BorderedSquare.md
 */
 
//给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0 。 
//
// 示例 1： 
// 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
//输出：9
//
// 示例 2： 
// 输入：grid = [[1,1,0,0]]
//输出：1
//
// 提示： 
// 1 <= grid.length <= 100 
// 1 <= grid[0].length <= 100 
// grid[i][j] 为 0 或 1 

public class Largest1BorderedSquare{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new Largest1BorderedSquare().new Solution();
        System.out.println("预期结果：9 , 运行结果：" + solution.largest1BorderedSquare(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
        System.out.println("预期结果：1 , 运行结果：" + solution.largest1BorderedSquare(new int[][]{{1, 1, 0, 0}}));
        System.out.println("预期结果：0 , 运行结果：" + solution.largest1BorderedSquare(new int[][]{{0}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前缀和（可以快速求区间和） + 枚举（判断有多少个符合条件的正方形，如果该正方形符合条件，那么它的每条边的1的和 = d（边长））
        //复杂度分析
        // 时间复杂度：O(m×n×min(m,n))，其中 m 表示矩阵的行数，n 表示矩阵的列数。
        // 空间复杂度：O(m×n)，其中 m 表示矩阵的行数，n 表示矩阵的列数。需要保存矩阵中每个位置的最长连续 1 的数目，需要的空间为 O(m×n)。
        public int largest1BorderedSquare(int[][] grid) {
            int m = grid.length, n = grid[0].length, rowSum[][] = new int[m][n + 1], colSum[][] = new int[n][m + 1];
            //计算每行，每列的前缀和
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];   // 每行的前缀和
                    colSum[j][i + 1] = colSum[j][i] + grid[i][j];   // 每列的前缀和
                }
            }
            
            //从大到小枚举正方形边长 d
            for(int d = Math.min(m, n); d > 0; d--){
                for(int i = 0; i <= m - d; i++){
                    for(int j = 0; j <= n - d; j++){ // 枚举正方形左上角坐标 (i,j)
                        if(rowSum[i][j + d] - rowSum[i][j] == d //上边
                           && rowSum[i + d - 1][j + d] - rowSum[i + d - 1][j] == d //下边
                           && colSum[j][i + d] - colSum[j][i] == d //左边
                           && colSum[j + d - 1][i + d] - colSum[j + d - 1][i] == d //右边
                         ){
                            //因为边长是从最大往最小遍历的，所以当前最大边长的正方形符合条件，立刻返回即可
                            return d * d;
                        }
                    }
                }
            }
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}