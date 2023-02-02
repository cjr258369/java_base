package Leetcode.editor.cn;

/**
 * 2023-01-31 09:14:50
 * [2319] - 判断矩阵是否是一个 X 矩阵
 * CheckIfMatrixIsXMatrix.md
 */
 
//如果一个正方形矩阵满足下述 全部 条件，则称之为一个 X 矩阵 ： 
// 
// 矩阵对角线上的所有元素都 不是 0，矩阵中所有其他元素都是 0 
// 给你一个大小为 n x n 的二维整数数组 grid ，表示一个正方形矩阵。如果 grid 是一个 X 矩阵 ，返回 true ；否则，返回 false 。 
//
// 示例 1： 
// 输入：grid = [[2,0,0,1],[0,3,1,0],[0,5,2,0],[4,0,0,2]]
//输出：true
//解释：矩阵如上图所示。
//X 矩阵应该满足：绿色元素（对角线上）都不是 0 ，红色元素都是 0 。因此，grid 是一个 X 矩阵。
//
// 示例 2： 
// 输入：grid = [[5,7,0],[0,3,1],[0,5,0]]
//输出：false
//解释：矩阵如上图所示。
//X 矩阵应该满足：绿色元素（对角线上）都不是 0 ，红色元素都是 0 。因此，grid 不是一个 X 矩阵。
//
// 提示： 
// n == grid.length == grid[i].length 
// 3 <= n <= 100 
// 0 <= grid[i][j] <= 10⁵ 

public class CheckIfMatrixIsXMatrix{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CheckIfMatrixIsXMatrix().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.checkXMatrix(new int[][]{{2,0,0,1}, {0,3,1,0},{0,5,2,0},{4,0,0,2}}));
        System.out.println("预期结果：false , 运行结果：" + solution.checkXMatrix(new int[][]{{5,7,0}, {0,3,1}, {0,5,0}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接模拟
        //复杂度分析
        // 时间复杂度：O(n^2)，其中 n 为正方形矩阵 grid 的行列数。
        // 空间复杂度：O(1)。仅使用常量空间。
        public boolean checkXMatrix(int[][] grid) {
            for(int i = 0, n = grid.length; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(i == j || j == n - 1 - i){   //对角线
                        if(grid[i][j] == 0){
                            return false;
                        }
                    }else{  //非对角线
                        if(grid[i][j] != 0){
                            return false;
                        }                        
                    }
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}