package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-07-04 09:14:28
 * [2679] - 矩阵中的和
 * SumInAMatrix.md
 */
 
//给你一个下标从 0 开始的二维整数数组 nums 。一开始你的分数为 0 。你需要执行以下操作直到矩阵变为空： 
// 矩阵中每一行选取最大的一个数，并删除它。如果一行中有多个最大的数，选择任意一个并删除。 
// 在步骤 1 删除的所有数字中找到最大的一个数字，将它添加到你的 分数 中。 
// 请你返回最后的 分数 。 
//
// 示例 1： 
//输入：nums = [[7,2,1],[6,4,2],[6,5,3],[3,2,1]]
//输出：15
//解释：第一步操作中，我们删除 7 ，6 ，6 和 3 ，将分数增加 7 。下一步操作中，删除 2 ，4 ，5 和 2 ，将分数增加 5 。最后删除 1 ，2 ，3 和 1 ，将分数增加 3 。所以总得分为 7 + 5 + 3 = 15 。
//
// 示例 2： 
//输入：nums = [[1]]
//输出：1
//解释：我们删除 1 并将分数增加 1 ，所以返回 1 。 
//
// 提示： 
// 1 <= nums.length <= 300 
// 1 <= nums[i].length <= 500 
// 0 <= nums[i][j] <= 10³ 

public class SumInAMatrix{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SumInAMatrix().new Solution();
        System.out.println("预期结果：15 , 运行结果：" + solution.matrixSum(new int[][]{{7,2,1}, {6,4,2}, {6,5,3}, {3,2,1}}));
        System.out.println("预期结果：1 , 运行结果：" + solution.matrixSum(new int[][]{{1}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析.
        // 时间复杂度：O(mnlogn)，其中 m,n 分别为矩阵的行数与列数。对矩阵中一行元素进行排序需要的时间复杂度为 nlogn，一共有 m 行，因此矩阵所有行排序的时间复杂度为 O(mnlogn)，
        //              遍历矩阵中的所有元素需要的时间复杂度为 O(mn)，因此总的时间复杂度为 O(mnlogn+mn)=O(mnlogn)。
        // 空间复杂度：O(mlogn)，其中 m,n 分别为矩阵的行数与列数。对矩阵中每一行进行排序需要的空间为 logn，矩阵一共有 m 行，因此总的空间复杂度为 O(mlogn)。
        public int matrixSum(int[][] nums) {
            for(int[] row : nums){
                Arrays.sort(row);
            }
            int ans = 0;
            for(int i = 0; i < nums[0].length; i++){
                int max = 0;
                for(int j = 0; j < nums.length; j++){
                    max = Math.max(max, nums[j][i]);                    
                }
                ans += max;
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}