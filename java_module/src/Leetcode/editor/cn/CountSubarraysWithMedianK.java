package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 2023-03-16 09:19:06
 * [2488] - 统计中位数为 K 的子数组
 * CountSubarraysWithMedianK.md
 */
 
//给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。另给你一个正整数 k 。 统计并返回 nums 中的 中位数 等于 k 的非空子数组的数目。 
//
// 注意： 
// 数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。 
// 例如，[2,3,1,4] 的中位数是 2 ，[8,4,3,5,1] 的中位数是 4 。 
// 子数组是数组中的一个连续部分。 
//
// 示例 1： 
//输入：nums = [3,2,1,4,5], k = 4
//输出：3
//解释：中位数等于 4 的子数组有：[4]、[4,5] 和 [1,4,5] 。
//
// 示例 2： 
//输入：nums = [2,3,1], k = 3
//输出：1
//解释：[3] 是唯一一个中位数等于 3 的子数组。
//
// 提示： 
// n == nums.length 
// 1 <= n <= 10⁵ 
// 1 <= nums[i], k <= n 
// nums 中的整数互不相同 

public class CountSubarraysWithMedianK{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountSubarraysWithMedianK().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.countSubarrays(new int[]{3,2,1,4,5}, 4));
        System.out.println("预期结果：1 , 运行结果：" + solution.countSubarrays(new int[]{2,3,1}, 3));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前缀和
        // 复杂度分析
        // 时间复杂度：O(n)，其中 n 是数组 nums 的长度。遍历数组寻找正整数 k 所在下标需要 O(n) 的时间，遍历数组计算中位数等于 k 的非空子数组数目需要 O(n) 的时间。
        // 空间复杂度：O(n)，其中 n 是数组 nums 的长度。哈希表需要 O(n) 的空间。
        public int countSubarrays(int[] nums, int k) {
            int kIdx = 0, ans = 0;
            while(nums[kIdx] != k){
                kIdx++;
            }
            HashMap<Integer, Integer> counts = new HashMap<>();
            counts.put(0, 1);
            for(int i = 0, sum = 0; i < nums.length; i++){
                sum += sign(nums[i] - k);
                if(i < kIdx){
                    counts.put(sum, counts.getOrDefault(sum, 0) + 1);
                }else{
                    ans += counts.getOrDefault(sum, 0) + counts.getOrDefault(sum - 1, 0);
                }                
            }
            return ans;    
        }
        
        private int sign(int num){
            if(num == 0){
                return 0;
            }
            return num > 0 ? 1 : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}