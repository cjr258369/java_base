package Leetcode.editor.cn;

/**
 * 2023-08-09 09:44:33
 * [1281] - 整数的各位积和之差
 * SubtractTheProductAndSumOfDigitsOfAnInteger.md
 */
 
//给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。 
//
// 示例 1： 
// 输入：n = 234
//输出：15 
//解释：
//各位数之积 = 2 * 3 * 4 = 24 
//各位数之和 = 2 + 3 + 4 = 9 
//结果 = 24 - 9 = 15
//
// 示例 2： 
// 输入：n = 4421
//输出：21
//解释： 
//各位数之积 = 4 * 4 * 2 * 1 = 32 
//各位数之和 = 4 + 4 + 2 + 1 = 11 
//结果 = 32 - 11 = 21
//
// 提示： 
// 1 <= n <= 10^5 

public class SubtractTheProductAndSumOfDigitsOfAnInteger{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SubtractTheProductAndSumOfDigitsOfAnInteger().new Solution();
        System.out.println("预期结果：15 , 运行结果：" + solution.subtractProductAndSum(234));
        System.out.println("预期结果：21 , 运行结果：" + solution.subtractProductAndSum(4421));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //模拟
        // 复杂度分析
        // 时间复杂度：O(logn)，其中 nnn 为题目给定的数字。
        // 空间复杂度：O(1)，仅使用常量空间。
        public int subtractProductAndSum(int n) {
            int prod = 1, sum = 0;
            while(n != 0){
                int temp = n % 10;
                n /= 10;
                prod *= temp;
                sum += temp;
            }
            return prod - sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}