package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-06-27 15:04:10
 * [1186] - 删除一次得到子数组最大和
 * MaximumSubarraySumWithOneDeletion.md
 */
 
//给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以
//决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。 
//
// 注意，删除一个元素后，子数组 不能为空。 
//
// 示例 1： 
//输入：arr = [1,-2,0,3]
//输出：4
//解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。 
//
// 示例 2： 
//输入：arr = [1,-2,-2,3]
//输出：3
//解释：我们直接选出 [3]，这就是最大和。
//
// 示例 3： 
//输入：arr = [-1,-1,-1,-1]
//输出：-1
//解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。 我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
//
// 提示： 
// 1 <= arr.length <= 10⁵ 
// -10⁴ <= arr[i] <= 10⁴ 

public class MaximumSubarraySumWithOneDeletion{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumSubarraySumWithOneDeletion().new Solution();
        System.out.println("预期结果：4 , 运行结果：" + solution.maximumSum(new int[]{1,-2,0,3}));
        System.out.println("预期结果：3 , 运行结果：" + solution.maximumSum(new int[]{1,-2,-2,3}));
        System.out.println("预期结果：-1 , 运行结果：" + solution.maximumSum(new int[]{-1,-1,-1,-1}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //详细分析看这个：https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-hzz6/
        
        int arr[], memo[][];
        public int maximumSum(int[] arr) {
            this.arr = arr;
            memo = new int[arr.length][2];
            for(int i = 0; i < arr.length; i++){
                Arrays.fill(memo[i], Integer.MIN_VALUE);
            }
            int ans = Integer.MIN_VALUE;
            for(int i = 0; i < arr.length; i++){
                ans = Math.max(ans, Math.max(dfs(i, 0), dfs(i, 1)));
            }
            return ans;
        }
        
        private int dfs(int i, int j){
            if(i < 0){
                // 除 2 防止负数相加溢出
                return Integer.MIN_VALUE / 2;
            }
            if(memo[i][j] != Integer.MIN_VALUE){
                // 之前计算过
                return memo[i][j];
            }
            if(j == 0){
                return memo[i][j] = Math.max(dfs(i - 1, 0), 0) + arr[i];
            }
            return memo[i][j] = Math.max(dfs(i - 1, 1) + arr[i], dfs(i - 1, 0));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}