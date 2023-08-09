package Leetcode.editor.cn;

/**
 * 2023-08-04 09:22:24
 * [980] - 不同路径 III
 * UniquePathsIii.md
 */
 
//在二维网格 grid 上，有 4 种类型的方格：
// 1 表示起始方格。且只有一个起始方格。 
// 2 表示结束方格，且只有一个结束方格。 
// 0 表示我们可以走过的空方格。 
// -1 表示我们无法跨越的障碍。 
//
// 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。 
// 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。 
//
// 示例 1： 
// 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
//输出：2
//解释：我们有以下两条路径：
//1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
//2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2) 
//
// 示例 2： 
// 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
//输出：4
//解释：我们有以下四条路径： 
//1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
//2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
//3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
//4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3) 
//
// 示例 3： 
// 输入：[[0,1],[2,0]]
//输出：0
//解释：
//没有一条路能完全穿过每一个空的方格一次。
//请注意，起始和结束方格可以位于网格中的任意位置。
//
// 提示： 
// 1 <= grid.length * grid[0].length <= 20 

public class UniquePathsIii{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new UniquePathsIii().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}}));
        System.out.println("预期结果：4 , 运行结果：" + solution.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,0,2}}));
        System.out.println("预期结果：0. , 运行结果：" + solution.uniquePathsIII(new int[][]{{0,1},{2,0}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(4^(r×c) )，4的(r乘c)次方，其中 r 和 c 分别是 grid 的行数和列数。
        // 空间复杂度：O(r×c)，是回溯的深度。
        int m, n, sx, sy, steps, grid[][], pos[][] = new int[][]{{-1, 0},{1, 0},{0, -1},{0, 1}};
        public int uniquePathsIII(int[][] grid) {
            this.grid = grid;
            m = grid.length;
            n = grid[0].length;
            sx = 0;
            sy = 0;
            //steps 初始值赋值为1，因为要走到2那一格
            steps = 1;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    steps += grid[i][j] == 0 ? 1 : 0;
                    if(grid[i][j] == 1){
                        sx = i;
                        sy = j;
                    }                    
                }
            }
            return dfs(sx, sy, 0);
        }
        
        private int dfs(int r, int c, int step){
            //当前格子是终点，如果所有 0 的格子都走了，则返回 1
            if(grid[r][c] == 2){
                return step == steps ? 1 : 0;
            }
            // 标记为遍历过的路径
            grid[r][c] = 5;
            int ans = 0;
            // 回溯，向四个方向搜索，返回路径数量
            for(int[] p : pos){
                int nx = r + p[0], ny = c + p[1];
                //新的位置如果越界，则跳过，
                if(nx < 0 || nx >= m || ny < 0 || ny >= n){
                    continue;
                }
                //如果新的位置不为0，要么是不能访问，要么已经访问过，也要跳过，如果新的位置为2，则不能跳过，因为是终点，要增加步数进入。
                if(grid[nx][ny] != 0 && grid[nx][ny] != 2){
                    continue;
                }
                ans += dfs(nx, ny, step + 1);                
            }
            // 删除访问标记
            grid[r][c] = 0;
            return ans;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}