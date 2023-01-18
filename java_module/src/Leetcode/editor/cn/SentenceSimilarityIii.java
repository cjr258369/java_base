package Leetcode.editor.cn;

/**
 * 2023-01-16 09:36:52
 * [1813] - 句子相似性 III
 * SentenceSimilarityIii.md
 */
 
//一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，"Hello World" ，"HELLO" ，"hello world hello world" 都是句子。每个单词都 只 包含大写和小写英文字母。 
//
// 如果两个句子 sentence1 和 sentence2 ，可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，那么我们称这两个句子是 相似的 。比方说，sentence1 = "Hello my name is Jane" 且 sentence2 = "Hello Jane" ，我们可以往
// sentence2 中 "Hello" 和 "Jane" 之间插入 "my name is" 得到 sentence1 。 
//
// 给你两个句子 sentence1 和 sentence2 ，如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回false 。 
//
// 示例 1： 
// 输入：sentence1 = "My name is Haley", sentence2 = "My Haley"
//输出：true
//解释：可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
//
// 示例 2： 
// 输入：sentence1 = "of", sentence2 = "A lot of words"
//输出：false
//解释：没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
//
// 示例 3： 
// 输入：sentence1 = "Eating right now", sentence2 = "Eating"
//输出：true
//解释：可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
// 
// 示例 4： 
// 输入：sentence1 = "Luky", sentence2 = "Lucccky"
//输出：false
//
// 提示： 
// 1 <= sentence1.length, sentence2.length <= 100 
// sentence1 和 sentence2 都只包含大小写英文字母和空格。 
// sentence1 和 sentence2 中的单词都只由单个空格隔开。 

public class SentenceSimilarityIii{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SentenceSimilarityIii().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.areSentencesSimilar("My name is Haley", "My Haley"));
        System.out.println("预期结果：false , 运行结果：" + solution.areSentencesSimilar("of", "A lot of words"));
        System.out.println("预期结果：true , 运行结果：" + solution.areSentencesSimilar("Eating right now", "Eating"));
        System.out.println("预期结果：false , 运行结果：" + solution.areSentencesSimilar("Luky", "Lucccky"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(m+n)，其中 m 是 sentence1的长度，n 是 sentence2的长度。字符串分割和双指针判断操作都需要遍历 O(m+n) 个字符。
        // 空间复杂度：O(m+n)。字符串分割需要 O(m+n) 的空间，双指针消耗 O(1) 空间。
        public boolean areSentencesSimilar(String sentence1, String sentence2) {
            String[] s1 = sentence1.split(" "), s2 = sentence2.split(" ");
            int i = 0, j = 0;
            while(i < s1.length && i < s2.length && s1[i].equals(s2[i])){
                i++;
            }
            while(j < s1.length - i && j < s2.length - i && s1[s1.length - 1 - j].equals(s2[s2.length - 1 - j])){
                j++;
            }
            return i + j == Math.min(s1.length, s2.length);    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}