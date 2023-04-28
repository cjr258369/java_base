package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-03-14 09:00:28
 * [1605] - 给定行和列的和求可行矩阵
 * FindValidMatrixGivenRowAndColumnSums.md
 */
 
//给你两个非负整数数组 rowSum 和 colSum ，其中 rowSum[i] 是二维矩阵中第 i 行元素的和， colSum[j] 是第 j 列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。 
// 请找到大小为 rowSum.length x colSum.length 的任意 非负整数 矩阵，且该矩阵满足 rowSum 和 colSum 的要求。 
// 请你返回任意一个满足题目要求的二维矩阵，题目保证存在 至少一个 可行矩阵。 
//
// 示例 1： 
//输入：rowSum = [3,8], colSum = [4,7]
//输出：[[3,0],
//      [1,7]]
//解释：
//第 0 行：3 + 0 = 3 == rowSum[0]
//第 1 行：1 + 7 = 8 == rowSum[1]
//第 0 列：3 + 1 = 4 == colSum[0]
//第 1 列：0 + 7 = 7 == colSum[1]
//行和列的和都满足题目要求，且所有矩阵元素都是非负的。
//另一个可行的矩阵为：[[1,2],
//                  [3,5]]
//
// 示例 2： 
//输入：rowSum = [5,7,10], colSum = [8,6,8]
//输出：[[0,5,0],
//      [6,1,0],
//      [2,0,8]]
//
// 示例 3： 
//输入：rowSum = [14,9], colSum = [6,9,8]
//输出：[[0,9,5],
//      [6,0,3]]
//
// 示例 4： 
//输入：rowSum = [1,0], colSum = [1]
//输出：[[1],
//      [0]]
//
// 示例 5： 
//输入：rowSum = [0], colSum = [0]
//输出：[[0]]
//
// 提示： 
// 1 <= rowSum.length, colSum.length <= 500 
// 0 <= rowSum[i], colSum[i] <= 10⁸ 
// sum(rowSum) == sum(colSum) 

public class FindValidMatrixGivenRowAndColumnSums{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FindValidMatrixGivenRowAndColumnSums().new Solution();
        System.out.println("预期结果：[[3,0], [1,7]] , 运行结果：" + Arrays.deepToString(solution.restoreMatrix2(new int[]{3, 8}, new int[]{4, 7})));
        System.out.println("预期结果：[[0,5,0], [6,1,0],[2,0,8]], 运行结果：" + Arrays.deepToString(solution.restoreMatrix2(new int[]{5, 7, 10}, new int[]{8, 6, 8})));
        System.out.println("预期结果：[[0,9,5], [6,0,3]] , 运行结果：" + Arrays.deepToString(solution.restoreMatrix2(new int[]{14, 9}, new int[]{6, 9, 8})));
        System.out.println("预期结果：[[1], [0]] , 运行结果：" + Arrays.deepToString(solution.restoreMatrix2(new int[]{1, 0}, new int[]{1})));
        System.out.println("预期结果：[[0]] , 运行结果：" + Arrays.deepToString(solution.restoreMatrix2(new int[]{0}, new int[]{0})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //贪心：对于 matrix 的每一个位置 matrix[i][j]，0≤i<n 且 0≤j<m，我们将 matrix[i][j] 设为 min{rowSum[i],colSum[j]}，
        // 然后将 rowSum[i],colSum[j] 同时减去 matrix[i][j] 即可。当遍历完全部位置后，matrix 即为一个满足要求的答案矩阵。
        //复杂度分析
        // 时间复杂度：O(n×m)，其中 n 和 m 分别为数组 rowSum 和 colSum 的长度，主要为构造 matrix 结果矩阵的时间开销，填充 matrix 的时间复杂度为 O(n+m)。
        // 空间复杂度：O(1)，仅使用常量空间。注意返回的结果数组不计入空间开销。
        
        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
            int ans[][] = new int[rowSum.length][colSum.length];
            for(int i = 0; i < rowSum.length; i++){
                for(int j = 0; j < colSum.length; j++){
                    ans[i][j] = Math.min(rowSum[i], colSum[j]);
                    rowSum[i] -= ans[i][j];
                    colSum[j] -= ans[i][j];
                }
            }
            return ans;    
        }
        
        //注意到 rowSum = 0, colSum = 0 后，不必再继续，所以简单优化
        public int[][] restoreMatrix2(int[] rowSum, int[] colSum) {
            int ans[][] = new int[rowSum.length][colSum.length];
            for(int i = 0, j = 0; i < rowSum.length && j < colSum.length;){
                ans[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= ans[i][j];
                colSum[j] -= ans[i][j];
                i += rowSum[i] == 0 ? 1 : 0;
                j += colSum[j] == 0 ? 1 : 0;
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}