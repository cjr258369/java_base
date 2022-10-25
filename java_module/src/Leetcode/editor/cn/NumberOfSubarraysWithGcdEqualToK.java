package Leetcode.editor.cn;

import java.math.BigInteger;

/**
 * 2022-10-24 11:15:32
 * [2447] - 最大公因数等于 K 的子数组数目
 * NumberOfSubarraysWithGcdEqualToK.md
 */
public class NumberOfSubarraysWithGcdEqualToK{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOfSubarraysWithGcdEqualToK().new Solution();
        System.out.println("运行结果：" + solution.subarrayGCD(new int[]{9,3,1,2,6,3}, 3));
        System.out.println("运行结果：" + solution.subarrayGCD(new int[]{4}, 7));
        System.out.println("运行结果：" + solution.subarrayGCD(new int[]{3,3,3}, 3));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subarrayGCD(int[] nums, int k) {
            int ans = 0;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] % k != 0) continue;
                BigInteger g = BigInteger.ZERO;
                for(int j = i; j < nums.length; j++){
                    ans += (g = g.gcd(BigInteger.valueOf(nums[j]))).intValue() == k ? 1 : 0;
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}