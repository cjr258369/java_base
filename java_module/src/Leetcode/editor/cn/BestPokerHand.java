package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-02-20 09:06:50
 * [2347] - 最好的扑克手牌
 * BestPokerHand.md
 */
 
//给你一个整数数组 ranks 和一个字符数组 suit 。你有 5 张扑克牌，第 i 张牌大小为 ranks[i] ，花色为 suits[i] 。 
// 下述是从好到坏你可能持有的 手牌类型 ： 
// "Flush"：同花，五张相同花色的扑克牌。 
// "Three of a Kind"：三条，有 3 张大小相同的扑克牌。 
// "Pair"：对子，两张大小一样的扑克牌。 
// "High Card"：高牌，五张大小互不相同的扑克牌。 
//
// 请你返回一个字符串，表示给定的 5 张牌中，你能组成的 最好手牌类型 。注意：返回的字符串 大小写 需与题目描述相同。 
//
// 示例 1： 
// 输入：ranks = [13,2,3,1,9], suits = ["a","a","a","a","a"]
//输出："Flush"
//解释：5 张扑克牌的花色相同，所以返回 "Flush" 。
//
// 示例 2： 
// 输入：ranks = [4,4,2,4,4], suits = ["d","a","a","b","c"]
//输出："Three of a Kind"
//解释：第一、二和四张牌组成三张相同大小的扑克牌，所以得到 "Three of a Kind" 。
//注意我们也可以得到 "Pair" ，但是 "Three of a Kind" 是更好的手牌类型。
//有其他的 3 张牌也可以组成 "Three of a Kind" 手牌类型。 
//
// 示例 3： 
// 输入：ranks = [10,10,2,12,9], suits = ["a","b","c","a","d"]
//输出："Pair"
//解释：第一和第二张牌大小相同，所以得到 "Pair" 。
//我们无法得到 "Flush" 或者 "Three of a Kind" 。
//
// 提示： 
// ranks.length == suits.length == 5 
// 1 <= ranks[i] <= 13 
// 'a' <= suits[i] <= 'd' 
// 任意两张扑克牌不会同时有相同的大小和花色。 

public class BestPokerHand{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new BestPokerHand().new Solution();
        System.out.println("预期结果：Flush , 运行结果：" + solution.bestHand(new int[]{13,2,3,1,9}, new char[]{'a', 'a', 'a', 'a', 'a'}));
        System.out.println("预期结果：Three of a Kind , 运行结果：" + solution.bestHand(new int[]{4,4,2,4,4}, new char[]{'d', 'a', 'a', 'b', 'c'}));
        System.out.println("预期结果：Pair , 运行结果：" + solution.bestHand(new int[]{10,10,2,12,9}, new char[]{'a', 'b', 'c', 'a', 'd'}));
        System.out.println("预期结果：Three of a Kind , 运行结果：" + solution.bestHand(new int[]{12,12,12,9,9}, new char[]{'b', 'd', 'a', 'c', 'b'}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //哈希表 + 计数
        // 复杂度分析
        // 时间复杂度：O(n)，其中 n 为数组 ranks 的长度。
        // 空间复杂度：O(n)，其中 n 为数组 ranks 的长度，下面的写法为常量14，主要为哈希表的存储开销。

        public String bestHand(int[] ranks, char[] suits) {
            if(suits[0] == suits[1] && suits[0] == suits[2] && suits[0] == suits[3] && suits[0] == suits[4]){
                return "Flush";
            }
            int maxCount = 0, cnt[] = new int[14];
            for (int rank : ranks) {
                cnt[rank]++;
                maxCount = cnt[rank] > maxCount ? cnt[rank] : maxCount;
            }
            if(maxCount >= 3){
                return "Three of a Kind";                
            }else if(maxCount == 2){
                return "Pair";                
            }else{
                return "High Card";                
            }           
        }
        
        //仅变更写法，以便于阅读【98 / 98 ， 0 ms ，38.9 MB】
        public String bestHand2(int[] ranks, char[] suits) {
            if(suits[0] == suits[1] && suits[0] == suits[2] && suits[0] == suits[3] && suits[0] == suits[4]){
                return "Flush";
            }
            //maxCount 用于统计出现频次最高的牌
            int maxCount = 0, cnt[] = new int[14];
            for (int rank : ranks) {
                cnt[rank]++;
                maxCount = Math.max(cnt[rank], maxCount);
            }
            return maxCount >= 3 ? "Three of a Kind" : maxCount == 2 ? "Pair" : "High Card";
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}