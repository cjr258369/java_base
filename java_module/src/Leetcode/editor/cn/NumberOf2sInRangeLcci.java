package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-04-03 14:52:37
 * [面试题 17.06] - 2出现的次数
 * NumberOf2sInRangeLcci.md
 */
 
//编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。 
//
// 示例: 
// 输入: 25
//输出: 9
//解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次) 
//
// 提示： 
// n <= 10^9 

public class NumberOf2sInRangeLcci{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOf2sInRangeLcci().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.numberOf2sInRange(2));
        System.out.println("预期结果：9 , 运行结果：" + solution.numberOf2sInRange(25));
        System.out.println("预期结果：488869169 , 运行结果：" + solution.numberOf2sInRange(483127807));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int dp[][];
        char s[];
        public int numberOf2sInRange(int n) {
            s = ("" + n).toCharArray();
            dp = new int[s.length][s.length];
            for(int[] d : dp) Arrays.fill(d, -1);
            return dfs(0, 0, true, false);    
        }

        private int dfs(int i, int cnt, boolean isLimit, boolean isNum){
            if(i == s.length){
                return cnt;
            }
            if(!isLimit && dp[i][cnt] >= 0){
                return dp[i][cnt];
            }
            int res = 0;
            for(int x = 0, up = isLimit ? s[i] - '0' : 9; x <= up; x++){
                res += dfs(i + 1, cnt + (x == 2 ? 1 : 0), isLimit && x == up, isNum);
            }
            if(!isLimit){
                dp[i][cnt] = res;
            }
           return res;
        }
        
        //方法2：数学
    }
    //leetcode submit region end(Prohibit modification and deletion)

}