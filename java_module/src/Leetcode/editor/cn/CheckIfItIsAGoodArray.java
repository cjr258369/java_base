package Leetcode.editor.cn;

import java.math.BigInteger;

/**
 * 2023-02-15 15:30:55
 * [1250] - 检查「好数组」
 * CheckIfItIsAGoodArray.md
 */
 
//给你一个正整数数组 nums，你需要从中任选一些子集，然后将子集中每一个数乘以一个 任意整数，并求出他们的和。 
// 假如该和结果为 1，那么原数组就是一个「好数组」，则返回 True；否则请返回 False。 
//
// 示例 1： 
// 输入：nums = [12,5,7,23]
//输出：true
//解释：挑选数字 5 和 7。5*3 + 7*(-2) = 1
//
// 示例 2： 
// 输入：nums = [29,6,10]
//输出：true
//解释：挑选数字 29, 6 和 10。29*1 + 6*(-3) + 10*(-1) = 1
//
// 示例 3： 
// 输入：nums = [3,6]
//输出：false
//
// 提示： 
// 1 <= nums.length <= 10^5 
// 1 <= nums[i] <= 10^9 

public class CheckIfItIsAGoodArray{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CheckIfItIsAGoodArray().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.isGoodArray2(new int[]{12,5,7,23}));
        System.out.println("预期结果：true , 运行结果：" + solution.isGoodArray2(new int[]{29,6,10}));
        System.out.println("预期结果：false , 运行结果：" + solution.isGoodArray2(new int[]{3, 6}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //数论：裴蜀定理【直接使用 BigInteger 效率较差，最好用下方的手写gcd】
        // 复杂度分析
        // 时间复杂度： O(n+logm)，其中  n 为数组  nums 的长度， m 为数组  nums 中的最大数，其中求单次最大公约数的时间复杂度为  O(logm)，
        //  由于在每次求两个数的最大公约数时其中一个数保持单调不增，所以求总的公约数的时间复杂度为 O(logm)。
        // 空间复杂度： O(1)。仅使用常量空间。
        
        public boolean isGoodArray(int[] nums) {
            BigInteger divsor = BigInteger.valueOf(nums[0]);
            for(int num : nums){
                divsor = divsor.gcd(BigInteger.valueOf(num));
                if(BigInteger.ONE.equals(divsor)){
                    break;
                }
            }
            return BigInteger.ONE.equals(divsor);
        }
        
        public boolean isGoodArray2(int[] nums) {
            int divsor = nums[0];
            for(int num : nums){
                divsor = gcd(divsor, num);
                if(divsor == 1){
                    break;
                }
            }
            return divsor == 1;
        }
        
        private int gcd(int a, int b){
            return b == 0 ? a : gcd(b, a % b);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}