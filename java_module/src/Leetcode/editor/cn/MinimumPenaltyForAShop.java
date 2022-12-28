package Leetcode.editor.cn;

/**
 * 2022-12-01 15:26:14
 * [2483] - 商店的最少代价
 * MinimumPenaltyForAShop.md
 */
public class MinimumPenaltyForAShop{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumPenaltyForAShop().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.bestClosingTime("YYNY"));
        System.out.println("预期结果：0 , 运行结果：" + solution.bestClosingTime("NNNNN"));
        System.out.println("预期结果：4 , 运行结果：" + solution.bestClosingTime("YYYY"));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int bestClosingTime(String customers) {
            int closeTime = customers.length();
            for(int i = customers.length() - 1, sum = 0, max = 0; i >= 0; i--){
                if('N' == customers.charAt(i)){
                    sum++;
                    if(sum >= max){
                        max = sum;
                        closeTime = i;
                    }
                }else{
                    sum--;
                }
            }
            return closeTime;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}