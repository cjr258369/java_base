package Leetcode.editor.cn;

import java.util.HashMap;

/**
 * 2023-03-10 09:41:33
 * [1590] - 使数组和能被 P 整除
 * MakeSumDivisibleByP.md
 */
 
//给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
// 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
// 子数组 定义为原数组中连续的一组元素。 
//
// 示例 1： 
// 输入：nums = [3,1,4,2], p = 6
//输出：1
//解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
//
// 示例 2： 
// 输入：nums = [6,3,5,2], p = 9
//输出：2
//解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
//
// 示例 3： 
// 输入：nums = [1,2,3], p = 3
//输出：0
//解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
//
// 示例 4： 
// 输入：nums = [1,2,3], p = 7
//输出：-1
//解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
//
// 示例 5： 
// 输入：nums = [1000000000,1000000000,1000000000], p = 3
//输出：0
//
// 提示： 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁹ 
// 1 <= p <= 10⁹ 

public class MakeSumDivisibleByP{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MakeSumDivisibleByP().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.minSubarray(new int[]{3,1,4,2}, 6));
        System.out.println("预期结果：2 , 运行结果：" + solution.minSubarray(new int[]{6,3,5,2}, 9));
        System.out.println("预期结果：0 , 运行结果：" + solution.minSubarray(new int[]{1,2,3}, 3));
        System.out.println("预期结果：-1 , 运行结果：" + solution.minSubarray(new int[]{1,2,3}, 7));
        System.out.println("预期结果：0 , 运行结果：" + solution.minSubarray(new int[]{1000000000,1000000000,1000000000}, 3));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSubarray(int[] nums, int p) {
            int target = 0;
            for(int num : nums) target = (target + num) % p;
            
            //如果所有数的和 % p 为 0 ，则无需删除任何子数组。
            if(target == 0) return 0;
            
            HashMap<Integer, Integer> idxMap = new HashMap<>();
            int res = nums.length;
            for(int i = 0, sum = 0; i < nums.length; i++){
                //哈希表记录 sum 对应的下标为 i
                idxMap.put(sum, i);
                //计算前缀和
                sum = (sum + nums[i]) % p;
                if(idxMap.containsKey((sum - target + p) % p)){
                    res = Math.min(res, i - idxMap.get((sum - target + p) % p) + 1);
                }                
            }
            return res == nums.length ? -1 : res;   
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}