package Leetcode.editor.cn;

/**
 * 2023-03-29 09:47:58
 * [1641] - 统计字典序元音字符串的数目
 * CountSortedVowelStrings.md
 */
 
//给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。 
//
// 示例 1： 
//输入：n = 1
//输出：5
//解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"]
//
// 示例 2： 
//输入：n = 2
//输出：15
//解释：仅由元音组成的 15 个字典序字符串为
//["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"]
//注意，"ea" 不是符合题意的字符串，因为 'e' 在字母表中的位置比 'a' 靠后
//
// 示例 3： 
//输入：n = 33
//输出：66045
//
// 提示： 
// 1 <= n <= 50 

public class CountSortedVowelStrings{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountSortedVowelStrings().new Solution();
        System.out.println("预期结果：5 , 运行结果：" + solution.countVowelStrings(1));
        System.out.println("预期结果：15 , 运行结果：" + solution.countVowelStrings(2));
        System.out.println("预期结果：66045 , 运行结果：" + solution.countVowelStrings(33));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //数学求组合数：C(n + 4, 4)
        //复杂度分析
        // 时间复杂度：O(Σ)，其中 Σ=5 表示元音字符集大小。
        // 空间复杂度：O(1)。
        public int countVowelStrings(int n) {
            return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}