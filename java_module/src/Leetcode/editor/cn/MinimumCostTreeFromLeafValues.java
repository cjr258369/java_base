package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2023-05-31 11:24:14
 * [1130] - 叶值的最小代价生成树
 * MinimumCostTreeFromLeafValues.md
 */
 
//给你一个正整数数组 arr，考虑所有满足以下条件的二叉树： 
// 每个节点都有 0 个或是 2 个子节点。 
// 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。 
// 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。 
//
// 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。 
// 如果一个节点有 0 个子节点，那么该节点为叶节点。 
//
// 示例 1： 
//输入：arr = [6,2,4]
//输出：32
//解释：有两种可能的树，第一种的非叶节点的总和为 36 ，第二种非叶节点的总和为 32 。 
//
// 示例 2： 
//输入：arr = [4,11]
//输出：44
//
// 提示： 
// 2 <= arr.length <= 40 
// 1 <= arr[i] <= 15 
// 答案保证是一个 32 位带符号整数，即小于 2³¹ 。 

public class MinimumCostTreeFromLeafValues{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumCostTreeFromLeafValues().new Solution();
        System.out.println("预期结果：32 , 运行结果：" + solution.mctFromLeafValues2(new int[]{6, 2, 4}));
        System.out.println("预期结果：44 , 运行结果：" + solution.mctFromLeafValues2(new int[]{4, 11}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //动态规划复杂度分析
        // 时间复杂度：O(n^3)，其中 n 是数组 arr 的长度。三重循环需要 O(n^3) 的空间。
        // 空间复杂度：O(n^2)。保存 dp 和 mval 需要 O(n^2) 的空间。
        public int mctFromLeafValues(int[] arr) {
            int n = arr.length, dp[][] = new int[n][n], maxVal[][] = new int[n][n];
            for(int i = 0; i < n; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            
            for(int j = 0; j < n; j++){
                maxVal[j][j] = arr[j];
                dp[j][j] = 0;
                for(int i = j - 1; i >= 0; i--){
                    maxVal[i][j] = Math.max(maxVal[i + 1][j], arr[i]);
                    
                    for(int k = i; k < j; k++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + maxVal[i][k] * maxVal[k + 1][j]);
                    }
                }
            }
            return dp[0][n - 1];    
        }
        
        //单调栈：问题可以转化为：给定一个数组 arr，不断地合并相邻的数，合并代价为两个数的乘积，合并之后的数为两个数的最大值，直到数组只剩一个数，求最小合并代价和。
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 为数组 arr 的长度。每次循环都有入栈或出栈操作，总次数不超过 2×n，因此时间复杂度为 O(n)。
        // 空间复杂度：O(n)。栈 stk 需要 O(n) 的空间。
        public int mctFromLeafValues2(int[] arr) {
            int ans = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            for(int x : arr){
                while(!queue.isEmpty() && queue.peek()<= x){
                    int y = queue.pop();
                    if(queue.isEmpty() || queue.peek() > x){
                        ans += y * x;
                    }else{
                        ans += queue.peek() * y;
                    }
                }
                queue.push(x);
            }
            while(queue.size() >= 2){
                int x = queue.pop();
                ans += queue.peek() * x;
            }
            
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}