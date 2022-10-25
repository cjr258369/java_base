package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-10-24 11:21:56
 * [2448] - 使数组相等的最小开销
 * MinimumCostToMakeArrayEqual.md
 */
public class MinimumCostToMakeArrayEqual{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumCostToMakeArrayEqual().new Solution();
        System.out.println("运行结果：" + solution.minCost(new int[]{1,3,5,2}, new int[]{2,3,1,14}));
        System.out.println("运行结果：" + solution.minCost(new int[]{2,2,2,2,2}, new int[]{4,2,8,1,3}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for(int i = 0; i < n; i++) idx[i] = i;
        //排序
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);
        
        //先计算出全部变到最小数字时候的开销
        //totalCost = 计算所有数字，都变为排序后的 nums[0] 也即最小值的费用
        //costAll = 所有开销总花费，也即前缀和
        long totalCost = 0, costAll = 0, ans = 0;
        for(int i = 0; i < n; costAll += cost[i++]){
            ans = totalCost += 1L * (nums[i] - nums[idx[0]]) * cost[i];
        }
        
        //枚举变到每一个数字的开销
        //从 i 变到 i + 1 的有多少个数字产生了变化：d = nums[idx[i]] - nums[idx[i - 1]]
        //每个数字变化的量：costAll - cost[idx[i - 1]]
        //后面所有的数字变少了：d * (costAll - cost[idx[i - 1]])
        //前面所有的数字增加了：d * cost[idx[i - 1]]
        //总体变化：totalCost -= d * (costAll - 2 * cost[idx[i - 1]])
        for(int i = 1; i < n; i++){
            ans = Math.min(ans, totalCost -= (costAll -= 2L * cost[idx[i - 1]]) * (nums[idx[i]] - nums[idx[i - 1]]));
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}