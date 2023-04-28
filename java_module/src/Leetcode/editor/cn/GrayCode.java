package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023-02-23 11:05:04
 * [89] - 格雷编码
 * GrayCode.md
 */
 
//n 位格雷码序列 是一个由 2ⁿ 个整数组成的序列，其中：
// 每个整数都在范围 [0, 2ⁿ - 1] 内（含 0 和 2ⁿ - 1） 
// 第一个整数是 0 
// 一个整数在序列中出现 不超过一次 
// 每对 相邻 整数的二进制表示 恰好一位不同 ，且  第一个 和 最后一个 整数的二进制表示 恰好一位不同 
//
// 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。 
//
// 示例 1： 
//输入：n = 2
//输出：[0,1,3,2]
//解释：
//[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
//- 00 和 01 有一位不同
//- 01 和 11 有一位不同
//- 11 和 10 有一位不同
//- 10 和 00 有一位不同
//[0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
//- 00 和 10 有一位不同
//- 10 和 11 有一位不同
//- 11 和 01 有一位不同
//- 01 和 00 有一位不同
//
// 示例 2： 
//输入：n = 1
//输出：[0,1]
//
// 提示： 
// 1 <= n <= 16 

public class GrayCode{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new GrayCode().new Solution();
        System.out.println("预期结果：[0,1,3,2] , 运行结果：" + solution.grayCode(2));
        System.out.println("预期结果：[0,1] , 运行结果：" + solution.grayCode(1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接使用格雷码的推导公式。
        //格雷码的介绍：https://baike.baidu.com/item/%E6%A0%BC%E9%9B%B7%E7%A0%81
        //相关公式推导：https://blog.csdn.net/jingfengvae/article/details/51691124
        //复杂度分析；
        // 时间复杂度：O(2^n)。每一个格雷码生成的时间为 O(1)，总时间为 O(2^n)。
        // 空间复杂度：O(1)。这里返回值不计入空间复杂度。
        public List<Integer> grayCode(int n) {
            List<Integer> res = new ArrayList<>();
            for(int i = 0; i < 1 << n;  i++){
                res.add(i ^ (i >> 1));
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}