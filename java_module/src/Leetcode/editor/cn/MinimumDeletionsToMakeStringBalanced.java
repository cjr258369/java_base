package Leetcode.editor.cn;

/**
 * 2023-03-06 15:27:21
 * [1653] - 使字符串平衡的最少删除次数
 * MinimumDeletionsToMakeStringBalanced.md
 */
 
//给你一个字符串 s ，它仅包含字符 'a' 和 'b' 。你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
// 请你返回使 s 平衡 的 最少 删除次数。 
//
// 示例 1： 
//输入：s = "aababbab"
//输出：2
//解释：你可以选择以下任意一种方案：
//下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
//下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
//
// 示例 2： 
//输入：s = "bbaaaaabb"
//输出：2
//解释：唯一的最优解是删除最前面两个字符。
//
// 提示： 
// 1 <= s.length <= 10⁵ 
// s[i] 要么是 'a' 要么是 'b' 。 

public class MinimumDeletionsToMakeStringBalanced{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumDeletionsToMakeStringBalanced().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.minimumDeletions("aababbab"));
        System.out.println("预期结果：2 , 运行结果：" + solution.minimumDeletions("bbaaaaabb"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //通过删除部分字符串，使得字符串达到下列三种情况之一，即为平衡状态：
        // 字符串全为 “a”；
        // 字符串全为 “b”；
        // 字符串既有 “a” 也有 “b”，且所有 “a” 都在所有 “a” 左侧。
        
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 是 s 的长度。需要遍历两遍 s，第一遍计算出 s 中 “a” 的数量，第二遍遍历所有的间隔，求出最小需要删除的字符数。
        // 空间复杂度：O(1)，只需要常数空间。
        public int minimumDeletions(String s) {
            int cnt = 0;
            char[] arr = s.toCharArray();
            for(char c : arr){
                //统计 a 出现的次数
                cnt += 'b' - c;
            }
            int min = cnt;
            for(char c : arr){
                //直接运算会CPU 不需要执行 50% 命中率的分支，会更快
                //cnt += c == 'a' ? -1 : 1;
                cnt += (c - 'a') * 2 - 1;
                min = Math.min(cnt, min);
            }
            return min;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}