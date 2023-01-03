package Leetcode.editor.cn;

/**
 * 2023-01-03 10:43:33
 * [2351] - 第一个出现两次的字母
 * FirstLetterToAppearTwice.md
 */
 
//给你一个由小写英文字母组成的字符串 s ，请你找出并返回第一个出现 两次 的字母。 
//
// 注意： 如果 a 的 第二次 出现比 b 的 第二次 出现在字符串中的位置更靠前，则认为字母 a 在字母 b 之前出现两次。 
// s 包含至少一个出现两次的字母。 
//
// 示例 1： 
// 输入：s = "abccbaacz"
//输出："c"
//解释：
//字母 'a' 在下标 0 、5 和 6 处出现。
//字母 'b' 在下标 1 和 4 处出现。
//字母 'c' 在下标 2 、3 和 7 处出现。
//字母 'z' 在下标 8 处出现。
//字母 'c' 是第一个出现两次的字母，因为在所有字母中，'c' 第二次出现的下标是最小的。
// 
// 示例 2： 
// 输入：s = "abcdd"
//输出："d"
//解释：
//只有字母 'd' 出现两次，所以返回 'd' 。
// 
// 提示： 
// 2 <= s.length <= 100 
// s 由小写英文字母组成 

public class FirstLetterToAppearTwice{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FirstLetterToAppearTwice().new Solution();
        System.out.println("预期结果：c , 运行结果：" + solution.repeatedCharacter2("abccbaacz"));
        System.out.println("预期结果：d , 运行结果：" + solution.repeatedCharacter2("abcdd"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public char repeatedCharacter(String s) {
            int[] cnt = new int[26];
            for(char c : s.toCharArray()){
                if(++cnt[c - 'a'] >= 2){
                    return c;
                }
            }
            return ' ';    
        }
        
        //压缩数组所使用的空间，使用 一个 32 位的数字的二进制的每一位当做数组来存储，代表当前字符，如果是0则置为1，如果是1，则可以直接返回

        //方法二：状态压缩：压缩数组所使用的空间，使用 一个 32 位的数字的二进制的每一位当做数组来存储
        //如果 seen 的第 i (0 ≤ i < 26) 位是 1，说明第 i 个小写字母已经出现过。
        //具体地：直接对字符串 s 进行一次遍历。当遍历到字母 c 时，记它是第 i 个字母，seen 的第 i (0 ≤ i < 26) 位是 1，我们返回 c 即可；否则，我们将 seen 的第 i 位置为 1。
        public char repeatedCharacter2(String s) {
            int seen = 0;
            for(char c : s.toCharArray()){
                if((seen & (1 << (c - 'a'))) != 0){
                    return c;
                }
                seen |= (1 << (c - 'a'));
            }
            return ' ';
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}