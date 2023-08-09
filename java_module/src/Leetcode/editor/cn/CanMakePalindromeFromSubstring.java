package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023-06-15 16:34:01
 * [1177] - 构建回文串检测
 * CanMakePalindromeFromSubstring.md
 */
 
//给你一个字符串 s，请你对 s 的子串进行检测。
// 每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[right]，并从中选择 最多 k 项替换成任何小写英文字母。
// 如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。
// 返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。 
//
// 注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，如果 s[left..right] = "aaa" 且 k = 2，我们只能替换其中的两个字母。（另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的） 
//
// 示例： 
// 输入：s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
//输出：[true,false,false,true,true]
//解释：
//queries[0] : 子串 = "d"，回文。
//queries[1] : 子串 = "bc"，不是回文。
//queries[2] : 子串 = "abcd"，只替换 1 个字符是变不成回文串的。
//queries[3] : 子串 = "abcd"，可以变成回文的 "abba"。 也可以变成 "baab"，先重新排序变成 "bacd"，然后把 "cd"替换为 "ab"。
//queries[4] : 子串 = "abcda"，可以变成回文的 "abcba"。
//
// 提示： 
// 1 <= s.length, queries.length <= 10^5 
// 0 <= queries[i][0] <= queries[i][1] < s.length 
// 0 <= queries[i][2] <= s.length 
// s 中只有小写英文字母 

public class CanMakePalindromeFromSubstring{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CanMakePalindromeFromSubstring().new Solution();
        System.out.println("预期结果：[true,false,false,true,true] , 运行结果：" + solution.canMakePaliQueries4("abcda", new int[][]{{3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //整体的优化流程及复杂度查看：https://leetcode.cn/problems/can-make-palindrome-from-substring/solution/yi-bu-bu-you-hua-cong-qian-zhui-he-dao-q-yh5p/
        
        //优化前，统计每个字母的前缀和
        public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
            int n = s.length(), sum[][] = new int[n + 1][26];
            for(int i = 0; i < n; i++){
                sum[i + 1] = sum[i].clone();
                sum[i + 1][s.charAt(i) -'a']++;
            }
            
            ArrayList<Boolean> ans = new ArrayList<>(queries.length);
            for(int[] query : queries){
                int m = 0;
                for(int i = 0; i < 26; i++){
                    m += (sum[query[1] + 1][i] - sum[query[0]][i]) % 2; // 奇数+1，偶数+0                    
                }
                ans.add(m / 2 <= query[2]);
            }
            return ans;            
        }
        
        
        //优化1：因为只需判奇偶，因此换成 每个字母的 奇偶性即可
        //至于奇偶的判断：为方便计算，用 0 表示出现偶数次，用 1 表示出现奇数次。
        // 由于只有奇数减偶数，或者偶数减奇数，才能得到奇数。所以如果相减的结果不为 0（或者说相减的两个数不相等），就表示出现了奇数次。
        public List<Boolean> canMakePaliQueries2(String s, int[][] queries) {
            int n = s.length(), sum[][] = new int[n + 1][26];
            for(int i = 0; i < n; i++){
                sum[i + 1] = sum[i].clone();
                sum[i + 1][s.charAt(i) -'a']++;
                sum[i + 1][s.charAt(i) -'a'] %= 2;  // 偶数是 0
            }
            
            ArrayList<Boolean> ans = new ArrayList<>(queries.length);
            for(int[] query : queries){
                int m = 0;
                for(int i = 0; i < 26; i++){
                    m += (sum[query[1] + 1][i] != sum[query[0]][i] ? 1 : 0); // 奇数+1，偶数+0
                }
                ans.add(m / 2 <= query[2]);
            }
            return ans;            
        }
        
        //优化2：更进一步直接使用异或来处理奇偶，因为异或运算满足 1 和 0 的结果是 1，而 0 和 0，以及 1 和 1 的结果都是 0，所以可以用异或替换上面的减法
        public List<Boolean> canMakePaliQueries3(String s, int[][] queries) {
            int n = s.length(), sum[][] = new int[n + 1][26];
            for(int i = 0; i < n; i++){
                sum[i + 1] = sum[i].clone();
                sum[i + 1][s.charAt(i) -'a'] ^= 1;  // 奇数变偶数，偶数变奇数
            }

            ArrayList<Boolean> ans = new ArrayList<>(queries.length);
            for(int[] query : queries){
                int m = 0;
                for(int i = 0; i < 26; i++){
                    m += (sum[query[1] + 1][i] ^ sum[query[0]][i]);
                }
                ans.add(m / 2 <= query[2]);
            }
            return ans;
        }
        
        //优化3：再进一步直接不需要用二维数组，优化为一维数组，使用一个 26位的二进制数字来表示 当前的前缀和的每个字母的奇偶性
        public List<Boolean> canMakePaliQueries4(String s, int[][] queries) {
            int n = s.length(), sum[] = new int[n + 1];
            for(int i = 0; i < n; i++){
                int bit = 1 << (s.charAt(i) - 'a');
                sum[i + 1] = sum[i] ^ bit;  // 该比特对应字母的奇偶性：奇数变偶数，偶数变奇数
            }

            ArrayList<Boolean> ans = new ArrayList<>(queries.length);
            for(int[] query : queries){
                int m = Integer.bitCount(sum[query[1] + 1] ^ sum[query[0]]);
                ans.add(m / 2 <= query[2]);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}