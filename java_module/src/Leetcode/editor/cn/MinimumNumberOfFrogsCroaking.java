package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-05-06 11:34:57
 * [1419] - 数青蛙
 * MinimumNumberOfFrogsCroaking.md
 */
 
//给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以croakOfFrogs 中会混合多个 “croak” 。
// 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
// 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。 
//
// 示例 1： 
//输入：croakOfFrogs = "croakcroak"
//输出：1 
//解释：一只青蛙 “呱呱” 两次
//
// 示例 2： 
//输入：croakOfFrogs = "crcoakroak"
//输出：2 
//解释：最少需要两只青蛙，“呱呱” 声用黑体标注
//第一只青蛙 "crcoakroak"
//第二只青蛙 "crcoakroak"
//
// 示例 3： 
//输入：croakOfFrogs = "croakcrook"
//输出：-1
//解释：给出的字符串不是 "croak" 的有效组合。
//
// 提示： 
// 1 <= croakOfFrogs.length <= 10⁵ 
// 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k' 

public class MinimumNumberOfFrogsCroaking{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumNumberOfFrogsCroaking().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.minNumberOfFrogs("croakcroak"));
        System.out.println("预期结果：2 , 运行结果：" + solution.minNumberOfFrogs("crcoakroak"));
        System.out.println("预期结果：-1 , 运行结果：" + solution.minNumberOfFrogs("croakcrook"));
        System.out.println("预期结果：2 , 运行结果：" + solution.minNumberOfFrogs("crcoakroakcrcoakroak"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 为 croakOfFrogs 的长度。
        // 空间复杂度：O(∣Σ∣)，其中 ∣Σ∣ 为字符集合的大小，若采用哈希表实现，则 ∣Σ∣=5。
        
        // 数组比哈希表快。's' 保证 "croak" 中的任意字符都不会越界
        private final char[] PREVIOUS = new char['s'];
        
        // 预处理每个字母在 "croak" 中的上一个字母
        //static {
        //    char[] s = new char[]{'c', 'r', 'o', 'a', 'k','c'};
        //    for(int i = 1; i < s.length; i++){
        //        PREVIOUS[s[i]] = s[i - 1];
        //    }
        //}
        
        public int minNumberOfFrogs(String croakOfFrogs) {
            char[] s = new char[]{'c', 'r', 'o', 'a', 'k', 'c'};
            for(int i = 1; i < s.length; i++){
                PREVIOUS[s[i]] = s[i - 1];
            }
            
            int[] cnt = new int['s'];
            for(char ch : croakOfFrogs.toCharArray()){
                // pre 为 ch 在 "croak" 中的上一个字母
                char pre = PREVIOUS[ch];
                // 如果有青蛙发出了 pre 的声音
                if(cnt[pre] > 0){
                    cnt[pre]--; // 复用一只
                }else if(ch != 'c'){ // 否则青蛙必须从 'c' 开始蛙鸣
                    return -1;
                }
                // 发出了 ch 的声音
                cnt[ch]++;
            }
            
            //遍历完，如果有发出其它声音的青蛙，不符合要求
            //if(cnt['c'] > 0 || cnt['r'] > 0 || cnt['o'] > 0 || cnt['a'] > 0){
            //    return -1;
            //}
            // 最后青蛙们都发出了 'k' 的声音
            //return cnt['k'];
            return (cnt['c'] > 0 || cnt['r'] > 0 || cnt['o'] > 0 || cnt['a'] > 0) ? -1 : cnt['k'];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}