package Leetcode.editor.cn;

/**
 * 2023-08-11 09:28:20
 * [1572] - 矩阵对角线元素的和
 * MatrixDiagonalSum.md
 */
 
//给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
// 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。 
//
// 示例 1： 
//输入：mat = [[1,2,3],
//           [4,5,6],
//           [7,8,9]]
//输出：25
//解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25
//请注意，元素 mat[1][1] = 5 只会被计算一次。
//
// 示例 2： 
//输入：mat = [[1,1,1,1],
//           [1,1,1,1],
//           [1,1,1,1],
//           [1,1,1,1]]
//输出：8
//
// 示例 3： 
//输入：mat = [[5]]
//输出：5
//
// 提示： 
// n == mat.length == mat[i].length 
// 1 <= n <= 100 
// 1 <= mat[i][j] <= 100 

public class MatrixDiagonalSum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MatrixDiagonalSum().new Solution();
        System.out.println("预期结果：25 , 运行结果：" + solution.diagonalSum2(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}}));
        System.out.println("预期结果：8 , 运行结果：" + solution.diagonalSum2(new int[][]{{1,1,1,1}, {1,1,1,1}, {1,1,1,1}, {1,1,1,1}}));
        System.out.println("预期结果：5 , 运行结果：" + solution.diagonalSum2(new int[][]{{5}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 是矩阵 mat 的行数。
        // 空间复杂度：O(1)。
        public int diagonalSum(int[][] mat) {
            int n = mat.length, sum = 0;
            for(int i = 0; i < n; i++){
                sum += (mat[i][i] + (i == n - i - 1 ? 0 : mat[i][n - i - 1]));                
            }
            return sum;
        }
        
        //每次循环都判断一下，判断的次数太多了
        //修改为只需在最后判断一下即可，如果 n 为奇数，那么对角线的中点位会重复计算，只需要减一下即可
        public int diagonalSum2(int[][] mat) {
            int n = mat.length, sum = 0;
            for(int i = 0; i < n; i++){
                sum += mat[i][i] + mat[i][n - i - 1];                
            }
            return (n & 1) == 1 ? sum - mat[n/2][n/2] : sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}