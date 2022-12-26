package Leetcode.editor.cn;

/**
 * 2022-12-16 17:12:02
 * [1785] - 构成特定和需要添加的最少元素
 * MinimumElementsToAddToFormAGivenSum.md
 */
public class MinimumElementsToAddToFormAGivenSum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumElementsToAddToFormAGivenSum().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.minElements(new int[]{1,-1,1}, 3, -4));
        System.out.println("预期结果：1 , 运行结果：" + solution.minElements(new int[]{1,-10,9,1}, 100, 0));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minElements(int[] nums, int limit, int goal) {
            long sum = 0L;
            for(int n : nums){
                sum += n;
            }
            long diff = Math.abs(goal - sum);
            return (int)((diff + limit - 1) / limit);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}