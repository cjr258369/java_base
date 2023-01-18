package Leetcode.editor.cn;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 2023-01-11 09:27:12
 * [2283] - 判断一个数的数字计数是否等于数位的值
 * CheckIfNumberHasEqualDigitCountAndDigitValue.md
 */
 
//给你一个下标从 0 开始长度为 n 的字符串 num ，它只包含数字。 
// 如果对于 每个 0 <= i < n 的下标 i ，都满足数位 i 在 num 中出现了 num[i]次，那么请你返回 true ，否则返回 false 。 
//
// 示例 1： 
// 输入：num = "1210"
//输出：true
//解释：
//num[0] = '1' 。数字 0 在 num 中出现了一次。
//num[1] = '2' 。数字 1 在 num 中出现了两次。
//num[2] = '1' 。数字 2 在 num 中出现了一次。
//num[3] = '0' 。数字 3 在 num 中出现了零次。
//"1210" 满足题目要求条件，所以返回 true 。
//
// 示例 2： 
// 输入：num = "030"
//输出：false
//解释：
//num[0] = '0' 。数字 0 应该出现 0 次，但是在 num 中出现了一次。
//num[1] = '3' 。数字 1 应该出现 3 次，但是在 num 中出现了零次。
//num[2] = '0' 。数字 2 在 num 中出现了 0 次。
//下标 0 和 1 都违反了题目要求，所以返回 false 。
//
// 提示： 
// n == num.length 
// 1 <= n <= 10 
// num 只包含数字。

public class CheckIfNumberHasEqualDigitCountAndDigitValue{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CheckIfNumberHasEqualDigitCountAndDigitValue().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.digitCount("1210"));
        System.out.println("预期结果：false , 运行结果：" + solution.digitCount("030"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean digitCount(String num) {
            int[] cnt = new int[10];
            char[] arr = num.toCharArray();
            for(char ch : arr){
                cnt[ch - '0']++;
            }
            for(int i = 0; i < arr.length; i++){
                if(cnt[i] != arr[i] - '0'){
                    return false;
                }
            }
            return true;
        }
        
        //纯一行写法，效率并不高
        public boolean digitCount2(String num) {
            return IntStream.range(0, num.length()).allMatch(i -> IntStream.range(0, num.length()).mapToObj(idx -> num.toCharArray()[idx]).filter(item -> item - '0' == i).collect(Collectors.toList()).size()==num.charAt(i)-'0');
        }
        
        //另一种不使用map，仅做一次遍历的思路，但会有很多重复的split，效率不高
        public boolean digitCount3(String num) {
            for(int i = 0; i < num.length(); i++){
                if(num.split("" + i).length - 1 != num.charAt(i) - '0'){
                    return false;
                }
            }
            return true;
        }
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}