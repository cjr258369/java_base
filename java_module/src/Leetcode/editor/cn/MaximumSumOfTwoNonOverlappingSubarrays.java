package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-04-26 14:30:57
 * [1031] - 两个非重叠子数组的最大和
 * MaximumSumOfTwoNonOverlappingSubarrays.md
 */
 
//给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，请你找出并返回两个非重叠 子数组 中元素的最大和，长度分别为firstLen 和 secondLen 。 
// 长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。 
// 子数组是数组的一个 连续 部分。 
//
// 示例 1： 
//输入：nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
//输出：20
//解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
//
// 示例 2： 
//输入：nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
//输出：29
//解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
//
// 示例 3： 
//输入：nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
//输出：31
//解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
//
// 提示： 
// 1 <= firstLen, secondLen <= 1000 
// 2 <= firstLen + secondLen <= 1000 
// firstLen + secondLen <= nums.length <= 1000 
// 0 <= nums[i] <= 1000 

public class MaximumSumOfTwoNonOverlappingSubarrays{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumSumOfTwoNonOverlappingSubarrays().new Solution();
        System.out.println("预期结果：20 , 运行结果：" + solution.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2));
        System.out.println("预期结果：29 , 运行结果：" + solution.maxSumTwoNoOverlap(new int[]{3,8,1,3,2,1,8,9,0}, 3, 2));
        System.out.println("预期结果：31 , 运行结果：" + solution.maxSumTwoNoOverlap(new int[]{2,1,5,6,0,9,5,0,3,8}, 4, 3));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路来源：https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/solution/tu-jie-mei-you-si-lu-yi-zhang-tu-miao-do-3lli/
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 为 nums 的长度。
        // 空间复杂度：O(n)。
        public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
            int n = nums.length, preSum[] = new int[n + 1];
            //求前缀和
            for(int i = 0; i < n; i++){
                preSum[i + 1] = preSum[i] + nums[i];
            }
            //System.out.println(Arrays.toString(preSum));
            int ans = 0;
            for(int i = firstLen + secondLen, maxSumA = 0, maxSumB = 0; i <= n; i++){
                maxSumA = Math.max(maxSumA, preSum[i - secondLen] - preSum[i - secondLen - firstLen]);
                maxSumB = Math.max(maxSumB, preSum[i - firstLen] - preSum[i - firstLen - secondLen]);
                ans = Math.max(ans, Math.max(
                        maxSumA + preSum[i] - preSum[i - secondLen],    // 左 a 右 b
                        maxSumB + preSum[i] - preSum[i - firstLen] // 左 b 右 a
                ));                
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}