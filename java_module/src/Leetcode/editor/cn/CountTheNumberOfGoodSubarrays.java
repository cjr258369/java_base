package Leetcode.editor.cn;

import java.util.HashMap;

/**
 * 2023-01-17 15:33:42
 * [2537] - 统计好子数组的数目
 * CountTheNumberOfGoodSubarrays.md
 */
 
//给你一个整数数组 nums 和一个整数 k ，请你返回 nums 中 好 子数组的数目。一个子数组 arr 如果有 至少 k 对下标 (i, j) 满足 i < j 且 arr[i] == arr[j] ，那么称它是一个 好 子数组。 
// 子数组 是原数组中一段连续 非空 的元素序列。 
//
// 示例 1： 
// 输入：nums = [1,1,1,1,1], k = 10
//输出：1
//解释：唯一的好子数组是这个数组本身。
//
// 示例 2： 
// 输入：nums = [3,1,4,3,2,2,4], k = 2
//输出：4
//解释：总共有 4 个不同的好子数组：
//- [3,1,4,3,2,2] 有 2 对。
//- [3,1,4,3,2,2,4] 有 3 对。
//- [1,4,3,2,2,4] 有 2 对。
//- [4,3,2,2,4] 有 2 对。
//
// 提示： 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i], k <= 10⁹ 

public class CountTheNumberOfGoodSubarrays{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountTheNumberOfGoodSubarrays().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.countGood2(new int[]{1,1,1,1,1}, 10));
        System.out.println("预期结果：4 , 运行结果：" + solution.countGood2(new int[]{3,1,4,3,2,2,4}, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //关键是窗口中的对数如何计算
        // 如果当前窗口中已经有c个元素x了，再来一个x，会增加c对
        // 如果当前窗口中已经有c个元素x了，去掉一个x，会减少c-1对
        
        //【41/41，59ms，62.7m】
        public long countGood(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();
            long ans = 0L;
            for(int left = 0, right = 0, pairs = 0; right < nums.length; right++){
                pairs += map.getOrDefault(nums[right], 0);
                map.merge(nums[right], 1, Integer::sum);
                while(pairs - map.get(nums[left]) + 1 >= k){
                    pairs -= map.merge(nums[left++], -1, Integer::sum);
                }
                ans += (pairs >= k) ? left + 1 : 0;
            }
            return ans;    
        }
        
        //merge 效率不高，直接全使用 getOrDefault
        //【41/41，44ms，53.7m】
        public long countGood2(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();
            long ans = 0L, pairs = 0;
            for(int left = 0, right = 0; right < nums.length; right++, ans += left){
                int pair = map.getOrDefault(nums[right], 0);
                pairs += pair++;
                map.put(nums[right], pair);
                while(pairs >= k){
                    pairs -= map.put(nums[left], map.get(nums[left++]) - 1) - 1;
                }
            }
            return ans; 
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}