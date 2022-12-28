package Leetcode.editor.cn;

/**
 * 2022-11-03 09:21:58
 * [1668] - 最大重复子字符串
 * MaximumRepeatingSubstring.md
 */
public class MaximumRepeatingSubstring{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumRepeatingSubstring().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.maxRepeating("ababc", "ab"));
        System.out.println("预期结果：1 , 运行结果：" + solution.maxRepeating("ababc", "ba"));
        System.out.println("预期结果：0 , 运行结果：" + solution.maxRepeating("ababc", "ac"));
        System.out.println("预期结果：3 , 运行结果：" + solution.maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba","aaaba"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxRepeating(String sequence, String word) {
            int ans = 0;
            //JDK 13 String 才有repeat函数
            //while(sequence.contains(word.repeat(ans))){
            //    ans++;
            //}
            //return ans - 1;
            while(sequence.contains(word)){
                word += word;
                ans++;
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}