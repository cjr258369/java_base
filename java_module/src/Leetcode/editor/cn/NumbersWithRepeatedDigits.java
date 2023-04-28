package Leetcode.editor.cn;

/**
 * 2023-03-20 09:28:15
 * [1012] - 至少有 1 位重复的数字
 * NumbersWithRepeatedDigits.md
 */
 
//给定正整数 n，返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。 
//
// 示例 1： 
//输入：n = 20
//输出：1
//解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。
//
// 示例 2： 
//输入：n = 100
//输出：10
//解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
//
// 示例 3： 
//输入：n = 1000
//输出：262
//
// 提示： 
// 1 <= n <= 10⁹ 

public class NumbersWithRepeatedDigits{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumbersWithRepeatedDigits().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.numDupDigitsAtMostN(20));
        System.out.println("预期结果：10 , 运行结果：" + solution.numDupDigitsAtMostN(100));
        System.out.println("预期结果：262 , 运行结果：" + solution.numDupDigitsAtMostN(1000));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        char[] arr;
        public int numDupDigitsAtMostN(int n) {
            arr = ("" + n).toCharArray();
            return n - dfs(0, 0, true, false);
        }
        
        private int dfs(int i, int mask, boolean isLimit, boolean isNum){
            if(i == arr.length){
                return isNum ? 1 : 0;
            }
            int res = 0;
            if(!isNum){
                res = dfs(i + 1, mask, false, false);
            }
            int up = isLimit ? arr[i] - '0' : 9;
            for(int x = isNum ? 0 : 1; x <= up; x++){
                if((mask >> x & 1) == 0){
                    res += dfs(i + 1, mask | (1 << x), isLimit && x == up, true);
                }
            }
            return res;
        }
        
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}