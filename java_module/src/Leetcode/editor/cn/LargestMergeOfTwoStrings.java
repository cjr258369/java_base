package Leetcode.editor.cn;

/**
 * 2022-12-24 10:11:08
 * [1754] - 构造字典序最大的合并字符串
 * LargestMergeOfTwoStrings.md
 */
public class LargestMergeOfTwoStrings{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LargestMergeOfTwoStrings().new Solution();
        System.out.println("预期结果： cbcabaaaaa, 运行结果：" + solution.largestMerge("cabaa", "bcaaa"));
        System.out.println("预期结果： abdcabcabcaba, 运行结果：" + solution.largestMerge("abcabc", "abdcaba"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String largestMerge(String word1, String word2) {
            StringBuilder merge = new StringBuilder();
            for(int i = 0, j = 0; i < word1.length() || j < word2.length();){
                if(i < word1.length() && word1.substring(i).compareTo(word2.substring(j)) > 0){
                    merge.append(word1.charAt(i++));
                }else{
                    merge.append(word2.charAt(j++));
                }
            }
            return merge.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}