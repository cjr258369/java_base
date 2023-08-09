package Leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 2023-05-15 10:11:44
 * [1072] - 按列翻转得到最大值等行数
 * FlipColumnsForMaximumNumberOfEqualRows.md
 */
 
//给定 m x n 矩阵 matrix 。
// 你可以从中选出任意数量的列并翻转其上的 每个 单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）
// 返回 经过一些翻转后，行与行之间所有值都相等的最大行数 。 
//
// 示例 1： 
//输入：matrix = [[0,1],[1,1]]
//输出：1
//解释：不进行翻转，有 1 行所有值都相等。
//
// 示例 2： 
//输入：matrix = [[0,1],[1,0]]
//输出：2
//解释：翻转第一列的值之后，这两行都由相等的值组成。
//
// 示例 3： 
//输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
//输出：2
//解释：翻转前两列的值之后，后两行由相等的值组成。 
//
// 提示： 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] == 0 或 1 

public class FlipColumnsForMaximumNumberOfEqualRows{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FlipColumnsForMaximumNumberOfEqualRows().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.maxEqualRowsAfterFlips(new int[][]{{0,1}, {1, 1}}));
        System.out.println("预期结果：2 , 运行结果：" + solution.maxEqualRowsAfterFlips(new int[][]{{0,1}, {1, 0}}));
        System.out.println("预期结果：2 , 运行结果：" + solution.maxEqualRowsAfterFlips(new int[][]{{0,0,0}, {0, 0, 1},{1,1,0}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //本质是找出所有行中模式相同的行数最大值，也即 001 和 110 是相同的模式串
        //因此需要把他们都转换为相同的模式串，然后判断哪种模式串的数量最大。
        //0开头的，每一个数与 0 异或依旧是：001，1开头的，每一个数与 1 异或则会变成：001
        
        //复杂度分析
        // 时间复杂度：O(mn)，其中 m 和 n 分别是矩阵 matrix 的行数和列数。过程中，我们遍历了矩阵中的每个元素各一次，这部分的时间复杂度为 O(mn)。
        //           哈希表中共有 m 个元素，每个元素长度为 n，所有元素插入和查询的渐进复杂度仍为 O(mn)。因此总体时间复杂度为 O(mn)。
        // 空间复杂度：O(m)。哈希表存放了 m 个字符串的哈希值，空间复杂度为 O(m)。
        public int maxEqualRowsAfterFlips(int[][] matrix) {
            HashMap<String, Integer> map = new HashMap<>();
            for(int i = 0; i < matrix.length; i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < matrix[0].length; j++){
                    sb.append(matrix[i][j] ^ matrix[i][0]);
                }
                String key = sb.toString();
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            
            int ans = 0;
            for(int max : map.values()){
                ans = Math.max(ans, max);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}