package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 2022-12-07 11:13:15
 * [1775] - 通过最少操作次数使数组的和相等
 * EqualSumArraysWithMinimumNumberOfOperations.md
 */
public class EqualSumArraysWithMinimumNumberOfOperations{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new EqualSumArraysWithMinimumNumberOfOperations().new Solution();
        System.out.println("预期结果： 3, 运行结果：" + solution.minOperations(new int[]{1,2,3,4,5,6}, new int[]{1,1,2,2,2,2}));
        System.out.println("预期结果： -1, 运行结果：" + solution.minOperations(new int[]{1,1,1,1,1,1,1}, new int[]{6}));
        System.out.println("预期结果： 3, 运行结果：" + solution.minOperations(new int[]{6,6}, new int[]{1}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接处理变化量
        public int minOperations(int[] nums1, int[] nums2) {
            HashMap<String, Integer> map = new HashMap<>();
            int sum1 = Arrays.stream(nums1).sum(), sum2 = Arrays.stream(nums2).sum();
            if(sum1 > sum2){
                return minOperations(nums2, nums1);
            }
            int ans = 0, d = sum2 - sum1, cnt[] = new int[6];
            for(int n : nums1){
                cnt[6 - n]++;
            }
            for(int n : nums2){
                cnt[n - 1]++;
            }
            for(int i = 5; i >= 0; i--){
                while(cnt[i] > 0 && d > 0){
                    d -= i;
                    cnt[i]--;
                    ans++;
                }
            }
            return d <= 0 ? ans : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}