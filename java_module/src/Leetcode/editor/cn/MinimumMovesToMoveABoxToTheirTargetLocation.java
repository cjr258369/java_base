package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 2023-05-08 09:13:20
 * [1263] - 推箱子
 * MinimumMovesToMoveABoxToTheirTargetLocation.md
 */
 
//「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
// 游戏地图用大小为 m x n 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
// 现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：
// 
// 玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。 
// 地板用字符 '.' 表示，意味着可以自由行走。 
// 墙用字符 '#' 表示，意味着障碍物，不能通行。 
// 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。 
// 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。 
// 玩家无法越过箱子。
//
// 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。 
//
// 示例 1： 
//输入：grid = [["#","#","#","#","#","#"],
//             ["#","T","#","#","#","#"],
//            ["#",".",".","B",".","#"],
//            ["#",".","#","#",".","#"],
//            ["#",".",".",".","S","#"],
//            ["#","#","#","#","#","#"]]
//输出：3
//解释：我们只需要返回推箱子的次数。 
//
// 示例 2： 
//输入：grid = [["#","#","#","#","#","#"],
//             ["#","T","#","#","#","#"],
//            ["#",".",".","B",".","#"],
//            ["#","#","#","#",".","#"],
//            ["#",".",".",".","S","#"],
//            ["#","#","#","#","#","#"]]
//输出：-1
//
// 示例 3： 
//输入：grid = [["#","#","#","#","#","#"],
//            ["#","T",".",".","#","#"],
//            ["#",".","#","B",".","#"],
//            ["#",".",".",".",".","#"],
//            ["#",".",".",".","S","#"],
//            ["#","#","#","#","#","#"]]
//输出：5
//解释：向下、向左、向左、向上再向上。
//
// 提示： 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 20 
// grid 仅包含字符 '.', '#', 'S' , 'T', 以及 'B'。 
// grid 中 'S', 'B' 和 'T' 各只能出现一个。 

public class MinimumMovesToMoveABoxToTheirTargetLocation{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumMovesToMoveABoxToTheirTargetLocation().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.minPushBox(new char[][]{{'#','#','#','#','#','#'}, {'#','T','#','#','#','#'}, {'#','.','.','B','.','#'}, {'#','.','#','#','.','#'}, {'#','.','.','.','S','#'}, {'#','#','#','#','#','#'}}));
        System.out.println("预期结果：-1 , 运行结果：" + solution.minPushBox(new char[][]{{'#','#','#','#','#','#'}, {'#','T','#','#','#','#'}, {'#','.','.','B','.','#'}, {'#','#','#','#','.','#'}, {'#','.','.','.','S','#'}, {'#','#','#','#','#','#'}}));
        System.out.println("预期结果：5 , 运行结果：" + solution.minPushBox(new char[][]{{'#','#','#','#','#','#'}, {'#','T','.','.','#','#'}, {'#','.','#','B','.','#'}, {'#','.','.','.','.','#'}, {'#','.','.','.','S','#'}, {'#','#','#','#','#','#'}}));
        System.out.println("预期结果：8 , 运行结果：" + solution.minPushBox(new char[][]{{'#','.','.','#','T','#','#','#','#'}, {'#','.','.','#','.','#','.','.','#'}, {'#','.','.','#','.','#','B','.','#'}, {'#','.','.','.','.','.','.','.','#'}, {'#','.','.','.','.','#','.','S','#'}, {'#','.','.','#','.','#','#','#','#'}}));
        //System.out.println("预期结果：? , 运行结果：" + solution.minPushBox(new char[][]{{'#', '.', '.', '#', '#', '#', '#', '#'}, {'#', '.', '.', 'T', '#', '.', '.', '#'}, {'#', '.', '.', '.', '#', 'B', '.', '#'}, {'#', '.', '.', '.', '.', '.', '.', '#'}, {'#', '.', '.', '.', '#', '.', 'S', '#'}, {'#', '.', '.', '#', '#', '#', '#', '#'}}));
        
        //System.out.println("预期结果：? , 运行结果：" + solution.minPushBox(new char[][]{{'#','.','.','#','T','#','#','#','#'}, {'#','.','.','#','.','#','.','.','#'}, {'#','.','.','#','.','#','B','.','#'}, {'#','.','.','.','.','.','.','.','#'}, {'#','.','.','.','.','#','.','S','#'}, {'#','.','.','#','.','#','#','#','#'}}));
        //System.out.println("预期结果：? , 运行结果：" + solution.minPushBox(new char[][]{{'#','.','.','#','T','#','#','#','#'}, {'#','.','.','#','.','#','.','.','#'}, {'#','.','.','#','.','#','B','.','#'}, {'#','.','.','.','.','.','.','.','#'}, {'#','.','.','.','.','#','.','S','#'}, {'#','.','.','#','.','#','#','#','#'}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //各个方向
        int[] d = {0, -1, 0, 1, 0};
        int m, n;
        public int minPushBox(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            // 玩家、箱子的初始位置
            int sx = -1, sy = -1, bx = -1, by = -1;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n ; j++){
                    if(grid[i][j] == 'S'){
                        sx = i;
                        sy = j;
                    }else if(grid[i][j] == 'B'){
                        bx = i;
                        by = j;
                    }
                }
            }
            
            int[][] dp = new int[m * n][m * n];
            for(int[] d : dp){
                Arrays.fill(d, Integer.MAX_VALUE);
            }
            
            Queue<int[]> queue = new ArrayDeque<>();
            // 初始状态的推动次数为 0
            dp[sx * n + sy][bx * n + by] = 0;
            queue.offer(new int[]{sx * n + sy, bx * n + by});
            
            while(!queue.isEmpty()){
                Queue<int[]> queue1 = new ArrayDeque<>();
                while(!queue.isEmpty()){
                    int[] temp = queue.poll();
                    int s1 = temp[0], b1 = temp[1];
                    int sx1 = s1 / n, sy1 = s1 % n, bx1 = b1 / n, by1 = b1 % n;
                    // 箱子已被推到目标处
                    if(grid[bx1][by1] == 'T'){
                        return dp[s1][b1];
                    }

                    // 玩家向四个方向移动到另一个状态
                    for(int i = 0; i < 4; i++){
                        int sx2 = sx1 + d[i], sy2 = sy1 + d[i + 1], s2 = sx2 * n + sy2;
                        // 玩家位置不合法
                        if(!ok(grid, sx2, sy2)){
                            continue;                            
                        }
                        // 推动箱子
                        if(bx1 == sx2 && by1 == sy2){
                            int bx2 = bx1 + d[i], by2 = by1 + d[i + 1], b2 = bx2 * n + by2;
                            // 箱子位置不合法 或 状态已访问
                            if(!ok(grid, bx2, by2) || dp[s2][b2] <= dp[s1][b1] + 1){
                                continue;
                            }
                            dp[s2][b2] = dp[s1][b1] + 1;
                            queue1.offer(new int[]{s2, b2});
                        }else{
                            //玩家动了，但不推箱子
                            //状态已访问
                            if(dp[s2][b1] <= dp[s1][b1]) {
                                continue;
                            }
                            dp[s2][b1] = dp[s1][b1];
                            queue.offer(new int[]{s2, b1});                            
                        }
                    }
                }
                queue = queue1;
            }
            return -1;    
        }

        // 不越界且不在墙上
        private boolean ok(char[][] grid, int x, int y){
            return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#';            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}