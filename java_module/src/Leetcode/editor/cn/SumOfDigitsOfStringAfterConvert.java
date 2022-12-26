package Leetcode.editor.cn;


/**
 * 2022-12-15 13:48:24
 * [1945] - 字符串转化后的各位数字之和
 * SumOfDigitsOfStringAfterConvert.md
 */
public class SumOfDigitsOfStringAfterConvert{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SumOfDigitsOfStringAfterConvert().new Solution();
        System.out.println("预期结果：36 , 运行结果：" + solution.getLucky("iiii", 1));
        System.out.println("预期结果：6 , 运行结果：" + solution.getLucky("leetcode", 2));
        System.out.println("预期结果：8 , 运行结果：" + solution.getLucky("zbax", 2));
        System.out.println("预期结果：1 , 运行结果：" + solution.getLucky("a", 1));
        
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int getLucky(String s, int k) {
            StringBuilder t = new StringBuilder();
            for(int i = 0; i < s.length(); i++){
                t.append(s.charAt(i) - 'a' + 1);
            }
            while(k-- > 0 && t.length() > 1){
                int sum = 0;
                for(int i = 0; i < t.length(); i++){
                    sum += t.charAt(i) - '0';
                }
                if(k < 0 && t.length() > 1) break;
                t = new StringBuilder(Integer.toString(sum));
            }
            return Integer.parseInt(t.toString());
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}