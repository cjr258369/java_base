package Leetcode.editor.cn;

/**
 * 2022-11-29 15:19:58
 * [1758] - 生成交替二进制字符串的最少操作数
 * MinimumChangesToMakeAlternatingBinaryString.md
 */
public class MinimumChangesToMakeAlternatingBinaryString{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumChangesToMakeAlternatingBinaryString().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.minOperations2("0100"));
        System.out.println("预期结果：0 , 运行结果：" + solution.minOperations2("10"));
        System.out.println("预期结果：2 , 运行结果：" + solution.minOperations2("1111"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minOperations(String s) {
            //zero 代表 0 开头【010101】， one 代表 1 开头【101010】
            int zero = 0, one = 1, cnt0 = 0, cnt1 = 0;
            for(char c : s.toCharArray()){
                int val = c - '0';
                cnt0 += zero == val ? 0 : 1;
                cnt1 += one == val ? 0 : 1;
                if(zero == 0){
                    zero ^= one;
                    one ^= zero;
                }else{
                    one ^= zero;
                    zero ^= one;
                }
            }
            return Math.min(cnt0, cnt1);
        }

        //只需统计其中一种即可，因为变成这两种不同的交替二进制字符串所需要的最少操作数加起来等于 s 的长度，
        //因此只需要计算出变为其中一种字符串的最少操作数，就可以推出另一个最少操作数，然后取最小值即可
        public int minOperations2(String s) {
            int cnt0 = 0;
            for(int i = 0; i < s.length(); i++){
                //i % 2 的结果为：01010101，也即：偶奇偶奇偶奇偶奇
                cnt0 += ((i % 2) == (s.charAt(i) - '0')) ? 0 : 1;
            }
            return Math.min(cnt0, s.length() - cnt0);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}