package Leetcode.editor.cn;

/**
 * 2023-06-19 11:32:18
 * [1254] - 统计封闭岛屿的数目
 * NumberOfClosedIslands.md
 */
 
//二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
// 请返回 封闭岛屿 的数目。 
//
// 示例 1： 
//输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
//输出：2
//解释：
//灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。 
//
// 示例 2： 
//输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//输出：1
//
// 示例 3： 
//输入：grid = [[1,1,1,1,1,1,1], [1,0,0,0,0,0,1], [1,0,1,1,1,0,1], [1,0,1,0,1,0,1], [1,0,1,1,1,0,1], [1,0,0,0,0,0,1], [1,1,1,1,1,1,1]]
//输出：2
//
// 提示： 
// 1 <= grid.length, grid[0].length <= 100 
// 0 <= grid[i][j] <=1 

public class NumberOfClosedIslands{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOfClosedIslands().new Solution();
        //System.out.println("预期结果：2 , 运行结果：" + solution.closedIsland(new int[][]{{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}}));
        //System.out.println("预期结果：1 , 运行结果：" + solution.closedIsland(new int[][]{{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}}));
        //System.out.println("预期结果：2 , 运行结果：" + solution.closedIsland(new int[][]{{1,1,1,1,1,1,1},{1,0,0,0,0,0,1},{1,0,1,1,1,0,1},{1,0,1,0,1,0,1},{1,0,1,1,1,0,1},{1,0,0,0,0,0,1},{1,1,1,1,1,1,1}}));
        System.out.println("预期结果：5 , 运行结果：" + solution.closedIsland(new int[][]{{0,0,1,1,0,1,0,0,1,0},{1,1,0,1,1,0,1,1,1,0},{1,0,1,1,1,0,0,1,1,0},{0,1,1,0,0,0,0,1,0,1},{0,0,0,0,0,0,1,1,1,0},{0,1,0,1,0,1,0,1,1,1},{1,0,1,0,1,1,0,0,0,1},{1,1,1,1,1,1,0,0,0,0},{1,1,1,0,0,1,0,1,0,1},{1,1,1,0,1,1,0,1,1,0}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //复杂度分析
        // 时间复杂度：O(mn)，其中 m,n 分别为矩阵的行数与列数。利用深度优先搜索只需对整个矩阵遍历一遍即可，因此时间复杂度为 O(mn)。
        // 空间复杂度：O(1)。直接在原矩阵中进行标记即可，不需要额外的空间。
        
        private int m, n, grid[][];
        public int closedIsland(int[][] grid) {
            this.grid = grid;
            m = grid.length;
            n = grid[0].length;
            int ans = 0;
            
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(grid[i][j] == 0 && dfs(i, j)){
                        ans++;
                    }
                }
            }
            return ans;
        }
        
        private boolean dfs(int r, int c){
            if(r < 0 || c < 0 || r >= m || c >= n){
                return false;
            }
            if(grid[r][c] != 0){
                return true;
            }
            grid[r][c] = -1;
            boolean res1 = dfs(r - 1, c);
            boolean res2 = dfs(r + 1, c);
            boolean res3 = dfs(r, c - 1);
            boolean res4 = dfs(r, c + 1);
            return res1 && res2 && res3 && res4;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}