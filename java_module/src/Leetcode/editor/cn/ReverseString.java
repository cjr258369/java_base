package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-08-07 15:23:20
 * [344] - 反转字符串
 * ReverseString.md
 */
 
//编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。 
// 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。 
//
// 示例 1： 
//输入：s = ["h","e","l","l","o"]
//输出：["o","l","l","e","h"]
//
// 示例 2： 
//输入：s = ["H","a","n","n","a","h"]
//输出：["h","a","n","n","a","H"] 
//
// 提示： 
// 1 <= s.length <= 10⁵ 
// s[i] 都是 ASCII 码表中的可打印字符 

public class ReverseString{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ReverseString().new Solution();
        char[] s1 = new char[]{'h','e','l','l','o'};
        char[] s2 = new char[]{'H','a','n','n','a','h'};
        solution.reverseString(s1);
        solution.reverseString(s2);
        System.out.println("预期结果：[o,l,l,e,h] , 运行结果：" + Arrays.toString(s1));
        System.out.println("预期结果：[h,a,n,n,a,H] , 运行结果：" + Arrays.toString(s2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //双指针：复杂度分析
        // 时间复杂度：O(N)，其中 N 为字符数组的长度。一共执行了 N/2 次的交换。
        // 空间复杂度：O(1)。只使用了常数空间来存放若干变量。
        public void reverseString(char[] s) {
            for(int i = 0, j = s.length - 1; i < j; i++, j--){
                char temp = s[i];
                s[i] = s[j];
                s[j] = temp;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}