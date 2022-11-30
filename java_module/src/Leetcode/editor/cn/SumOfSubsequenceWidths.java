package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-11-18 15:42:27
 * [891] - 子序列宽度之和
 * SumOfSubsequenceWidths.md
 */
public class SumOfSubsequenceWidths{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SumOfSubsequenceWidths().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.sumSubseqWidths(new int[]{2,1,3}));
        System.out.println("预期结果：0 , 运行结果：" + solution.sumSubseqWidths(new int[]{2}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 题目求所有子序列的最大最小值的差值的和：
         * 思路是：排序后的所有子序列的最大最小值，和排序前的最大最小值的差值一致。
         * 排序后，会更方便的求每个元素作为最小值或最大值时的贡献量，比如 [1，2，3,4], 2作为子序列最大值，只有1，2， 2作为子序列最小值可以延展至4，
         * 因此排序能够更好的计算 2 的最小值，最大值的贡献量
         * 整体贡献的求和推导详见：https://leetcode.cn/problems/sum-of-subsequence-widths/solution/by-lcbin-gsy6/
         */
        public int sumSubseqWidths(int[] nums) {
            final int MOD = 1000000007;
            Arrays.sort(nums);
            int n = nums.length;
            long ans = 0, p = 1;
            for(int i = 0; i < n; i++){
                ans = (ans + (nums[i] - nums[n - 1 - i]) * p + MOD) % MOD;
                p = (p << 1) % MOD;
            }
            return (int)ans;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}