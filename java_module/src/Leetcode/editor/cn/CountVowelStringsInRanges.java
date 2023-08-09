package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-06-02 09:17:10
 * [2559] - 统计范围内的元音字符串数
 * CountVowelStringsInRanges.md
 */
 
//给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。 
// 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。 
// 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。 
// 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。 
//
// 示例 1： 
//输入：words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
//输出：[2,3,0]
//解释：以元音开头和结尾的字符串是 "aba"、"ece"、"aa" 和 "e" 。
//查询 [0,2] 结果为 2（字符串 "aba" 和 "ece"）。
//查询 [1,4] 结果为 3（字符串 "ece"、"aa"、"e"）。
//查询 [1,1] 结果为 0 。
//返回结果 [2,3,0] 。
//
// 示例 2： 
//输入：words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
//输出：[3,2,1]
//解释：每个字符串都满足这一条件，所以返回 [3,2,1] 。 
//
// 提示： 
// 1 <= words.length <= 10⁵ 
// 1 <= words[i].length <= 40 
// words[i] 仅由小写英文字母组成 
// sum(words[i].length) <= 3 * 10⁵ 
// 1 <= queries.length <= 10⁵ 
// 0 <= queries[j][0] <= queries[j][1] < words.length 

public class CountVowelStringsInRanges{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountVowelStringsInRanges().new Solution();
        System.out.println("预期结果：[2,3,0] , 运行结果：" + Arrays.toString(solution.vowelStrings(new String[]{"aba", "bcb", "ece", "aa", "e"}, new int[][]{{0, 2}, {1, 4}, {1, 1}})));
        System.out.println("预期结果：[3,2,1] , 运行结果：" + Arrays.toString(solution.vowelStrings(new String[]{"a", "e", "i"}, new int[][]{{0, 2}, {0, 1}, {2, 2}})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        String p = "[aeiou](.*[aeiou])?";
        
        //前缀和【正则的匹配慢一些，因为要整个字符串整体去匹配，用直接取charAt(0) 和 charAt(word.length() - 1) 然后用或运算判断是否是元音字符会快很多】
        //复杂度分析
        // 时间复杂度：O(n+q)，其中 n 是数组 words 的长度，q 是数组 queries 的长度（即查询数）。计算前缀和数组的时间是 O(n)，然后计算 q 个查询的答案，计算每个查询的答案的时间是 O(1)，因此时间复杂度是 O(n+q)。
        // 空间复杂度：O(n)，其中 n 是数组 words 的长度。需要创建长度为 n+1 的前缀和数组。注意返回值不计入空间复杂度。
        
        public int[] vowelStrings(String[] words, int[][] queries) {
            int preSum[] = new int[words.length + 1], ans[] = new int[queries.length];
            for(int i = 0; i < words.length; i++){
                preSum[i + 1] = preSum[i] + (words[i].matches(p) ? 1 : 0);
            }
            for(int i = 0; i < queries.length; i++){
                ans[i] = preSum[queries[i][1] + 1] - preSum[queries[i][0]];
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}