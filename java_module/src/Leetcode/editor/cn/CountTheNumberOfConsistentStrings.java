package Leetcode.editor.cn;

/**
 * 2022-11-08 09:19:35
 * [1684] - 统计一致字符串的数目
 * CountTheNumberOfConsistentStrings.md
 */
public class CountTheNumberOfConsistentStrings{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountTheNumberOfConsistentStrings().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.countConsistentStrings2("ab", new String[]{"ad","bd","aaab","baa","badab"}));
        System.out.println("预期结果：7 , 运行结果：" + solution.countConsistentStrings2("abc", new String[]{"a","b","c","ab","ac","bc","abc"}));
        System.out.println("预期结果：4 , 运行结果：" + solution.countConsistentStrings2("cad", new String[]{"cc","acd","b","ba","bac","bad","ac","d"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countConsistentStrings(String allowed, String[] words) {
            int[] allow = new int[26];
            for(char ch : allowed.toCharArray()){
                allow[ch - 'a']++;
            }
            
            int ans = 0;
            for(String word : words){
                int i = 0;
                while(i < word.length() && allow[word.charAt(i) - 'a'] > 0){
                    i++;                    
                }
                ans += (i >= word.length() ? 1 : 0);
            }
            return ans;
        }
        
        //用位运算，压缩一下用了数组的空间
        public int countConsistentStrings2(String allowed, String[] words) {
            int mask = 0;
            for(int i = 0; i < allowed.length(); i++){
                mask |= 1 << (allowed.charAt(i) - 'a');
            }
            //for(char ch : allowed.toCharArray()){
            //    mask |= 1 << (ch - 'a');
            //}
            
            int ans = 0;
            for(String word : words){
                int wordMask = 0;
                for(int i = 0; i < word.length(); i++){
                    wordMask |= 1 << (word.charAt(i) - 'a');
                }
                //for(char ch : word.toCharArray()){
                //    wordMask |= 1 << (ch - 'a');
                //}
                ans += ((mask | wordMask) == mask ? 1 : 0);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}