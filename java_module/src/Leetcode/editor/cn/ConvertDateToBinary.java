package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 2025-01-01 18:43:12
 * [3280] - 将日期转换为二进制表示
 * ConvertDateToBinary.md
 */

//给你一个字符串 date，它的格式为 yyyy-mm-dd，表示一个公历日期。  date 可以重写为二进制表示，只需要将年、月、日分别转换为对应的二进制表示（不带前导零）并遵循 year-month-day 的格式。 
// 返回 date 的 二进制 表示。 
//
// 示例 1： 
// 输入： date = "2080-02-29" 
// 输出： "100000100000-10-11101" 
//
// 解释： 
// 100000100000, 10 和 11101 分别是 2080, 02 和 29 的二进制表示。 
//
// 示例 2： 
// 输入： date = "1900-01-01" 
// 输出： "11101101100-1-1" 
//
// 解释： 
// 11101101100, 1 和 1 分别是 1900, 1 和 1 的二进制表示。 
//
// 提示： 
// date.length == 10 
// date[4] == date[7] == '-'，其余的 date[i] 都是数字。 
// 输入保证 date 代表一个有效的公历日期，日期范围从 1900 年 1 月 1 日到 2100 年 12 月 31 日（包括这两天）。 
 
public class ConvertDateToBinary{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ConvertDateToBinary().new Solution();
        //方法1
        System.out.println("预期结果：100000100000-10-11101 , 运行结果：" + solution.convertDateToBinary("2080-02-29"));
        System.out.println("预期结果：11101101100-1-1 , 运行结果：" + solution.convertDateToBinary("1900-01-01"));
        
        System.out.println("方法一耗时：" + (System.currentTimeMillis() - start) + " ms");

        //方法2
        start = System.currentTimeMillis();
        System.out.println("预期结果：100000100000-10-11101 , 运行结果：" + solution.convertDateToBinary2("2080-02-29"));
        System.out.println("预期结果：11101101100-1-1 , 运行结果：" + solution.convertDateToBinary2("1900-01-01"));
        
        System.out.println("方法二耗时：" + (System.currentTimeMillis() - start) + " ms");

        //方法3
        start = System.currentTimeMillis();
        System.out.println("预期结果：100000100000-10-11101 , 运行结果：" + solution.convertDateToBinary3("2080-02-29"));
        System.out.println("预期结果：11101101100-1-1 , 运行结果：" + solution.convertDateToBinary3("1900-01-01"));
        
        System.out.println("方法三耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    
    class Solution {

        //复杂度分析：【因为入参数据格式固定，因此无论如何遍历，也是常数】
        // 时间复杂度：O(1)。
        // 空间复杂度：O(1)。

        //方法一：较慢，最优雅
        public String convertDateToBinary(String date) {
            return Arrays.stream(date.split("-"))
                    .map(Integer::parseInt)
                    .map(Integer::toBinaryString)
                    .collect(Collectors.joining("-"));
        }

        //方法二：使用了StringBuilder，所以较快
        public String convertDateToBinary2(String date) {
            StringBuilder res = new StringBuilder();
            for(String s : date.split("-")){
                res.append(Integer.toBinaryString(Integer.parseInt(s))).append("-");
            }
            return res.substring(0, res.length() - 1);
        }
        
        //方法三：直接硬编码打表，速度最快
        public String convertDateToBinary3(String date) {
            StringBuilder res = new StringBuilder();
            res.append(Integer.toBinaryString(Integer.parseInt(date.substring(0,3))))
                    .append("-")
                    .append(Integer.toBinaryString(Integer.parseInt(date.substring(5,7))))
                    .append("-")
                    .append(Integer.toBinaryString(Integer.parseInt(date.substring(8,10))));
            return res.toString();
        }        
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}