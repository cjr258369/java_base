package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2022-10-25 09:07:27
 * [934] - 最短的桥
 * ShortestBridge.md
 */
public class ShortestBridge{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution2 solution = new ShortestBridge().new Solution2();
        System.out.println("运行结果：" + solution.shortestBridge(new int[][]{{0,1},{1,0}}));
        System.out.println("运行结果：" + solution.shortestBridge(new int[][]{{0,1,0},{0,0,0},{0,0,1}}));
        System.out.println("运行结果：" + solution.shortestBridge(new int[][]{{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] position = new int[][]{{1,0}, {-1,0},{0,1},{0,-1}};
        Deque<int[]> queue;
        int n;
        public int shortestBridge(int[][] grid) {
            n = grid.length;
            queue = new ArrayDeque<>();
            boolean flag = false;
            //深搜，同时把 岛屿1 的所有点入队，并标记为2
            for(int i = 0; !flag && i < n; i++){
                for(int j = 0; !flag && j < n; j++){
                    if(grid[i][j] == 1){
                        dfs(grid, i, j);
                        flag = true;
                    }
                }
            }
            
            //广搜最短路，即为层级
            int level = 0;
            while(!queue.isEmpty()){
                int size = queue.size();
                for(int i = 0; i < size; i++){
                    int[] t = queue.poll();
                    for(int[] pos : position){
                        int r = t[0] + pos[0], c = t[1] + pos[1];
                        if(r >= 0 && r < n && c >= 0 && c < n){
                            if(grid[r][c] == 0){
                                queue.offer(new int[]{r,c});
                                grid[r][c] = 2;
                            }else if(grid[r][c] == 1){
                                return level;
                            }
                        }
                    }
                }
                level++;
            }
            return 0;
        }
        
        public void dfs(int[][] grid, int r, int c){
            //当前坐标越出边界，或已经不等于 1 了，说明已经到了 岛屿1 的边界，所以返回
            if(r < 0 || r >= n || c < 0 || c >= n || grid[r][c] != 1) return;
            //入队并标记，然后继续朝4个方向深搜
            queue.offer(new int[]{r,c});
            grid[r][c] = 2;
            for(int[] pos : position){
                dfs(grid, r + pos[0], c + pos[1]);
            }
        }
    }
    
    class Solution2 {
        int[][] position = new int[][]{{1,0}, {-1,0},{0,1},{0,-1}};
        Deque<int[]> queue;
        int n;
        public int shortestBridge(int[][] grid) {
            n = grid.length;
            queue = new ArrayDeque<>();
            boolean flag = false;
            //深搜，同时把 岛屿1 的所有点入队，并标记为2
            for(int i = 0; !flag && i < n; i++){
                for(int j = 0; !flag && j < n; j++){
                    if(grid[i][j] == 1){
                        dfs(grid, i, j);
                        
                        //深搜完，直接开始广搜： 广搜最短路，即为层级
                        int level = 0;
                        while(!queue.isEmpty()){
                            int size = queue.size();
                            for(int k = 0; k < size; k++){
                                int[] t = queue.poll();
                                for(int[] pos : position){
                                    int r = t[0] + pos[0], c = t[1] + pos[1];
                                    if(r >= 0 && r < n && c >= 0 && c < n){
                                        if(grid[r][c] == 0){
                                            queue.offer(new int[]{r,c});
                                            grid[r][c] = 2;
                                        }else if(grid[r][c] == 1){
                                            return level;
                                        }
                                    }
                                }
                            }
                            level++;
                        }
                        
                        //标志位，不需要外层循环了
                        flag = true;
                    }
                }
            }
            
    
            return 0;
        }
        
        public void dfs(int[][] grid, int r, int c){
            //当前坐标越出边界，或已经不等于 1 了，说明已经到了 岛屿1 的边界，所以返回
            if(r < 0 || r >= n || c < 0 || c >= n || grid[r][c] != 1) return;
            //入队并标记，然后继续朝4个方向深搜
            queue.offer(new int[]{r,c});
            grid[r][c] = 2;
            for(int[] pos : position){
                dfs(grid, r + pos[0], c + pos[1]);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}