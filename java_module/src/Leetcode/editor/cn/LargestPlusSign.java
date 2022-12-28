package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 2022-11-09 11:36:56
 * [764] - 最大加号标志
 * LargestPlusSign.md
 */
public class LargestPlusSign{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LargestPlusSign().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.orderOfLargestPlusSign2(5, new int[][]{{4,2}}));
        System.out.println("预期结果：0 , 运行结果：" + solution.orderOfLargestPlusSign2(1, new int[][]{{0,0}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int orderOfLargestPlusSign(int n, int[][] mines) {
            int[][] dp = new int[n][n];
            for(int i = 0; i < n; i++) Arrays.fill(dp[i], n);
    
            HashSet<Integer> mineIdx = new HashSet<>();
            for(int[] mine : mines) mineIdx.add(mine[0] * n + mine[1]);
            
            int ans = 0;
            for(int i = 0; i < n; i++){
                int count = 0;
                /* left */
                for(int j = 0; j < n; j++){
                    if(mineIdx.contains(i * n + j)){
                        count = 0;
                    }else{
                        count++;
                    }
                    dp[i][j] = Math.min(dp[i][j], count);                
                }
                
                /* right */
                count = 0;
                for(int j = n - 1; j >= 0; j--){
                    if(mineIdx.contains(i * n + j)){
                        count = 0;
                    }else{
                        count++;
                    }
                    dp[i][j] = Math.min(dp[i][j], count);
                }
            }
            
            for(int i = 0; i < n; i++){
                /* up */
                int count = 0;
                for(int j = 0; j < n; j++){
                    if(mineIdx.contains(j * n + i)){
                        count = 0;
                    }else{
                        count++;
                    }
                    dp[j][i] = Math.min(dp[j][i], count);
                }
    
                /* down */
                count = 0;
                for(int j = n - 1; j >= 0; j--){
                    if(mineIdx.contains(j * n + i)){
                        count = 0;
                    }else{
                        count++;
                    }
                    dp[j][i] = Math.min(dp[j][i], count);
                    ans = Math.max(ans, dp[j][i]);
                }
            }
            return ans;
        }
        
        /* 用数组代替 set 效率高点 */
        public int orderOfLargestPlusSign2(int n, int[][] mines) {
            int[][] grid = new int[n][n];
            HashSet<Integer> mineIdx = new HashSet<>();
            /* 生成矩阵grid。注意这里的0和1是反的，这并不会影响结果，判断的时候反过来即可。*/
            for(int[] mine : mines) grid[mine[0]][mine[1]] = 1;

            int[][] dp = new int[n][n];
            
            int ans = 0;
            for(int i = 0; i < n; i++){
                int count = 0;
                /* left */
                for(int j = 0; j < n; j++){
                    count = grid[i][j] == 1 ? 0 : ++count;
                    dp[i][j] = count;              
                }
                
                /* right */
                count = 0;
                for(int j = n - 1; j >= 0; j--){
                    count = grid[i][j] == 1 ? 0 : ++count;
                    dp[i][j] = Math.min(dp[i][j], count);
                }
            }
            
            for(int i = 0; i < n; i++){
                /* up */
                int count = 0;
                for(int j = 0; j < n; j++){
                    count = grid[j][i] == 1 ? 0 : ++count;
                    dp[j][i] = Math.min(dp[j][i], count);
                }
    
                /* down */
                count = 0;
                for(int j = n - 1; j >= 0; j--){
                    count = grid[j][i] == 1 ? 0 : ++count;
                    dp[j][i] = Math.min(dp[j][i], count);
                    ans = Math.max(ans, dp[j][i]);
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}