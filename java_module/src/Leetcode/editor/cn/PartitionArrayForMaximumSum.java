package Leetcode.editor.cn;

/**
 * 2023-04-19 10:09:28
 * [1043] - 分隔数组以得到最大和
 * PartitionArrayForMaximumSum.md
 */
 
//给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
// 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。 
//
// 示例 1： 
//输入：arr = [1,15,7,9,2,5,10], k = 3
//输出：84
//解释：数组变为 [15,15,15,9,10,10,10] 
//
// 示例 2： 
//输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
//输出：83
//
// 示例 3： 
//输入：arr = [1], k = 1
//输出：1
//
// 提示： 
// 1 <= arr.length <= 500 
// 0 <= arr[i] <= 10⁹ 
// 1 <= k <= arr.length 

public class PartitionArrayForMaximumSum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new PartitionArrayForMaximumSum().new Solution();
        System.out.println("预期结果：84 , 运行结果：" + solution.maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10}, 3));
        System.out.println("预期结果：83 , 运行结果：" + solution.maxSumAfterPartitioning(new int[]{1,4,1,5,7,3,6,1,9,9,3}, 4));
        System.out.println("预期结果：1 , 运行结果：" + solution.maxSumAfterPartitioning(new int[]{1}, 1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSumAfterPartitioning(int[] arr, int k) {
            int n = arr.length, dp[] = new int[n + 1];
            for(int i = 0; i <= n; i++){
                int max = -1;
                for(int j = i - 1; j >= Math.max(i - k, 0); j--){
                    max = Math.max(max, arr[j]);
                    dp[i] = Math.max(dp[i], dp[j] + max * (i - j));
                }
            }
            return dp[n];            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}