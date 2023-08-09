package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-05-17 11:44:14
 * [1335] - 工作计划的最低难度
 * MinimumDifficultyOfAJobSchedule.md
 */
 
//你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。 
// 给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。
// 返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。 
//
// 示例 1： 
// 输入：jobDifficulty = [6,5,4,3,2,1], d = 2
//输出：7
//解释：第一天，您可以完成前 5 项工作，总难度 = 6.
//第二天，您可以完成最后一项工作，总难度 = 1.
//计划表的难度 = 6 + 1 = 7 
//
// 示例 2： 
// 输入：jobDifficulty = [9,9,9], d = 4
//输出：-1
//解释：就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
//
// 示例 3： 
// 输入：jobDifficulty = [1,1,1], d = 3
//输出：3
//解释：工作计划为每天一项工作，总难度为 3 。
//
// 示例 4： 
// 输入：jobDifficulty = [7,1,7,1,7,1], d = 3
//输出：15
//
// 示例 5： 
// 输入：jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
//输出：843
//
// 提示： 
// 1 <= jobDifficulty.length <= 300 
// 0 <= jobDifficulty[i] <= 1000 
// 1 <= d <= 10 

public class MinimumDifficultyOfAJobSchedule{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumDifficultyOfAJobSchedule().new Solution();
        System.out.println("预期结果：7 , 运行结果：" + solution.minDifficulty(new int[]{6,5,4,3,2,1}, 2));
        System.out.println("预期结果：-1 , 运行结果：" + solution.minDifficulty(new int[]{9,9,9}, 4));
        System.out.println("预期结果：3 , 运行结果：" + solution.minDifficulty(new int[]{1,1,1}, 3));
        System.out.println("预期结果：15 , 运行结果：" + solution.minDifficulty(new int[]{7,1,7,1,7,1}, 3));
        System.out.println("预期结果：843 , 运行结果：" + solution.minDifficulty(new int[]{11,111,22,222,33,333,44,444}, 6));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(dn^2)，其中 n 为 jobDifficulty 的长度。动态规划的时间复杂度 = 状态个数 × 单个状态的计算时间。本题中状态个数等于 O(nd)，单个状态的计算时间为  O(n)，因此时间复杂度为 O(dn^2)。
        // 空间复杂度：O(nd)。有 O(nd) 个状态。
        
        int jobDifficulty[], memo[][];
        public int minDifficulty(int[] jobDifficulty, int d) {
            int n = jobDifficulty.length;
            if(n < d){
                return -1;
            }
            this.jobDifficulty = jobDifficulty;
            memo = new int[d][n];
            // -1 表示还没有计算过
            for(int i = 0; i < d; i++){
                Arrays.fill(memo[i], -1);
            }
            
            return dfs(d - 1, n - 1);
        }
        
        private int dfs(int i, int j){
            if(memo[i][j] != -1){
                return memo[i][j];
            }
            // 只有一天，必须完成所有工作
            int max = Integer.MIN_VALUE;
            if(i == 0){
                for(int k = 0; k <= j; k++){
                    max = Math.max(jobDifficulty[k], max);
                }
                return memo[i][j] = max;                
            }
            int res = Integer.MAX_VALUE;
            for(int k = j; k >= i; k--){
                // 从 jobDifficulty[k] 到 jobDifficulty[j] 的最大值
                max = Math.max(max, jobDifficulty[k]);
                res = Math.min(res, dfs(i - 1, k - 1) + max);
            }
            return memo[i][j] = res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}