package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-03-21 10:16:05
 * [233] - 数字 1 的个数
 * NumberOfDigitOne.md
 */
 
//给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。 
//
// 示例 1： 
//输入：n = 13
//输出：6
//
// 示例 2： 
//输入：n = 0
//输出：0
//
// 提示： 
// 0 <= n <= 10⁹ 

public class NumberOfDigitOne{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOfDigitOne().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.countDigitOne(13));
        System.out.println("预期结果：0 , 运行结果：" + solution.countDigitOne(0));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        char[] arr;
        int[][] dp;
        public int countDigitOne(int n) {
            arr = ("" + n).toCharArray();
            dp = new int[arr.length][arr.length];
            for(int i = 0; i < arr.length; i++){
                Arrays.fill(dp[i], -1);
            }
            return dfs(0, 0, true, false);    
        }
        
        private int dfs(int i, int cnt, boolean isLimit, boolean isNum){
            if(i == arr.length){
                return cnt;
            }
            if(!isLimit && dp[i][cnt] >= 0){
                return dp[i][cnt];
            }
            int res = 0;
            // 枚举要填入的数字 d
            for(int x = 0, up = isLimit ? arr[i] - '0' : 9; x <= up; x++){
                res += dfs(i + 1, cnt + (x == 1 ? 1 : 0), isLimit && x == up, isNum);
            }
            if(!isLimit){
                dp[i][cnt] = res;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}