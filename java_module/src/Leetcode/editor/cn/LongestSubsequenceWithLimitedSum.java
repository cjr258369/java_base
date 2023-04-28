package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-03-17 14:56:33
 * [2389] - 和有限的最长子序列
 * LongestSubsequenceWithLimitedSum.md
 */
 
//给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。  返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度 。 
//
// 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。 
//
// 示例 1： 
//输入：nums = [4,5,2,1], queries = [3,10,21]
//输出：[2,3,4]
//解释：queries 对应的 answer 如下：
//- 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
//- 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
//- 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
//
// 示例 2： 
//输入：nums = [2,3,4,5], queries = [1]
//输出：[0]
//解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。 
//
// 提示： 
// n == nums.length 
// m == queries.length 
// 1 <= n, m <= 1000 
// 1 <= nums[i], queries[i] <= 10⁶ 

public class LongestSubsequenceWithLimitedSum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LongestSubsequenceWithLimitedSum().new Solution();
        System.out.println("预期结果：[2,3,4] , 运行结果：" + Arrays.toString(solution.answerQueries(new int[]{4, 5, 2, 1}, new int[]{3, 10, 21})));
        System.out.println("预期结果：[0] , 运行结果：" + Arrays.toString(solution.answerQueries(new int[]{2,3,4,5}, new int[]{1})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前缀和 + 二分查找
        // 复杂度分析
        // 时间复杂度：O((n+m)×logn)，其中 n 是数组 nums 的长度，m 是数组 queries 的长度。对 nums 进行排序需要 O(nlogn) 的时间，二分查找需要 O(logn) 的时间。
        // 空间复杂度：O(n)。返回值不计入空间复杂度。
        public int[] answerQueries(int[] nums, int[] queries) {
            Arrays.sort(nums);
            for(int i = 1; i < nums.length; i++){
                nums[i] += nums[i - 1];
            }
            int[] res = new int[queries.length];
            for(int i = 0; i < res.length; i++){
                res[i] = binarySearch_1(nums, queries[i]);
            }
            return res;
        }
        private int binarySearch_1(int[] nums, int target){
            int left = 0, right = nums.length;
            while(left < right){
                int mid = left + ((right - left) / 2);
                if(nums[mid] <= target){
                    left = mid + 1;
                }else{
                    right = mid;
                }               
            }
            return left;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}