package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 2023-04-27 11:49:17
 * [1048] - 最长字符串链
 * LongestStringChain.md
 */
 
//给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
// 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的前身 。 
// 
// 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是
// word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。 
//
// 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。 
//
// 示例 1： 
//输入：words = ["a","b","ba","bca","bda","bdca"]
//输出：4
//解释：最长单词链之一为 ["a","ba","bda","bdca"]
//
// 示例 2: 
//输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
//输出：5
//解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
//
// 示例 3: 
//输入：words = ["abcd","dbqca"]
//输出：1
//解释：字链["abcd"]是最长的字链之一。
//["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
//
// 提示： 
// 1 <= words.length <= 1000 
// 1 <= words[i].length <= 16 
// words[i] 仅由小写英文字母组成。 

public class LongestStringChain{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LongestStringChain().new Solution();
        System.out.println("预期结果：4 , 运行结果：" + solution.longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
        System.out.println("预期结果：5 , 运行结果：" + solution.longestStrChain(new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"}));
        System.out.println("预期结果：1 , 运行结果：" + solution.longestStrChain(new String[]{"abcd","dbqca"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n×m×(logn+m))，其中 n 表示字符串数组的长度，m 表示每个字符串的平均长度。首选对字符串数组进行排序，需要的时间为 O(n×m×logn)，然后遍历每个字符串，并对每个字符串都生成其「前身」字符串，需要的时间为 O(n×m^2)，因此总的时间复杂度为 O(n×m×(logn+m))。
        // 空间复杂度：O(n×m)，其中 n 表示字符串数组的长度，m 表示每个字符串的平均长度。需要存储以每个字符串为结尾的最大链的长度，一共有 n 个字符串，每个字符串的平均长度为 m，因此需要的空间为 O(n×m)。
        public int longestStrChain(String[] words) {
            Arrays.sort(words, Comparator.comparingInt(String::length));
            //Arrays.sort(words, (a, b) -> a.length() - b.length());
            HashMap<String, Integer> map = new HashMap<>();
            int ans = 0;
            for(String word : words){
                map.put(word, 1);
                for(int i = 0; i < word.length(); i++){
                    String key = word.substring(0, i) + word.substring(i + 1);
                    if(map.containsKey(key)){
                        map.put(word, Math.max(map.get(word), map.get(key) + 1));
                    }
                }
                ans = Math.max(ans, map.get(word));
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}