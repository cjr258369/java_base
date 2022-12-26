package Leetcode.editor.cn;

/**
 * 2022-12-22 11:32:53
 * [1799] - N 次操作后的最大分数和
 * MaximizeScoreAfterNOperations.md
 */
public class MaximizeScoreAfterNOperations{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximizeScoreAfterNOperations().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.maxScore(new int[]{1,2}));
        System.out.println("预期结果：11 , 运行结果：" + solution.maxScore(new int[]{3,4,6,8}));
        System.out.println("预期结果：14 , 运行结果：" + solution.maxScore(new int[]{1,2,3,4,5,6}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxScore(int[] nums) {
            int n = nums.length, dp[] = new int[1<<n], gcdTmp[][] = new int[n][n];
            //初始化所有gcd
            for(int i = 0; i < n; i++){
                for(int j = i + 1; j < n; j++){
                    gcdTmp[i][j] = gcd(nums[i], nums[j]);
                }
            }
            
            int all = 1 << n;
            for(int s = 1; s < all; s++){
                int t = Integer.bitCount(s);
                if((t & 1) != 0) continue;
                
                for(int i = 0; i < n; i++){
                    if(((s>>i) & 1) != 0){
                        for(int j = i + 1; j < n; j++){
                            if(((s>>j) & 1) != 0){
                                dp[s] = Math.max(dp[s], dp[s ^ (1<<i) ^ (1<<j)] + t/2 * gcdTmp[i][j]);
                            }
                        }
                    }
                }
            }
            return dp[all - 1];    
        }
        
        public int gcd(int a, int b){
            return a % b == 0 ? b : gcd(b, a % b);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}