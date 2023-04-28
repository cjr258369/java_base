package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-03-01 09:37:17
 * [2373] - 矩阵中的局部最大值
 * LargestLocalValuesInAMatrix.md
 */
 
//给你一个大小为 n x n 的整数矩阵 grid 。生成一个大小为 (n - 2) x (n - 2) 的整数矩阵 maxLocal ，并满足： 
// maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。 
// 换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。 
// 返回生成的矩阵。 
//
// 示例 1： 
//输入：grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
//输出：[[9,9],[8,6]]
//解释：原矩阵和生成的矩阵如上图所示。
//注意，生成的矩阵中，每个值都对应 grid 中一个相接的 3 x 3 矩阵的最大值。 
//
// 示例 2： 
//输入：grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
//输出：[[2,2,2],[2,2,2],[2,2,2]]
//解释：注意，2 包含在 grid 中每个 3 x 3 的矩阵中。
//
// 提示： 
// n == grid.length == grid[i].length 
// 3 <= n <= 100 
// 1 <= grid[i][j] <= 100 

public class LargestLocalValuesInAMatrix{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LargestLocalValuesInAMatrix().new Solution();
        System.out.println("预期结果：[[9,9],[8,6]] , 运行结果：" + Arrays.deepToString(solution.largestLocal(new int[][]{{9, 9, 8, 1}, {5, 6, 2, 6}, {8, 2, 6, 4}, {6, 2, 2, 2}})));
        System.out.println("预期结果：[[2,2,2],[2,2,2],[2,2,2]] , 运行结果：" + Arrays.deepToString(solution.largestLocal(new int[][]{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 2, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接遍历模拟
        //复杂度分析
        // 时间复杂度：O(n^2)，其中 n 是矩阵 grid 的行数和列数。
        // 空间复杂度：O(1)。这里不考虑返回值的空间。
        public int[][] largestLocal(int[][] grid) {
            int n = grid.length, res[][] = new int[n - 2][n - 2];
            for(int i = 0; i < n - 2; i++){
                for(int j = 0; j < n - 2; j++){
                    for(int x = 0; x < 3; x++){
                        for(int y = 0; y < 3; y++){
                            res[i][j] = Math.max(res[i][j], grid[i + x][j + y]);
                        }
                    }                    
                }
            }
            return res;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}