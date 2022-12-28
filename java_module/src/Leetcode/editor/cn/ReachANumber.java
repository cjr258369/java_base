package Leetcode.editor.cn;

/**
 * 2022-11-04 09:14:56
 * [754] - 到达终点数字
 * ReachANumber.md
 */
public class ReachANumber{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ReachANumber().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.reachNumber(2));
        System.out.println("预期结果：2 , 运行结果：" + solution.reachNumber(3));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reachNumber(int target) {
            target = target > 0 ? target : - target;
            int step = 0;
            while(target > 0){
                step++;
                target -= step;
            }
            return target % 2 == 0 ? step : step + 1 + step % 2;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}