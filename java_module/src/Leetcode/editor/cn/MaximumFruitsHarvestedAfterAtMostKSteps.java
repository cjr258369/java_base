package Leetcode.editor.cn;

/**
 * 2023-05-04 10:40:26
 * [2106] - 摘水果
 * MaximumFruitsHarvestedAfterAtMostKSteps.md
 */
 
//在一个无限的 x 坐标轴上，有许多水果分布在其中某些位置。给你一个二维整数数组 fruits ，其中 fruits[i] = [positioni,amounti] 表示共有 amounti 个水果放置在 positioni 上。
// fruits 已经按 position[i] 升序排列 ，每个 position[i] 互不相同 。
// 另给你两个整数 startPos 和 k 。最初，你位于 startPos 。从任何位置，你可以选择 向左或者向右 走。在 x 轴上每移动 一个单位 ，就记作 一步 。你总共可以走 最多 k 步。你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。 
//
// 返回你可以摘到水果的 最大总数 。 
//
// 示例 1： 
// 输入：fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
//输出：9
//解释：
//最佳路线为：
//- 向右移动到位置 6 ，摘到 3 个水果
//- 向右移动到位置 8 ，摘到 6 个水果
//移动 3 步，共摘到 3 + 6 = 9 个水果
//
// 示例 2： 
// 输入：fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
//输出：14
//解释：
//可以移动最多 k = 4 步，所以无法到达位置 0 和位置 10 。
//最佳路线为：
//- 在初始位置 5 ，摘到 7 个水果
//- 向左移动到位置 4 ，摘到 1 个水果
//- 向右移动到位置 6 ，摘到 2 个水果
//- 向右移动到位置 7 ，摘到 4 个水果
//移动 1 + 3 = 4 步，共摘到 7 + 1 + 2 + 4 = 14 个水果
//
// 示例 3： 
// 输入：fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
//输出：0
//解释：
//最多可以移动 k = 2 步，无法到达任一有水果的地方
//
// 提示： 
// 1 <= fruits.length <= 10⁵ 
// fruits[i].length == 2 
// 0 <= startPos, positioni <= 2 * 10⁵ 
// 对于任意 i > 0 ，positioni-1 < positioni 均成立（下标从 0 开始计数） 
// 1 <= amounti <= 10⁴ 
// 0 <= k <= 2 * 10⁵ 

public class MaximumFruitsHarvestedAfterAtMostKSteps{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumFruitsHarvestedAfterAtMostKSteps().new Solution();
        System.out.println("预期结果：9 , 运行结果：" + solution.maxTotalFruits(new int[][]{{2,8},{6,3},{8,6}}, 5, 4));
        System.out.println("预期结果：14 , 运行结果：" + solution.maxTotalFruits(new int[][]{{0,9},{4,1},{5,7},{6,2},{7,4},{10,9}}, 5, 4));
        System.out.println("预期结果：0 , 运行结果：" + solution.maxTotalFruits(new int[][]{{0,3},{6,4},{8,5}}, 3, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //滑动窗口【step 函数用于统计走到 [left, right] 区间需要走多少步，如果没大于 k 都可以继续向右滑， sum 用于统计当前区间内的水果总数，left 和 right 为滑窗的同向双指针】
        // 复杂度分析
        // 时间复杂度：O(n)，其中 n 表示数组的长度。每次固定窗口的右侧，然后尝试移动左侧窗口，右侧端点最多移动 n 次，左侧端点最多移动 n 次，因此时间复杂度为 O(2n)=O(n)。
        // 空间复杂度：O(1)。
        public int maxTotalFruits(int[][] fruits, int startPos, int k) {
            int max = 0;
            for(int left = 0, right = 0, sum = 0; right < fruits.length; right++){
                sum += fruits[right][1];
                while(left <= right && step(fruits, startPos, left, right) > k){
                    sum -= fruits[left++][1];
                }
                max = Math.max(max, sum);
            }
            return max;
        }
        
        private int step(int[][] fruits, int startPos, int left, int right){
            //int l = fruits[left][0], r = fruits[right][0];
            //if (r <= startPos) return startPos - l;
            //else if (l >= startPos) return r - startPos;
            //else return r - l + Math.min(startPos - l, r - startPos);
            return fruits[right][0] - fruits[left][0] + Math.min(Math.abs(startPos - fruits[right][0]), Math.abs(startPos - fruits[left][0]));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}