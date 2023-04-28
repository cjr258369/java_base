package Leetcode.editor.cn;

/**
 * 2023-04-24 16:06:31
 * [1163] - 按字典序排在最后的子串
 * LastSubstringInLexicographicalOrder.md
 */
 
//给你一个字符串 s ，找出它的所有子串并按字典序排列，返回排在最后的那个子串。 
//
// 示例 1： 
//输入：s = "abab"
//输出："bab"
//解释：我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是"bab"。
//
// 示例 2： 
//输入：s = "leetcode"
//输出："tcode"
//
// 提示： 
// 1 <= s.length <= 4 * 10⁵ 
// s 仅含有小写英文字符。 

public class LastSubstringInLexicographicalOrder{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LastSubstringInLexicographicalOrder().new Solution();
        System.out.println("预期结果：bab , 运行结果：" + solution.lastSubstring("abab"));
        System.out.println("预期结果：tcode , 运行结果：" + solution.lastSubstring("leetcode"));
        System.out.println("预期结果：cb , 运行结果：" + solution.lastSubstring("cacacb"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String lastSubstring(String s) {
            int i = 0, j = 1, n = s.length();
            while(j < n){
                int k = 0;
                while(j + k < n && s.charAt(i + k) == s.charAt(j + k)){
                    k++;
                }

                if(j + k < n && s.charAt(j + k) > s.charAt(i + k)){
                    int temp = i;
                    i = j;
                    j = Math.max(j + 1, temp + k + 1);
                }else{
                    j = j + k + 1;
                }
            }
            return s.substring(i);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}