package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-04-07 11:00:53
 * [1040] - 移动石子直到连续 II
 * MovingStonesUntilConsecutiveIi.md
 */
 
//在一个长度 无限 的数轴上，第 i 颗石子的位置为 stones[i]。如果一颗石子的位置最小/最大，那么该石子被称作 端点石子 。
// 每个回合，你可以将一颗端点石子拿起并移动到一个未占用的位置，使得该石子不再是一颗端点石子。
// 值得注意的是，如果石子像 stones = [1,2,5] 这样，你将 无法 移动位于位置 5 的端点石子，因为无论将它移动到任何位置（例如 0 或 3），该石子都仍然会是端点石子。 
//
// 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
// 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves,maximum_moves] 。 
//
// 示例 1： 
//输入：[7,4,9]
//输出：[1,2]
//解释：
//我们可以移动一次，4 -> 8，游戏结束。
//或者，我们可以移动两次 9 -> 5，4 -> 6，游戏结束。
//
// 示例 2： 
//输入：[6,5,4,3,10]
//输出：[2,3]
//解释：
//我们可以移动 3 -> 8，接着是 10 -> 7，游戏结束。
//或者，我们可以移动 3 -> 7, 4 -> 8, 5 -> 9，游戏结束。
//注意，我们无法进行 10 -> 2 这样的移动来结束游戏，因为这是不合要求的移动。
//
// 示例 3： 
//输入：[100,101,104,102,103]
//输出：[0,0] 
//
// 提示： 
// 3 <= stones.length <= 10^4 
// 1 <= stones[i] <= 10^9 
// stones[i] 的值各不相同。 

public class MovingStonesUntilConsecutiveIi{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MovingStonesUntilConsecutiveIi().new Solution();
        System.out.println("预期结果：[1, 2] , 运行结果：" + Arrays.toString(solution.numMovesStonesII(new int[]{7, 4, 9})));
        System.out.println("预期结果：[2, 3] , 运行结果：" + Arrays.toString(solution.numMovesStonesII(new int[]{6, 5, 4, 3, 10})));
        System.out.println("预期结果：[0, 0] , 运行结果：" + Arrays.toString(solution.numMovesStonesII(new int[]{100,101,104,102,103})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //同向双指针滑动窗口
        //复杂度分析
        // 时间复杂度：O(nlogn)，其中 n 为数组 stones 的长度。主要为排序的时间复杂度。
        // 空间复杂度：O(logn)，其中 n 为数组 stones 的长度。主要为排序的空间开销。
        public int[] numMovesStonesII(int[] stones) {
            Arrays.sort(stones);
            int n = stones.length, ans[] = new int[2];
            if(stones[n - 1] - stones[0] + 1 == n){
                return ans;
            }
            //ans[1] = Math.max(stones[n - 2] - stones[0] + 1, stones[n - 1] - stones[1] + 1) - (n - 1);
            int e1 = stones[n - 2] - stones[0] - n + 2, e2 = stones[n - 1] - stones[1] - n + 2;
            ans[1] = Math.max(e1, e2);
            if(e1 == 0 || e2 == 0){
                ans[0] = Math.min(2, ans[1]);
                return ans;
            }
            for(int left = 0, right = 0; right < n; right++){
                while(stones[right] - stones[left] + 1 > n){    // 窗口大小大于 n
                    left++;
                }
                ans[0] = Math.max(ans[0], right - left + 1);    // 维护窗口内的最大石子数
            }
            ans[0] = n - ans[0];
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}