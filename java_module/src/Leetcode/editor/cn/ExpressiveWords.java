package Leetcode.editor.cn;

/**
 * 2022-11-25 15:39:08
 * [809] - 情感丰富的文字
 * ExpressiveWords.md
 */
public class ExpressiveWords{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ExpressiveWords().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
        System.out.println("预期结果：3 , 运行结果：" + solution.expressiveWords("zzzzzyyyyy", new String[]{"zzyy","zy","zyy"}));
        System.out.println("预期结果：3 , 运行结果：" + solution.expressiveWords("dddiiiinnssssssoooo", new String[]{"dinnssoo","ddinso","ddiinnso","ddiinnssoo","ddiinso","dinsoo","ddiinsso","dinssoo","dinso"}));
     
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int expressiveWords(String s, String[] words) {
        int len = s.length(), ans = words.length;
        char[] arr = s.toCharArray();
        for(String word : words){
            ans -= expressiveWords(arr, word.toCharArray()) ? 0 : 1;
        }
        return ans;
    }
    boolean expressiveWords(char[] s, char[] t){
        // word的字符串长度不允许超过s的字符串长度
        if(t.length > s.length){
            return false;
        }
        int cp,p1 = 0, p2 = 0;
        while((cp = p1) < s.length && p2 < t.length){
            if(s[p1] != t[p2]){
                return false;
            }
            
            int cnt1 = 0;            
            while(p1 < s.length && s[p1] == s[cp]){
                // 在字符串s中，遍历连续的字符sc[cp]的数量
                cnt1++;
                p1++;
            }
            
            int cnt2 = 0;
            while(p2 < t.length && t[p2] == s[cp]){
                // 在字符串word中，遍历连续的的字符sc[cp]的数量
                cnt2++;
                p2++;
            }
            
            if((cnt1 < 3 && cnt1 != cnt2) || (cnt1 >= 3 && cnt1 < cnt2)){
                return false;
            }
        }
        // 只有s和t都被遍历完毕，才返回true
        return p1 == s.length && p2 == t.length;      
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}