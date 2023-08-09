package Leetcode.editor.cn;

import java.util.PriorityQueue;

/**
 * 2023-07-25 09:18:12
 * [2208] - 将数组和减半的最少操作次数
 * MinimumOperationsToHalveArraySum.md
 */
 
//给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。（注意，在后续操作中你可以对减半过的数继续执行操作） 
//
// 请你返回将 nums 数组和 至少 减少一半的 最少 操作数。 
//
// 示例 1： 
// 输入：nums = [5,19,8,1]
//输出：3
//解释：初始 nums 的和为 5 + 19 + 8 + 1 = 33 。
//以下是将数组和减少至少一半的一种方法：
//选择数字 19 并减小为 9.5 。
//选择数字 9.5 并减小为 4.75 。
//选择数字 8 并减小为 4 。
//最终数组为 [5, 4.75, 4, 1] ，和为 5 + 4.75 + 4 + 1 = 14.75 。
//nums 的和减小了 33 - 14.75 = 18.25 ，减小的部分超过了初始数组和的一半，18.25 >= 33/2 = 16.5 。
//我们需要 3 个操作实现题目要求，所以返回 3 。
//可以证明，无法通过少于 3 个操作使数组和减少至少一半。
//
// 示例 2： 
// 输入：nums = [3,8,20]
//输出：3
//解释：初始 nums 的和为 3 + 8 + 20 = 31 。
//以下是将数组和减少至少一半的一种方法：
//选择数字 20 并减小为 10 。
//选择数字 10 并减小为 5 。
//选择数字 3 并减小为 1.5 。
//最终数组为 [1.5, 8, 5] ，和为 1.5 + 8 + 5 = 14.5 。
//nums 的和减小了 31 - 14.5 = 16.5 ，减小的部分超过了初始数组和的一半， 16.5 >= 31/2 = 16.5 。
//我们需要 3 个操作实现题目要求，所以返回 3 。
//可以证明，无法通过少于 3 个操作使数组和减少至少一半。
//
// 提示： 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁷ 

public class MinimumOperationsToHalveArraySum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumOperationsToHalveArraySum().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.halveArray(new int[]{5,19,8,1}));
        System.out.println("预期结果：3 , 运行结果：" + solution.halveArray(new int[]{3,8,20}));
        System.out.println("预期结果：29 , 运行结果：" + solution.halveArray(new int[]{18,22,62,61,1,88,17,98,38,32,51,57,7,29,40,61,62,13,89,41,73,85,88,60,59,76,71,76,76,41,29,43,19,9,79}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int halveArray(int[] nums) {
            double sum = 0.0;
            PriorityQueue<Double> queue = new PriorityQueue<>((a, b) -> Double.compare(b, a));
            for(int num : nums){
                queue.offer(0.0 + num);
                sum += num / 2.0;
            }
            int ans = 0;
            while(sum > 0){
                double temp = queue.poll() / 2;
                sum -= temp;
                queue.offer(temp);
                ans++;
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}