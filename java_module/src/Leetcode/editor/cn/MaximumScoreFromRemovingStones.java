package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2022-12-21 09:33:41
 * [1753] - 移除石子的最大得分
 * MaximumScoreFromRemovingStones.md
 */
public class MaximumScoreFromRemovingStones{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumScoreFromRemovingStones().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.maximumScore(2, 4, 6));
        System.out.println("预期结果：7 , 运行结果：" + solution.maximumScore(4, 4, 6));
        System.out.println("预期结果：8 , 运行结果：" + solution.maximumScore(1, 8, 8));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximumScore(int a, int b, int c) {
            int sum = a + b + c;
            int maxVal = Math.max(a, Math.max(b,c));
            return Math.min(sum - maxVal, sum / 2);            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}