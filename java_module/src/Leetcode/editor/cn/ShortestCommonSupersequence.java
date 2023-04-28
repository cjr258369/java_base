package Leetcode.editor.cn;

/**
 * 2023-03-28 10:29:09
 * [1092] - 最短公共超序列
 * ShortestCommonSupersequence.md
 */
 
//给出两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为子序列的最短字符串。如果答案不止一个，则可以返回满足条件的任意一个答案。
// （如果从字符串 T 中删除一些字符（也可能不删除，并且选出的这些字符可以位于 T 中的 任意位置），可以得到字符串 S，那么 S 就是 T 的子序列） 
//
// 示例： 
// 输入：str1 = "abac", str2 = "cab"
//输出："cabac"
//解释：
//str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。 
//str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
//最终我们给出的答案是满足上述属性的最短字符串。
//
// 提示： 
// 1 <= str1.length, str2.length <= 1000 
// str1 和 str2 都由小写英文字母组成。 

public class ShortestCommonSupersequence{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ShortestCommonSupersequence().new Solution();
        System.out.println("预期结果：cabac , 运行结果：" + solution.shortestCommonSupersequence("abac", "cab"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //动态规划
        // 复杂度分析
        // 时间复杂度：O(n×m)。其中预处理动态规划求解的复杂度为 O(n×m)，构造具体字符串方案的复杂度为 O(n+m)，其中 m 为字符串 str1 的长度，n 为字符串 str2 的长度。
        // 空间复杂度：O(n×m)，其中 m 为字符串 str1 的长度，n 为字符串 str2 的长度。空间复杂度主要取决于动态规划模型中状态的总数。
        public String shortestCommonSupersequence(String str1, String str2) {
            int m = str1.length(), n = str2.length(), dp[][] = new int[m + 1][n + 1];
            for(int i = 0; i < m; i++){
                dp[i][n] = m - i;
            }
            for(int i = 0; i < n; i++){
                dp[m][i] = n - i;
            }
            for(int i = m - 1; i >= 0; i--){
                for(int j = n - 1; j >= 0; j--){
                    if(str1.charAt(i) == str2.charAt(j)){
                        dp[i][j] = dp[i + 1][j + 1] + 1;                        
                    }else{
                        dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) + 1;
                    }
                }
            }
            StringBuilder ans = new StringBuilder();
            int t1 = 0, t2 = 0;
            while(t1 < m && t2 < n){
                if(str1.charAt(t1) == str2.charAt(t2)){
                    ans.append(str1.charAt(t1));
                    t1++;
                    t2++;                    
                }else if(dp[t1 + 1][t2] == dp[t1][t2] - 1){
                    ans.append(str1.charAt(t1));
                    t1++;                    
                }else if(dp[t1][t2 + 1] == dp[t1][t2] - 1){
                    ans.append(str2.charAt(t2));
                    t2++;
                }
            }
            if(t1 < m){
                ans.append(str1.substring(t1));
            }else if(t2 < n){
                ans.append(str2.substring(t2));
            }
            return ans.toString();    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}