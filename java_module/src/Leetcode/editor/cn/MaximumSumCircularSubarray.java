package Leetcode.editor.cn;

/**
 * 2023-07-20 15:34:39
 * [918] - 环形子数组的最大和
 * MaximumSumCircularSubarray.md
 */
 
//给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
// 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
// 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。 
//
// 示例 1： 
//输入：nums = [1,-2,3,-2]
//输出：3
//解释：从子数组 [3] 得到最大和 3
//
// 示例 2： 
//输入：nums = [5,-3,5]
//输出：10
//解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
//
// 示例 3： 
//输入：nums = [3,-2,2,-3]
//输出：3
//解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
//
// 提示： 
// n == nums.length 
// 1 <= n <= 3 * 10⁴ 
// -3 * 10⁴ <= nums[i] <= 3 * 10⁴ 

public class MaximumSumCircularSubarray{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumSumCircularSubarray().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.maxSubarraySumCircular(new int[]{1,-2,3,-2}));
        System.out.println("预期结果：10 , 运行结果：" + solution.maxSubarraySumCircular(new int[]{5,-3,5}));
        System.out.println("预期结果：3 , 运行结果：" + solution.maxSubarraySumCircular(new int[]{3,-2,2,-3}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //如果子数组没有跨越边界，只有中间一段，就是普通的最大子数组和。
        //如果子数组 a 跨越边界，考虑不在子数组中的数，它们组成了一个中间的子数组 b。那么有 sum(a)+sum(b)=sum(nums)=常数。
        //所以 b 的元素和越小，a 的元素和就越大，所以相当于求最小子数组和。用整个数组的元素和，减去最小子数组和，就得到了跨越边界的最大子数组和了
        
        
        public int maxSubarraySumCircular(int[] nums) {
            int total=0, maxSum=nums[0], currMax=0, minSum=nums[0], currMin=0;
            for(int n : nums){
                total+=n;
                currMax = Math.max(currMax + n , n);
                maxSum = Math.max(maxSum , currMax);
                currMin = Math.min(currMin + n , n);
                minSum = Math.min(currMin , minSum);
            }
            return maxSum>0?Math.max(maxSum , total - minSum):maxSum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}