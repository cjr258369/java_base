package Leetcode.editor.cn;

/**
 * 2022-11-28 14:40:23
 * [813] - 最大平均值和的分组
 * LargestSumOfAverages.md
 */
public class LargestSumOfAverages{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LargestSumOfAverages().new Solution();
        System.out.println("预期结果：20.00000 , 运行结果：" + solution.largestSumOfAverages(new int[]{9,1,2,3,9}, 3));
        System.out.println("预期结果：20.50000 , 运行结果：" + solution.largestSumOfAverages(new int[]{1,2,3,4,5,6,7}, 4));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double preSum[] = new double[n + 1], dp[][] = new double[n + 1][k + 1];
        for(int i = 0; i < n; i++){
            preSum[i + 1] = preSum[i] + nums[i];
        }
        //初始化边界
        for(int i = 1; i <= n; i++){
            dp[i][1] = preSum[i] / i;
        }
        
        for(int j = 2; j <= k; j++){
            for(int i = 0; i <= n; i++){
                for(int x = j - 1; x < i; x++){
                    dp[i][j] = Math.max(dp[i][j], dp[x][j - 1] + (preSum[i] - preSum[x]) / (i - x));
                    
                }
            }
        }
        
        return dp[n][k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}