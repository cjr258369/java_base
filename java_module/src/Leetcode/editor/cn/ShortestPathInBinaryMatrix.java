package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 2023-05-26 09:25:48
 * [1091] - 二进制矩阵中的最短路径
 * ShortestPathInBinaryMatrix.md
 */
 
//给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
// 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求： 
// 路径途经的所有单元格都的值都是 0 。 
// 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。 
//
// 畅通路径的长度 是该路径途经的单元格总数。 
//
// 示例 1： 
//输入：grid = [[0,1],[1,0]]
//输出：2
//
// 示例 2： 
//输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
//输出：4
//
// 示例 3： 
//输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
//输出：-1
//
// 提示： 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 100 
// grid[i][j] 为 0 或 1 

public class ShortestPathInBinaryMatrix{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ShortestPathInBinaryMatrix().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.shortestPathBinaryMatrix(new int[][]{{0,1},{1,0}}));
        System.out.println("预期结果：4 , 运行结果：" + solution.shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
        System.out.println("预期结果：-1 , 运行结果：" + solution.shortestPathBinaryMatrix(new int[][]{{1,0,0},{1,1,0},{1,1,0}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n^2)，其中 n 是数组的行数或列数。广度优先搜索最多访问 n^2 个单元格。
        // 空间复杂度：O(n^2)。队列 q 不超过 n^2 个元素，保存队列 q 需要 O(n^2) 的空间。
        
        int[][] pos = new int[][]{{-1, -1},{-1, 0},{-1, 1},{0, -1},{0, 1},{1, -1},{1, 0},{1, 1}};
        public int shortestPathBinaryMatrix(int[][] grid) {
            if(grid[0][0] == 1) return -1;
            
            int m = grid.length, n = grid[0].length, ans = 1;
            
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{0, 0});
            grid[0][0] = -1;
            while(!queue.isEmpty()){
                for(int i = queue.size(); i > 0; i--){
                    int[] node = queue.poll();
                    if(node[0] == m - 1 && node[1] == n - 1){
                        return ans;
                    }
                    //8个方向
                    for(int[] p : pos){
                        int x = node[0] + p[0], y = node[1] + p[1];
                        //越界 或者为 1 或者已访问过，则跳过
                        if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 1 || grid[x][y] == -1){
                            continue;
                        }
                        grid[x][y] = -1;
                        queue.offer(new int[]{x, y});
                    }                    
                }
                ans++;
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}