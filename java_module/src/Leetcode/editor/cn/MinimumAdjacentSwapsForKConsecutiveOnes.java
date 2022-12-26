package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022-12-18 22:46:11
 * [1703] - 得到连续 K 个 1 的最少相邻交换次数
 * MinimumAdjacentSwapsForKConsecutiveOnes.md
 */
public class MinimumAdjacentSwapsForKConsecutiveOnes{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumAdjacentSwapsForKConsecutiveOnes().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.minMoves(new int[]{1,0,0,1,0,1}, 2));
        System.out.println("预期结果：5 , 运行结果：" + solution.minMoves(new int[]{1,0,0,0,0,0,1,1}, 3));
        System.out.println("预期结果：0 , 运行结果：" + solution.minMoves(new int[]{1,1,0,1}, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minMoves(int[] nums, int k) {
            List<Integer> g = new ArrayList<Integer>();
            List<Integer> preSum = new ArrayList<Integer>();
            preSum.add(0);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    g.add(i - g.size());
                    preSum.add(preSum.get(preSum.size() - 1) + g.get(g.size() - 1));
                }
            }
            int m = g.size(), res = Integer.MAX_VALUE;
            for (int i = 0; i <= m - k; i++) {
                int mid = i + k / 2;
                int r = g.get(mid);
                res = Math.min(res, (1 - k % 2) * r + (preSum.get(i + k) - preSum.get(mid + 1)) - (preSum.get(mid) - preSum.get(i)));
            }
            return res;
    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}