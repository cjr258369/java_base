package Leetcode.editor.cn;

import java.util.PriorityQueue;

/**
 * 2022-11-08 11:49:02
 * [2462] - 雇佣 K 位工人的总代价
 * TotalCostToHireKWorkers.md
 */
public class TotalCostToHireKWorkers{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new TotalCostToHireKWorkers().new Solution();
        //System.out.println("预期结果：11 , 运行结果：" + solution.totalCost(new int[]{17,12,10,2,7,2,11,20,8}, 3, 4));
        System.out.println("预期结果：4 , 运行结果：" + solution.totalCost(new int[]{1,2,4,1}, 3, 3));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long totalCost(int[] costs, int k, int candidates) {
            PriorityQueue<Integer> left = new PriorityQueue<>(), right = new PriorityQueue<>();
            int i = 0, j = costs.length - 1;
            long sum = 0;
            while(left.size() < candidates){
                left.offer(costs[i++]);
            }
            while(right.size() < candidates){
                right.offer(j < i ? Integer.MAX_VALUE : costs[j--]);
            }
            while(k-- > 0){
                if(right.peek() < left.peek()){
                    sum += right.poll();
                    right.offer(j < i ? Integer.MAX_VALUE : costs[j--]);
                }else{
                    sum += left.poll();
                    left.offer(i > j ? Integer.MAX_VALUE : costs[i++]);
                }
            }
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}