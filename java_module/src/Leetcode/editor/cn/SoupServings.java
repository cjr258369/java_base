package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-11-21 11:34:17
 * [808] - 分汤
 * SoupServings.md
 */
public class SoupServings{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SoupServings().new Solution();
        System.out.println("预期结果：0.62500 , 运行结果：" + solution.soupServings2(50));
        System.out.println("预期结果：0.71875 , 运行结果：" + solution.soupServings2(100));
        System.out.println("预期结果：0.9999893866772255 , 运行结果：" + solution.soupServings2(4450));
        System.out.println("预期结果：0.9999902113072541 , 运行结果：" + solution.soupServings2(4451));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //全概率公式：P(X) = P(X|Y1)*P(Y1) + P(X|Y2)*P(Y2) + P(X|Y3)*P(Y3) + P(X|Y4)*P(Y4)
        //n >= 4475 时，计算结果已经为：0.99999，可以直接返回1，n小于此值时，通过dp来求。
        // 4475 这个数来源于A 和 B 的期望值
        public double soupServings(int n) {
            n = (int)Math.ceil((double)n/25);
            if(n > 179){
                return 1.0;
            }
            double[][] dp = new double[n+1][n+1];
            Arrays.fill(dp[0], 1.0);
            dp[0][0] = 0.5;
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    //dp 公式，因为越界问题，全部都需要加判断
                    //dp[i][j] = (dp[i-4][j] + dp[i - 3][j - 1] + dp[i - 2][j - 2] + dp[i - 1][j - 3]) / 4.0;
                    dp[i][j] = (dp[Math.max(0, i-4)][j] + dp[Math.max(0, i - 3)][Math.max(0, j - 1)] + dp[Math.max(0, i - 2)][Math.max(0, j - 2)] + dp[Math.max(0, i - 1)][Math.max(0, j - 3)]) / 4.0;
                }
            }
            return dp[n][n];    
        }
        
        //同样动态规划的解题思路我们还可以采用自顶向下的记忆化搜索的方法来实现，与自底向上的动态规划相比记忆化搜索会减少许多无效状态的计算。
        //因为有记忆化，减少了很多无效状态的计算，所以效率更好
        public double soupServings2(int n) {
            n = (int)Math.ceil((double)n/25);
            if(n > 179){
                return 1.0;
            }
            double[][] memo = new double[n+1][n+1];
            return dfs(n, n, memo);
        }
        
        double dfs(int a, int b, double[][] memo){
            if(a <= 0 && b <= 0){
                return 0.5;
            }else if(a <= 0){
                return 1.0;                
            }else if(b <= 0){
                return 0.0;
            }
            
            //未初始化
            if(memo[a][b] == 0){
                memo[a][b] = 0.25 * (dfs(a - 4, b, memo) + dfs(a - 3, b - 1, memo) + dfs(a - 2, b - 2, memo) + dfs(a - 1, b - 3, memo));
            }
            
            return memo[a][b];            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}