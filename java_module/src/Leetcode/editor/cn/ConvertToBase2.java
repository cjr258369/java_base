package Leetcode.editor.cn;

/**
 * 2023-04-06 09:22:46
 * [1017] - 负二进制转换
 * ConvertToBase2.md
 */
 
//给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。 
// 注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。 
//
// 示例 1： 
//输入：n = 2
//输出："110"
//解释：(-2)² + (-2)¹ = 2
//
// 示例 2： 
//输入：n = 3
//输出："111"
//解释：(-2)² + (-2)¹ + (-2)⁰ = 3
//
// 示例 3： 
//输入：n = 4
//输出："100"
//解释：(-2)² = 4
//
// 提示： 
// 0 <= n <= 10⁹ 

public class ConvertToBase2{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ConvertToBase2().new Solution();
        System.out.println("预期结果：110 , 运行结果：" + solution.baseNeg2_2(2));
        System.out.println("预期结果：111 , 运行结果：" + solution.baseNeg2_2(3));
        System.out.println("预期结果：100 , 运行结果：" + solution.baseNeg2_2(4));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //进制转换：十进制转N进制：除N取余，逆序排列。
        // https://leetcode.cn/problems/convert-to-base-2/solution/python3-duan-chu-fa-fu-er-jin-zhi-zhuan-1zahd/
        // 但有个小问题，就是整数相除时到底是要上取整、下取整还是要向零取整呢?
        //public String baseNeg2(int n) {
        //
        //}
        
        //可以参考这个思路，优化至 1 行：
        //原帖：https://leetcode.cn/problems/convert-to-base-2/solution/java-san-xing-dai-ma-tong-guo-by-maple_7-mass/
        //在原帖的基础上优化：https://leetcode.cn/problems/convert-to-base-2/solution/java-yi-xing-xie-fa-by-jovial-chebyshevs-p7d4/
        public String baseNeg2_2(int n) {
            return Integer.toBinaryString(0x55555555 ^ 0x55555555 - n); 
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}