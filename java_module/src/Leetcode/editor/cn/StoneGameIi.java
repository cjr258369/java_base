package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-02-22 09:25:00
 * [1140] - 石子游戏 II
 * StoneGameIi.md
 */
 
//爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
// 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。  游戏一直持续到所有石子都被拿走。 
// 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。 
//
// 示例 1： 
//输入：piles = [2,7,9,4,4]
//输出：10
//解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那
//么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
//
// 示例 2: 
//输入：piles = [1,2,3,4,5,100]
//输出：104
//
// 提示： 
// 1 <= piles.length <= 100 
// 1 <= piles[i] <= 10⁴ 

public class StoneGameIi{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new StoneGameIi().new Solution();
        System.out.println("预期结果：10 , 运行结果：" + solution.stoneGameII2(new int[]{2,7,9,4,4}));
        System.out.println("预期结果：104 , 运行结果：" + solution.stoneGameII2(new int[]{1,2,3,4,5,100}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] suffixSum;
        int[][] cache;
        public int stoneGameII(int[] piles) {
            suffixSum = piles;
            //计算后缀和
            for(int i = piles.length - 2; i >= 0; i--){
                suffixSum[i] += suffixSum[i + 1];
            }
            return dfs1(0, 1);    
        }
        //暴力dfs，会超时
        private int dfs1(int i, int m){
            if(i + 2 * m >= suffixSum.length) return suffixSum[i];
            int min = Integer.MAX_VALUE;
            //对手的最小值
            for(int x = 1; x <= 2 * m; x++){
                min = Math.min(min, dfs1(i + x, Math.max(m, x)));
            }
            return suffixSum[i] - min;
        }
        
        public int stoneGameII2(int[] piles) {
            cache = new int[piles.length][(piles.length + 1) / 4 + 1];
            suffixSum = piles;
            Arrays.fill(cache[0], -1);
            //计算后缀和
            for(int i = piles.length - 2; i >= 0; i--){
                suffixSum[i] += suffixSum[i + 1];
                Arrays.fill(cache[i + 1], -1);
            }
            return dfs2(0, 1);    
        }

        //使用记忆化优化暴力的dfs
        private int dfs2(int i, int m){
            if(i + 2 * m >= suffixSum.length) return suffixSum[i];
            if(cache[i][m] != -1) return cache[i][m];
            
            
            int min = Integer.MAX_VALUE;
            //对手的最小值
            for(int x = 1; x <= 2 * m; x++){
                min = Math.min(min, dfs2(i + x, Math.max(m, x)));
            }
            return cache[i][m] = suffixSum[i] - min;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}