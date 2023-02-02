package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-01-30 17:27:47
 * [2551] - 将珠子放入背包中
 * PutMarblesInBags.md
 */
 
//你有 k 个背包。给你一个下标从 0 开始的整数数组 weights ，其中 weights[i] 是第 i 个珠子的重量。同时给你整数 k 。 
// 请你按照如下规则将所有的珠子放进 k 个背包。 
// 没有背包是空的。 
// 如果第 i 个珠子和第 j 个珠子在同一个背包里，那么下标在 i 到 j 之间的所有珠子都必须在这同一个背包中。 
// 如果一个背包有下标从 i 到 j 的所有珠子，那么这个背包的价格是 weights[i] + weights[j] 。 
//
// 一个珠子分配方案的 分数 是所有 k 个背包的价格之和。 
// 请你返回所有分配方案中，最大分数 与 最小分数 的 差值 为多少。 
//
// 示例 1： 
// 输入：weights = [1,3,5,1], k = 2
//输出：4
//解释：
//分配方案 [1],[3,5,1] 得到最小得分 (1+1) + (3+1) = 6 。
//分配方案 [1,3],[5,1] 得到最大得分 (1+3) + (5+1) = 10 。
//所以差值为 10 - 6 = 4 。
//
// 示例 2： 
// 输入：weights = [1, 3], k = 2
//输出：0
//解释：唯一的分配方案为 [1],[3] 。
//最大最小得分相等，所以返回 0 。
//
// 提示： 
// 1 <= k <= weights.length <= 10⁵ 
// 1 <= weights[i] <= 10⁹ 

public class PutMarblesInBags{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new PutMarblesInBags().new Solution();
        System.out.println("预期结果：4 , 运行结果：" + solution.putMarbles(new int[]{1,3,5,1}, 2));
        System.out.println("预期结果：0 , 运行结果：" + solution.putMarbles(new int[]{1,3}, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long putMarbles(int[] weights, int k) {
            int[] sum = new int[weights.length - 1];
            for(int i = 0; i < weights.length - 1; i++){
                sum[i] = weights[i] + weights[i + 1];                
            }
            Arrays.sort(sum);
            long ans = 0L;
            //累计 尾部k个 为最大值 减去 头部 k 个最小值
            for(int i = 0; i < k - 1; i++){
                ans -= sum[i];
                ans += sum[sum.length - 1 - i];
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}