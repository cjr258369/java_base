package Leetcode.editor.cn;

/**
 * 2022-11-27 14:52:27
 * [1704] - 判断字符串的两半是否相似
 * DetermineIfStringHalvesAreAlike.md
 */
public class DetermineIfStringHalvesAreAlike{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new DetermineIfStringHalvesAreAlike().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.halvesAreAlike("book"));
        System.out.println("预期结果：false , 运行结果：" + solution.halvesAreAlike("textbook"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean halvesAreAlike(String s) {
            int cnt = 0, h[] = new int[123];
            h['a'] = h['e'] = h['i'] = h['o'] = h['u'] = h['A'] = h['E'] = h['I'] = h['O'] = h['U'] = 1;
            for(int i = 0, j = s.length() - 1; i < j; i++, j--){
                cnt += h[s.charAt(i)] - h[s.charAt(j)];
            }
            return cnt == 0;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}