package Leetcode.editor.cn;

/**
 * 2023-03-09 09:16:53
 * [2379] - 得到 K 个黑块的最少涂色次数
 * MinimumRecolorsToGetKConsecutiveBlackBlocks.md
 */
 
//给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和 'B' 分别表示白色和黑色。 
// 给你一个整数 k ，表示想要 连续 黑色块的数目。 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。 
// 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。 
//
// 示例 1： 
//输入：blocks = "WBBWWBBWBW", k = 7
//输出：3
//解释：
//一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。得到 blocks = "BBBBBBBWBW" 。
//可以证明无法用少于 3 次操作得到 7 个连续的黑块。
//所以我们返回 3 。
//
// 示例 2： 
//输入：blocks = "WBWBBBW", k = 2
//输出：0
//解释：
//不需要任何操作，因为已经有 2 个连续的黑块。
//所以我们返回 0 。
//
// 提示： 
// n == blocks.length 
// 1 <= n <= 100 
// blocks[i] 要么是 'W' ，要么是 'B' 。 
// 1 <= k <= n 

public class MinimumRecolorsToGetKConsecutiveBlackBlocks{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumRecolorsToGetKConsecutiveBlackBlocks().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.minimumRecolors("WBBWWBBWBW", 7));
        System.out.println("预期结果：0 , 运行结果：" + solution.minimumRecolors("WBWBBBW", 2));
        System.out.println("预期结果：3 , 运行结果：" + solution.minimumRecolors("BWWWBB", 6));
        System.out.println("预期结果：6 , 运行结果：" + solution.minimumRecolors("WWBBBWBBBBBWWBWWWB", 16));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接使用滑动窗口
        //复杂度分析
        // 时间复杂度：O(n)，其中 n 为字符串 blocks 的长度。
        // 空间复杂度：O(1)，仅使用常量空间。
        public int minimumRecolors(String blocks, int k) {
            int ans = Integer.MAX_VALUE;
            for(int left = 0, right = 0, cnt = 0; right < blocks.length(); right++){
                cnt += blocks.charAt(right) == 'W' ? 1 : 0;
                if(right < k){
                    ans = cnt;
                }else {
                    cnt -= blocks.charAt(left++) == 'W' ? 1 : 0;
                    ans = Math.min(ans, cnt);
                }
                //System.out.println("left = " + left + " , right = " + right + " , ans = " + ans + " , cnt = " + cnt);
            }
            return ans;            
        }
        
        public int minimumRecolors2(String blocks, int k) {
            int ans = Integer.MAX_VALUE;
            for(int left = 0, right = 0, cnt = 0; right < blocks.length(); right++){
                if(right < k){
                    ans = cnt += blocks.charAt(right) == 'W' ? 1 : 0;
                }else {
                    cnt += blocks.charAt(right) == 'W' ? 1 : 0;
                    cnt -= blocks.charAt(left++) == 'W' ? 1 : 0;
                    ans = Math.min(ans, cnt);
                }
                //System.out.println("left = " + left + " , right = " + right + " , ans = " + ans + " , cnt = " + cnt);
            }
            return ans;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
    
}