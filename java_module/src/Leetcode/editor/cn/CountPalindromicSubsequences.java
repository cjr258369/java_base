package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-12-01 16:26:13
 * [2484] - 统计回文子序列数目
 * CountPalindromicSubsequences.md
 */
public class CountPalindromicSubsequences{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountPalindromicSubsequences().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.countPalindromes("103301"));
        //System.out.println("预期结果：21 , 运行结果：" + solution.countPalindromes("0000000"));
        //System.out.println("预期结果：2 , 运行结果：" + solution.countPalindromes("9999900000"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //abdba 统计长度为5的回文子序列，那么只有可能是这种类型，
        // 由于题目只有数字出现，那么我们就以遍历所有字符作为d,当前d字符能够贡献的方案数为前面所有ab出现的情况*后面ba出现的情况
        // 因为 ab 是两位数，所以最大只有 99 ， 而且在最坏情况下（所有字符都相同，且 n<= 10^4），结果数量级为 C（n， 5）< 10 ^ 18，因此用long即可，只需在最后返回的时候再取模转为 int
        // 计算过程：
        // 1. 设二维前缀和数组preCount[n][100]，意思是截止到每个下标i，数字num的计数，数字num的取值范围为0..99，统计所有数字的前缀和个数
        // 2. 然后相同方法求后缀数组suffixCount[n][100]，统计所有数字的后缀和个数
        // 3. 遍历下标2..n-3，开始计算以s[i]为中心的回文串，因为2个数字的可能有100种，所以可以遍历0..99的数字x，
        //       用preCount[i-1][x]得到有多少个x，然后再从suffixCount[i+1][x]得到i后面有多少个x的逆序数字，2者的相乘即为以s[i]为中心的回文串数
        public int countPalindromes(String s) {
            char[] arr = s.toCharArray();
            int n = arr.length;

            int[][] preCount = new int[n][100], suffixCount = new int[n][100];
            //初始值
            for(int i = 0; i < n; i++){
                preCount[i][arr[i] - '0']++;
            }
            //统计所有的两位数
            //for(int i = 1; i < n; i++){
            //    for(int j = 0; j < 10; j++){
            //        preCount[i][arr[i] - ] += preCount[i - 1][j];
            //    }
            //}
            
            
            return 1;
            
    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}