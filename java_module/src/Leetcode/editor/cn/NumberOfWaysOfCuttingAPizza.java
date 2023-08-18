package Leetcode.editor.cn;

import DSA.排序.MatrixSum;
import Leetcode.util.constVal;

import java.util.Arrays;

/**
 * 2023-08-17 09:54:09
 * [1444] - 切披萨的方案数
 * NumberOfWaysOfCuttingAPizza.md
 */
 
//给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。你需要切披萨 k-1 次，得到 k 块披萨并送给别人。
// 切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。
// 请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。 
//
// 示例 1： 
// 输入：pizza = ["A..","AAA","..."], k = 3
//输出：3 
//解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
//
// 示例 2： 
// 输入：pizza = ["A..","AA.","..."], k = 3
//输出：1
//
// 示例 3： 
// 输入：pizza = ["A..","A..","..."], k = 1
//输出：1
//
// 提示： 
// 1 <= rows, cols <= 50 
// rows == pizza.length 
// cols == pizza[i].length 
// 1 <= k <= 10 
// pizza 只包含字符 'A' 和 '.' 。 

public class NumberOfWaysOfCuttingAPizza{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOfWaysOfCuttingAPizza().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.ways2(new String[]{"A..","AAA","..."}, 3));
        System.out.println("预期结果：1 , 运行结果：" + solution.ways2(new String[]{"A..","AA.","..."}, 3));
        System.out.println("预期结果：1 , 运行结果：" + solution.ways2(new String[]{"A..","A..","..."}, 1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //时空复杂度直接看这篇，以及后续的优化：https://leetcode.cn/problems/number-of-ways-of-cutting-a-pizza/solutions/2392051/ji-bai-100cong-di-gui-dao-di-tui-dao-you-dxz5/
        
        public int ways(String[] pizza, int k) {
            MatrixSum ms = new MatrixSum(pizza);
            return dfs1(k - 1, 0, 0, ms, pizza.length, pizza[0].length());
        }

        //dfs1 因为没用记忆化，做了很多重复的计算，因此这个递归代码是会超时的
        private int dfs1(int c, int i, int j, MatrixSum ms, int m, int n){
            // 递归边界：无法再切了
            if(c == 0){
                return ms.query(i, j, m, n) > 0 ? 1 : 0;
            }
            
            int res = 0;
            // 垂直切
            for(int j2 = j; j2 < n; j2++){
                // 有苹果
                if(ms.query(i, j, m, j2) > 0){
                    res = (res + dfs1(c - 1, i, j2, ms, m, n)) % constVal.MOD;
                }
            }
            // 水平切
            for(int i2 = i; i2 < m; i2++){
                if(ms.query(i, j, i2, n) > 0){
                    res = (res + dfs1(c - 1, i2, j, ms, m, n)) % constVal.MOD;
                }
            }
            return res;
        }
        
        //增加记忆化，减少重复的计算
        public int ways2(String[] pizza, int k) {
            int m = pizza.length, n = pizza[0].length(), memo[][][] = new int[k][m][n];
            MatrixSum ms = new MatrixSum(pizza);
            for(int i = 0; i < k ;i++){
                for(int j = 0; j < m; j++){
                    // -1 表示没有计算过
                    Arrays.fill(memo[i][j], -1);
                }
            }
            
            return dfs2(k - 1, 0, 0, ms, memo, m, n);
        }
        
        private int dfs2(int c, int i, int j, MatrixSum ms, int[][][] memo, int m, int n){
            if(c == 0){
                return ms.query(i, j, m, n) > 0 ? 1 : 0;
            }
            if(memo[c][i][j] != -1){
                // 之前计算过
                return memo[c][i][j];
            }
            int res = 0;
            // 垂直切
            for(int j2 = j; j2 < n; j2++){
                // 有苹果
                if(ms.query(i, j, m, j2) > 0){
                    res = (res + dfs2(c - 1, i, j2, ms, memo, m, n)) % constVal.MOD;
                }                
            }
            // 水平切
            for(int i2 = i; i2 < m; i2++){
                //有苹果
                if(ms.query(i, j, i2, n) > 0){
                    res = (res + dfs2(c - 1, i2, j, ms, memo, m, n)) % constVal.MOD;
                } 
            }
            // 记忆化
            return memo[c][i][j] = res;            
        }
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}