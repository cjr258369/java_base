package Leetcode.editor.cn;

import java.util.HashSet;

/**
 * 2023-05-19 10:20:52
 * [1079] - 活字印刷
 * LetterTilePossibilities.md
 */
 
//你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。 注意：本题中，每个活字字模只能使用一次。 
//
// 示例 1： 
//输入："AAB"
//输出：8
//解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
//
// 示例 2： 
//输入："AAABBC"
//输出：188
//
// 示例 3： 
//输入："V"
//输出：1 
//
// 提示： 
// 1 <= tiles.length <= 7 
// tiles 由大写英文字母组成 

public class LetterTilePossibilities{
    //经典的回溯全排列
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new LetterTilePossibilities().new Solution();
        System.out.println("预期结果：8 , 运行结果：" + solution.numTilePossibilities("AAB"));
        System.out.println("预期结果：188 , 运行结果：" + solution.numTilePossibilities("AAABBC"));
        System.out.println("预期结果：1 , 运行结果：" + solution.numTilePossibilities("V"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //经典的回溯，输出全排列
        
        //复杂度分析：在最差情况下，所有字符互不相同，总共有 n! 个结果，对应 dfs 有 n! 个状态，每个状态有 O(n) 的搜索复杂度。
        //时间复杂度：O(n×n!)，其中 n 表示 tiles 长度的最小值。
        // 空间复杂度：O(n)， 其中 n 表示字符集大小和 tiles 长度的最小值。
        HashSet<String> set;
        char[] tiles;
        public int numTilePossibilities(String tiles) {
            set = new HashSet<>();
            this.tiles = tiles.toCharArray();
            dfs(new StringBuilder());
            return set.size() - 1;
        }
        
        private void dfs(StringBuilder sb){
            set.add(sb.toString());
            for(int i = 0; i < tiles.length; i++){
                if(tiles[i] != '0'){
                    char c = tiles[i];
                    tiles[i] = '0';
                    sb.append(c);
                    dfs(sb);
                    sb.deleteCharAt(sb.length() - 1);
                    tiles[i] = c;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}