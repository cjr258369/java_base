package Leetcode.editor.cn;

/**
 * 2022-12-28 09:08:19
 * [1750] - 删除字符串两端相同字符后的最短长度
 * MinimumLengthOfStringAfterDeletingSimilarEnds.md
 */
public class MinimumLengthOfStringAfterDeletingSimilarEnds{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumLengthOfStringAfterDeletingSimilarEnds().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.minimumLength("ca"));
        System.out.println("预期结果：0 , 运行结果：" + solution.minimumLength("cabaabac"));
        System.out.println("预期结果：3 , 运行结果：" + solution.minimumLength("aabccabba"));
        System.out.println("预期结果：3 , 运行结果：" + solution.minimumLength("cab"));
        System.out.println("预期结果：0 , 运行结果：" + solution.minimumLength("aaa"));
        System.out.println("预期结果：0 , 运行结果：" + solution.minimumLength("aa"));
        System.out.println("预期结果：1 , 运行结果：" + solution.minimumLength("aaaaabcccacbba"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumLength(String s) {
            int left = 0, right = s.length() - 1;
            while(left < right && s.charAt(left) == s.charAt(right)){
                char c = s.charAt(left);
                while(left <= right && c == s.charAt(left)){
                    left++;
                }
                while(left <= right && c == s.charAt(right)){
                    right--;
                }
            }
            return right - left + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}