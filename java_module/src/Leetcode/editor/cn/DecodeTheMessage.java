package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-02-01 09:13:36
 * [2325] - 解密消息
 * DecodeTheMessage.md
 */
 
//给你字符串 key 和 message ，分别表示一个加密密钥和一段加密消息。解密 message 的步骤如下： 
// 
// 使用 key 中 26 个英文小写字母第一次出现的顺序作为替换表中的字母 顺序 。 
// 将替换表与普通英文字母表对齐，形成对照表。 
// 按照对照表 替换 message 中的每个字母。 
// 空格 ' ' 保持不变。 
// 
// 例如，key = "happy boy"（实际的加密密钥会包含字母表中每个字母 至少一次），据此，可以得到部分对照表（'h' -> 'a'、'a' ->'b'、'p' -> 'c'、'y' -> 'd'、'b' -> 'e'、'o' -> 'f'）。 
// 返回解密后的消息。 
//
// 示例 1： 
//输入：key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs tsuepuv"
//输出："this is a secret"
//解释：对照表如上图所示。
//提取 "the quick brown fox jumps over the lazy dog" 中每个字母的首次出现可以得到替换表。
//
// 示例 2： 
//输入：key = "eljuxhpwnyrdgtqkviszcfmabo", message = "zwx hnfx lqantp mnoeiusycgk vcnjrdb"
//输出："the five boxing wizards jump quickly"
//解释：对照表如上图所示。
//提取 "eljuxhpwnyrdgtqkviszcfmabo" 中每个字母的首次出现可以得到替换表。
//
// 提示： 
// 26 <= key.length <= 2000 
// key 由小写英文字母及 ' ' 组成 
// key 包含英文字母表中每个字符（'a' 到 'z'）至少一次 
// 1 <= message.length <= 2000 
// message 由小写英文字母和 ' ' 组成 

public class DecodeTheMessage{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new DecodeTheMessage().new Solution();
        System.out.println("预期结果：this is a secret , 运行结果：" + solution.decodeMessage2("the quick brown fox jumps over the lazy dog", "vkbs bs tsuepuv"));
        System.out.println("预期结果：the five boxing wizards jump quickly , 运行结果：" + solution.decodeMessage2("eljuxhpwnyrdgtqkviszcfmabo", "zwx hnfx lqantp mnoeius ycgk vcnjrdb"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接模拟
        // 复杂度分析
        //时间复杂度：O(m+n)，其中 m 和 n 分别是字符串 key 和 message 的长度。
        //空间复杂度：O(∣Σ∣) 或者 O(n+∣Σ∣)，其中 Σ 是字符集，在本题中 ∣Σ∣=26。哈希表需要使用 O(∣Σ∣) 的空间。
        // 此外，如果我们使用的语言不能对字符串进行修改，就需要额外 O(n) 的空间存储答案的临时表示；否则可以在给定的字符串 message 上进行修改。
        public String decodeMessage(String key, String message) {
            int[] map = new int[26];
            Arrays.fill(map, -1);
            for(int i = 0, j = 0; i < key.length() && j < 26; i++){
                int idx = key.charAt(i) - 'a';
                if(idx != -65 && map[idx] == -1){
                    map[idx] = j++;
                }             
            }
            StringBuilder ans = new StringBuilder();
            for(char c : message.toCharArray()){
                ans.append(c == ' ' ? ' ' : (char)('a' + map[c - 'a']));
            }
            return ans.toString();    
        }
        
        //直接用char数组 
        public String decodeMessage2(String key, String message) {
            char start = 'a' , map[] = new char[26];
            for(int i = 0; i < key.length() && start <= 'z'; i++){
                char c = key.charAt(i);
                if(c != ' ' && map[c - 'a'] == 0){
                    map[c - 'a'] = start++;
                }
            }
            StringBuilder ans = new StringBuilder();
            for(char c : message.toCharArray()){
                ans.append(c == ' ' ? ' ' : map[c - 'a']);
            }
            return ans.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}