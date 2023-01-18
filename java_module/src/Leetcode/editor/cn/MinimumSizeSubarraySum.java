package Leetcode.editor.cn;

/**
 * 2023-01-17 11:27:56
 * [209] - 长度最小的子数组
 * MinimumSizeSubarraySum.md
 */
 
//给定一个含有 n 个正整数的数组和一个正整数 target 。 
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。 
//
// 示例 1： 
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
//
// 示例 2： 
//输入：target = 4, nums = [1,4,4]
//输出：1
//
// 示例 3： 
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
//
// 提示： 
// 1 <= target <= 10⁹ 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁵ 
//
// 进阶： 
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。

public class MinimumSizeSubarraySum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumSizeSubarraySum().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println("预期结果：1 , 运行结果：" + solution.minSubArrayLen(4, new int[]{1,4,4}));
        System.out.println("预期结果：0 , 运行结果：" + solution.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //时间复杂度：O(n)，因为 left，和 right，均最多只执行 n 次，并不存在 for 里面嵌套的 O(n) 的情况
        //空间复杂度：O(1)，只使用了 4 个常量
        public int minSubArrayLen(int target, int[] nums) {
            int min = Integer.MAX_VALUE;
            for(int left = 0, right = 0, sum = 0; right < nums.length; right++){
                sum += nums[right];
                //while(left <= right && sum >= target){    //为什么不需要判断：left <= right 因为 left = right 时， sum - nums[left] = 0，而 0 不会大于任何一个正整数（target）
                while(sum >= target){
                    min = Math.min(min, right - left + 1);
                    System.out.println("sum = " + sum + " , left = " + left + " , right = " + right + " , min = " + min);
                    sum -= nums[left++];
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}