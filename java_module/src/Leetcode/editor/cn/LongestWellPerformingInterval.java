package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2023-02-14 08:44:31
 * [1124] - 表现良好的最长时间段
 * LongestWellPerformingInterval.md
 */
 
//给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
// 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。请你返回「表现良好时间段」的最大长度。 
//
// 示例 1： 
//输入：hours = [9,9,6,0,6,6,9]
//输出：3
//解释：最长的表现良好时间段是 [9,9,6]。 
//
// 示例 2： 
//输入：hours = [6,6,6]
//输出：0
//
// 提示： 
// 1 <= hours.length <= 10⁴ 
// 0 <= hours[i] <= 16 

public class LongestWellPerformingInterval{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LongestWellPerformingInterval().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.longestWPI(new int[]{9,9,6,0,6,6,9}));
        System.out.println("预期结果：0 , 运行结果：" + solution.longestWPI(new int[]{6, 6, 6}));
        System.out.println("预期结果：1 , 运行结果：" + solution.longestWPI(new int[]{6, 6, 9}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 为 hours 的长度。每个元素最多入栈和出栈一次，因此时间复杂度为 O(n)。
        // 空间复杂度：O(n)，其中 n 为 hours 的长度。
        
        public int longestWPI(int[] hours) {
            //前缀和数组
            int preSum[] = new int[hours.length + 1];
            // 因为最终需要的答案是最长距离,需要下标来计算,所以这里存储下标
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(0);
            for(int i = 1; i <= hours.length; i++){
                preSum[i] = preSum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
                //维护单调递减栈
                if (preSum[stack.peek()] > preSum[i]) {
                    stack.push(i);
                }
            }
            
            //从后往前遍历r，并更新最大区间
            int ans = 0;
            for(int r = hours.length; r >= 1; r--){
                while(!stack.isEmpty() && preSum[stack.peek()] < preSum[r]){
                    ans = Math.max(ans, r - stack.pop());
                }
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}