package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-06-06 10:13:17
 * [2352] - 相等行列对
 * EqualRowAndColumnPairs.md
 */
 
//给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。 
// 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。 
//
// 示例 1： 
//输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
//输出：1
//解释：存在一对相等行列对：
//- (第 2 行，第 1 列)：[2,7,7]
//
// 示例 2： 
//输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
//输出：3
//解释：存在三对相等行列对：
//- (第 0 行，第 0 列)：[3,1,2,2]
//- (第 2 行, 第 2 列)：[2,4,2,2]
//- (第 3 行, 第 2 列)：[2,4,2,2]
//
// 提示： 
// n == grid.length == grid[i].length 
// 1 <= n <= 200 
// 1 <= grid[i][j] <= 10⁵ 

public class EqualRowAndColumnPairs{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new EqualRowAndColumnPairs().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.equalPairs(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}}));
        System.out.println("预期结果：3 , 运行结果：" + solution.equalPairs(new int[][]{{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n^3)，需要进行双层循环，每次循环最多需要遍历 n 个数字。
        // 空间复杂度：O(n)，每次存储一行。
        public int equalPairs(int[][] grid) {
            int n = grid.length, ans = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    int[] rol = new int[n];
                    for(int k = 0; k < n; k++){
                        rol[k] = grid[k][j];
                    }
                    ans += Arrays.equals(grid[i], rol) ? 1 : 0;
                }
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}