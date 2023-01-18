package Leetcode.editor.cn;

/**
 * 2023-01-17 15:10:32
 * [3] - 无重复字符的最长子串
 * LongestSubstringWithoutRepeatingCharacters.md
 */
 
//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
// 示例 2: 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
// 示例 3: 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// 提示： 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 

public class LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println("预期结果：1 , 运行结果：" + solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println("预期结果：3 , 运行结果：" + solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println("预期结果：0 , 运行结果：" + solution.lengthOfLongestSubstring(""));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
        // 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0, 128)
        // 内的字符，即 ∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，而字符最多有 ∣Σ∣ 个，因此空间复杂度为 O(∣Σ∣)。
        public int lengthOfLongestSubstring(String s) {
            int max = 0, map[] = new int[128];
            for(int left = 0, right = 0; right < s.length(); right++){
                map[s.charAt(right)]++;
                while(map[s.charAt(right)] > 1){
                    map[s.charAt(left++)]--;
                }
                max = Math.max(max, right - left + 1);
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}