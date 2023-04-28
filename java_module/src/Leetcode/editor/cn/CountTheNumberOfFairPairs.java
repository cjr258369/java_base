package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-02-13 16:53:06
 * [2563] - 统计公平数对的数目
 * CountTheNumberOfFairPairs.md
 */
 
//给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
// 如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：  0 <= i < j < n，且  lower <= nums[i] + nums[j] <= upper 
//
// 示例 1： 
//输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
//输出：6
//解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
//
// 示例 2： 
//输入：nums = [1,7,9,2,5], lower = 11, upper = 11
//输出：1
//解释：只有单个公平数对：(2,3) 。
//
// 提示： 
// 1 <= nums.length <= 10⁵ 
// nums.length == n 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= lower <= upper <= 10⁹ 

public class CountTheNumberOfFairPairs{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountTheNumberOfFairPairs().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.countFairPairs(new int[]{0,1,7,4,4,5}, 3, 6));
        System.out.println("预期结果：1 , 运行结果：" + solution.countFairPairs(new int[]{1,7,9,2,5}, 11, 11));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long countFairPairs(int[] nums, int lower, int upper) {
            Arrays.sort(nums);
            long count = 0;
            for(int i = 0, j = nums.length - 1, k = nums.length - 1; i < nums.length; i++){
                // 找到第一个 >= lower 的前一个位置
                while(j >= 0 && nums[i] + nums[j] >= lower){
                    j--;
                }
                // 找到第一个 <= upper 的位置
                while(k >= 0 && nums[i] + nums[k] > upper){
                    k--;
                }
                // 若定位的 i 夹在 j、k 之间，则会多统计一次 (i,i) 数对，故要 -1
                count += k - j - (i > j && i <= k ? 1 : 0); 
            }
            // 数对，每个数对统计了两次
            return count / 2;
    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}