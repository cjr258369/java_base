package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023-02-23 09:29:10
 * [1238] - 循环码排列
 * CircularPermutationInBinaryRepresentation.md
 */
 
//给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足： 
// p[0] = start 
// p[i] 和 p[i+1] 的二进制表示形式只有一位不同 
// p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同 
//
// 示例 1： 
// 输入：n = 2, start = 3
// 输出：[3,2,0,1]
// 解释：这个排列的二进制表示是 (11,10,00,01) 所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
// 
//
// 示例 2： 
// 输出：n = 3, start = 2
// 输出：[2,6,7,5,4,0,1,3]
// 解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
// 
// 提示： 
// 1 <= n <= 16 
// 0 <= start < 2^n 

public class CircularPermutationInBinaryRepresentation{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CircularPermutationInBinaryRepresentation().new Solution();
        System.out.println("预期结果：[3,2,0,1] , 运行结果：" + solution.circularPermutation(2, 3));
        System.out.println("预期结果：[2,6,7,5,4,0,1,3] , 运行结果：" + solution.circularPermutation(3, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //详细格雷码的说明可以参考：Leetcode.editor.cn.GrayCode
        //直接套用格雷码公式，因为 需要从 start开始，所以公式内多异或一个 start复杂度分析
        //复杂度分析：
        // 时间复杂度：O(2^n)。每一个格雷码生成的时间为 O(1)，总时间为 O(2^n)。
        // 空间复杂度：O(1)。这里返回值不计入空间复杂度。
        public List<Integer> circularPermutation(int n, int start) {
            List<Integer> res = new ArrayList<>();
            for(int i = 0; i < 1 << n; i++){
                res.add(i ^ (i >> 1) ^ start);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}