package Leetcode.editor.cn;

/**
 * 2023-03-02 09:16:41
 * [面试题 05.02] - 二进制数转字符串
 * BianryNumberToStringLcci.md
 */
 
//二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。 
//
// 示例1: 
// 输入：0.625
// 输出："0.101"
//
// 示例2: 
// 输入：0.1
// 输出："ERROR"
// 提示：0.1无法被二进制准确表示
//
// 提示： 
// 32位包括输出中的 "0." 这两位。 
// 题目保证输入用例的小数位数最多只有 6 位 

public class BianryNumberToStringLcci{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new BianryNumberToStringLcci().new Solution();
        System.out.println("预期结果：0.101 , 运行结果：" + solution.printBin(0.625));
        System.out.println("预期结果：ERROR , 运行结果：" + solution.printBin(0.1));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String printBin(double num) {
            StringBuilder ans = new StringBuilder().append("0.");
            while(ans.length() <= 32 && num != 0){
                num *= 2;
                int digit = (int)num;
                ans.append(digit);
                num -= digit;
            }
            return ans.length() <= 32 ? ans.toString() : "ERROR";            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}