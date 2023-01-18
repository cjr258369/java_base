package Leetcode.editor.cn;

/**
 * 2023-01-17 14:52:41
 * [713] - 乘积小于 K 的子数组
 * SubarrayProductLessThanK.md
 */
 
//给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
//
// 示例 1： 
//输入：nums = [10,5,2,6], k = 100
//输出：8
//解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
//需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
//
// 示例 2： 
//输入：nums = [1,2,3], k = 0
//输出：0 
//
// 提示: 
// 1 <= nums.length <= 3 * 10⁴ 
// 1 <= nums[i] <= 1000 
// 0 <= k <= 10⁶ 

public class SubarrayProductLessThanK{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SubarrayProductLessThanK().new Solution();
        System.out.println("预期结果：8 , 运行结果：" + solution.numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
        System.out.println("预期结果：0 , 运行结果：" + solution.numSubarrayProductLessThanK(new int[]{1,2,3}, 0));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 是数组 nums 的长度。两个端点 i 和 j 的增加次数都不超过 n。
        // 空间复杂度：O(1)。
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            //因为 nums 中的每一个元素都是正整数，所以乘积必为正整数，所以 k 等于 0 或 k 等于 1 的子数组是不存在的。
            //其次因为：prod 的值始终不超过 k × maxl{nums[l]}，因此无需担心整型溢出的问题。
            if(k <= 1){
                return 0;
            }
            int cnt = 0;
            for(int left = 0, right = 0, prod = 1; right < nums.length; right++){
                prod *= nums[right];
                while(prod >= k){
                    prod /= nums[left++];
                }
                cnt += right - left + 1;
            }
            return cnt;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}