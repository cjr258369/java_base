package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-07-24 09:40:02
 * [42] - 接雨水
 * TrappingRainWater.md
 */
 
//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 示例 1： 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
//
// 示例 2： 
//输入：height = [4,2,0,3,2,5]
//输出：9
//
// 提示： 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 

public class TrappingRainWater{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new TrappingRainWater().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println("预期结果：9 , 运行结果：" + solution.trap(new int[]{4,2,0,3,2,5}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //接雨水是很经典的一个题，建议直接看精选的题解：
        //  https://leetcode.cn/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
        public int trap(int[] height) {
            int ans = 0, n = height.length, maxLeft[] = new int[n], maxRight[] = new int[n];
            for(int i = 1, j = n - 2; i < n; i++, j--){
                maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
                maxRight[j] = Math.max(maxRight[j + 1], height[j + 1]);
            }
            for(int i = 1; i < n - 1; i++){
                ans += Math.max(Math.min(maxLeft[i], maxRight[i]) - height[i], 0);                
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}