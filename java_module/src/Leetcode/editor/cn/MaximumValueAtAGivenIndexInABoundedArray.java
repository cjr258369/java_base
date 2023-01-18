package Leetcode.editor.cn;

/**
 * 2023-01-04 09:11:35
 * [1802] - 有界数组中指定下标处的最大值
 * MaximumValueAtAGivenIndexInABoundedArray.md
 */
 
//给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）： 
// 
// 1. nums.length == n 
// 2. nums[i] 是 正整数 ，其中 0 <= i < n 
// 3. abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1 
// 4. nums 中所有元素之和不超过 maxSum 
// 5. nums[index] 的值被 最大化 
//
// 返回你所构造的数组中的 nums[index] 。 
// 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。 
//
// 示例 1： 
// 输入：n = 4, index = 2,  maxSum = 6
//输出：2
//解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
//
// 示例 2： 
// 输入：n = 6, index = 1,  maxSum = 10
//输出：3
//
// 提示： 
// 1 <= n <= maxSum <= 10⁹ 
// 0 <= index < n

public class MaximumValueAtAGivenIndexInABoundedArray{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumValueAtAGivenIndexInABoundedArray().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.maxValue(4, 2, 6));
        System.out.println("预期结果：3 , 运行结果：" + solution.maxValue(6, 1, 10));
        System.out.println("预期结果：399435402 , 运行结果：" + solution.maxValue(2, 0, 798870804));
        System.out.println("预期结果：24180 , 运行结果：" + solution.maxValue(7586551, 5667346, 592213541));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //方案一：贪心 + 二分查找
        //思路：一旦 nums[index] 确定后，这个数组的和 numsSum 也就确定了。并且 nums[index] 越大，数组和 numsSum 也越大。据此，使用二分搜索来找出最大的使得 numsSum ≤ maxSum 成立的 nums[index]。
        //代码实现上，二分搜索的左边界为 1，右边界为 maxSum。二分判断 nums[index] 对应的 numsSum 是否满足条件。numsSum 由三部分组成，nums[index]，nums[index] 左边的部分之和，和 nums[index] 右边的部分之和。函数 getSum 用来计算单边的元素和，需要考虑边界元素是否早已下降到 1 的情况。
        //
        //复杂度分析
        //时间复杂度：O(lg(maxSum))。二分搜索上下界的差为 maxSum。
        //空间复杂度：O(1)，仅需要常数空间。
        public int maxValue(int n, int index, int maxSum) {
            int left = 1, right = maxSum;
            while(left < right){
                int mid = (left + right + 1)/2;
                if(mid + getSum(mid, index) + getSum(mid, n - 1 - index) <= maxSum){
                    left = mid;                    
                }else{
                    right = mid - 1;
                }
            }
            return left;    
        }
        
        private long getSum(int big, int length){
            if(length + 1 < big){
                int small = big - length;
                return (small + big - 1L) * length / 2;
            }else{
                int ones = length - (big - 1);
                return big * (big - 1L) / 2 + ones;
            }
        }

        //方案二：数学推导
        public int maxValue2(int n, int index, int maxSum) {
            int left = 1, right = maxSum;
            while(left < right){
                int mid = (left + right + 1)/2;
                if(mid + getSum(mid, index) + getSum(mid, n - 1 - index) <= maxSum){
                    left = mid;
                }else{
                    right = mid - 1;
                }
            }
            return left;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}