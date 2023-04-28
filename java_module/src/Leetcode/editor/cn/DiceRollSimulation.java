package Leetcode.editor.cn;

import java.util.Arrays;

import static Leetcode.util.constVal.MOD;

/**
 * 2023-02-10 09:19:04
 * [1223] - 掷骰子模拟
 * DiceRollSimulation.md
 */
 
//有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。 
// 现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。 
// 假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。 
//
// 示例 1： 
// 输入：n = 2, rollMax = [1,1,2,2,2,3]
//输出：34
//解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所
//以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
//
// 示例 2： 
// 输入：n = 2, rollMax = [1,1,1,1,1,1]
//输出：30
//
// 示例 3： 
// 输入：n = 3, rollMax = [1,1,1,2,2,3]
//输出：181
//
// 提示： 
// 1 <= n <= 5000 
// rollMax.length == 6 
// 1 <= rollMax[i] <= 15 

public class DiceRollSimulation{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution2 solution = new DiceRollSimulation().new Solution2();
        System.out.println("预期结果：34 , 运行结果：" + solution.dieSimulator(2, new int[]{1,1,2,2,2,3}));
        System.out.println("预期结果：30 , 运行结果：" + solution.dieSimulator(2, new int[]{1,1,1,1,1,1}));
        System.out.println("预期结果：181 , 运行结果：" + solution.dieSimulator(3, new int[]{1,1,1,2,2,3}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    //普通的回溯写法【直接超时】
    class Solution1 {
        public int dieSimulator(int n, int[] rollMax) {
            this.rollMax = rollMax;
            long ans = 0;
            for(int i = 0; i < rollMax.length; i++){
                ans += dfs(n - 1, i, rollMax[i] - 1);
            }
            return (int)(ans % MOD);
        }
        int[] rollMax;        
        /**
         * 
         * @param n 当前第几次投骰子
         * @param last 最后投出来的点数
         * @param left 当前点数还能不能连续的数目
         * @return 返回所有可以的结果数量
         */
        public int dfs(int n, int last, int left){
            if(n == 0) return 1;
            long res = 0;
            for(int i = 0; i < rollMax.length; i++){
                if(i != last){
                    //与上一轮投出的点数不一样
                    res += dfs(n - 1, i, rollMax[i] - 1);
                }else{
                    //如果与上一轮相同，则判断是否还可以连续，可以则继续dfs，否则跳过
                    if(left > 0){
                        res += dfs(n - 1, i, left-  1);
                    }
                }
            }
            return (int)(res % MOD);            
        }
    }

    //改为记忆化的写法
    //因为 「先掷 1 后掷 3」和「先掷 2 后掷 3」，都会递归到 dfs(n−2,3,rollMax[3]−1)。
    //一叶知秋，整个回溯过程是有大量重复递归调用的。由于递归函数没有副作用，无论多少次调用 dfs(i,last,left) 算出来的结果都是一样的，因此可以用记忆化搜索来优化：
    // 如果一个状态（递归入参）是第一次遇到，那么可以在返回前，把状态及其结果记到一个 cache 数组（或者哈希表）中；
    // 如果一个状态不是第一次遇到，那么直接返回 cache 中保存的结果。
    //复杂度分析
    // 时间复杂度：O(nmS)，其中 m 为 rollMax 的长度，即 6，S=∑rollMax，这不会超过 6×15=90。动态规划的时间复杂度 == 状态个数 × 单个状态的转移个数。本题中状态个数等于 O(nS)，而单个状态的转移个数为 O(m)，因此时间复杂度为 O(nmS)。
    // 空间复杂度：O(nS)。
    
    class Solution2 {
        int rollMax[], cache[][][];
        
        public int dieSimulator(int n, int[] rollMax) {
            this.rollMax = rollMax;
            this.cache = new int[n][rollMax.length][15];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < rollMax.length; j++){
                    // -1 表示没有访问过
                    Arrays.fill(cache[i][j], -1);
                }
            }
            long ans = 0;
            for(int i = 0; i < rollMax.length; i++){
                ans += dfs(n - 1, i, rollMax[i] - 1);
            }
            return (int)(ans % MOD);    
        }
        /**
         * 
         * @param n 当前第几次投骰子
         * @param last 最后投出来的点数
         * @param left 当前点数还能不能连续的数目
         * @return 返回所有可以的结果数量
         */
        public int dfs(int n, int last, int left){
            if(n == 0) return 1;
            if(cache[n][last][left] >= 0) return cache[n][last][left];
            long res = 0;
            for(int i = 0; i < rollMax.length; i++){
                if(i != last){
                    //与上一轮投出的点数不一样
                    res += dfs(n - 1, i, rollMax[i] - 1);
                }else{
                    //如果与上一轮相同，则判断是否还可以连续，可以则继续dfs，否则跳过
                    if(left > 0){
                        res += dfs(n - 1, i, left-  1);
                    }
                }
            }
            return cache[n][last][left] = (int)(res % MOD);            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}