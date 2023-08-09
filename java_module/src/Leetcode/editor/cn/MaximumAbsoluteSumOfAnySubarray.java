package Leetcode.editor.cn;

/**
 * 2023-08-08 09:38:28
 * [1749] - 任意子数组和的绝对值的最大值
 * MaximumAbsoluteSumOfAnySubarray.md
 */
 
//给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。 
// 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。 
// abs(x) 定义如下： 
// 如果 x 是负整数，那么 abs(x) = -x 。 
// 如果 x 是非负整数，那么 abs(x) = x 。 
//
// 示例 1： 
//输入：nums = [1,-3,2,3,-4]
//输出：5
//解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
//
// 示例 2： 
//输入：nums = [2,-5,1,-4,3,-2]
//输出：8
//解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
//
// 提示： 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 

public class MaximumAbsoluteSumOfAnySubarray{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumAbsoluteSumOfAnySubarray().new Solution();
        System.out.println("预期结果：5 , 运行结果：" + solution.maxAbsoluteSum(new int[]{1,-3,2,3,-4}));
        System.out.println("预期结果：8 , 运行结果：" + solution.maxAbsoluteSum(new int[]{2,-5,1,-4,3,-2}));
        System.out.println("预期结果：1 , 运行结果：" + solution.maxAbsoluteSum(new int[]{-1}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxAbsoluteSum(int[] nums) {
            int ans = 0, max = 0, min = 0;
            for(int num : nums){
                max = Math.max(max, 0) + num;
                min = Math.min(min, 0) + num;
                ans = Math.max(ans, Math.max(max, -min));
            }
            return ans; 
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}