package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-12-23 14:30:40
 * [2011] - 执行操作后的变量值
 * FinalValueOfVariableAfterPerformingOperations.md
 */
public class FinalValueOfVariableAfterPerformingOperations{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FinalValueOfVariableAfterPerformingOperations().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.finalValueAfterOperations2(new String[]{"--X","X++","X++"}));
        System.out.println("预期结果：3 , 运行结果：" + solution.finalValueAfterOperations2(new String[]{"++X","++X","X++"}));
        System.out.println("预期结果：0 , 运行结果：" + solution.finalValueAfterOperations2(new String[]{"X++","++X","--X","X--"}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int finalValueAfterOperations(String[] operations) {
            int ans = 0;
            for(String operation : operations){
                ans += operation.contains("+") ? 1 : -1;
            }
            return ans;    
        }
        
        //效率最优
        public int finalValueAfterOperations2(String[] operations) {
            int ans = 0;
            for(String operation : operations){
                ans += operation.charAt(1) == '+' ? 1 : -1;
            }
            return ans;    
        }
        
        //效率最低
        public int finalValueAfterOperations3(String[] operations) {
            return (int)(Arrays.stream(operations).filter(s -> s.contains("+")).count() - Arrays.stream(operations).filter(s -> s.contains("-")).count());
        }
        
        //在 ASCII 中，+ 为 43，- 为 45，所以：44 - 43 = 1， 44 - 45 = -1，从而转成1，-1，然后直接求和
        public int finalValueAfterOperations4(String[] operations) {
            return Arrays.stream(operations)
                    .mapToInt(s -> 44 - s.charAt(1))
                    .sum();
        }
        
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}