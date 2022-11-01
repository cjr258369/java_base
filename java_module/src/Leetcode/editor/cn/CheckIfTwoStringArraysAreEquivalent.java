package Leetcode.editor.cn;

import java.util.stream.IntStream;

/**
 * 2022-11-01 09:07:25
 * [1662] - 检查两个字符串数组是否相等
 * CheckIfTwoStringArraysAreEquivalent.md
 */
public class CheckIfTwoStringArraysAreEquivalent{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CheckIfTwoStringArraysAreEquivalent().new Solution();
        System.out.println("预期结果：true  运行结果：" + solution.arrayStringsAreEqual(new String[]{"ab", "c"}, new String[]{"a", "bc"}));
        System.out.println("预期结果：false 运行结果：" + solution.arrayStringsAreEqual(new String[]{"a", "cb"}, new String[]{"ab", "c"}));
        System.out.println("预期结果：true  运行结果：" + solution.arrayStringsAreEqual(new String[]{"abc", "d", "defg"}, new String[]{"abcddefg"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("",word2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}