package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 2022-12-20 10:39:20
 * [1760] - 袋子里最少数目的球
 * MinimumLimitOfBallsInABag.md
 */
public class MinimumLimitOfBallsInABag{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumLimitOfBallsInABag().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.minimumSize(new int[]{9}, 2));
        System.out.println("预期结果：2 , 运行结果：" + solution.minimumSize(new int[]{2,4,8,2}, 4));
        System.out.println("预期结果：7 , 运行结果：" + solution.minimumSize(new int[]{7,17}, 2));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumSize(int[] nums, int maxOperations) {
            Arrays.sort(nums);
            int left = 1, right = nums[nums.length - 1], ans = 0;
            while(left <= right){
                int mid = (right + left) / 2;
                long sum = 0;
                for(int n : nums) sum += (n-1)/mid;
                if(sum <= maxOperations){
                    ans = mid;
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }                                                                                                                                                                                       
            }
            return ans;    
        }
        
        //无需排序，因为答案具有单调性
        public int minimumSize2(int[] nums, int maxOperations) {
            int ans = 0, left = 1, right = IntStream.of(nums).max().getAsInt();
            //int ans = 0, left = 1, right = Arrays.stream(nums).max().getAsInt();
            while(left <= right){
                int mid = (right + left) / 2;
                long sum = 0;
                for(int n : nums) sum += (n-1)/mid;
                if(sum <= maxOperations){
                    ans = mid;
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }                                                                                                                                                                                       
            }
            return ans;    
        }       
    }
    //leetcode submit region end(Prohibit modification and deletion)

}