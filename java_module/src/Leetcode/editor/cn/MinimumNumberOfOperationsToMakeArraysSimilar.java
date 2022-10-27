package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-10-27 10:12:38
 * [2449] - 使数组相似的最少操作次数
 * MinimumNumberOfOperationsToMakeArraysSimilar.md
 */
public class MinimumNumberOfOperationsToMakeArraysSimilar{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumNumberOfOperationsToMakeArraysSimilar().new Solution();
        System.out.println("运行结果：" + solution.makeSimilar(new int[]{8,12,6}, new int[]{2,14,10}));
        System.out.println("运行结果：" + solution.makeSimilar(new int[]{1,2,5}, new int[]{4,1,3}));
        System.out.println("运行结果：" + solution.makeSimilar(new int[]{1,1,1,1,1}, new int[]{1,1,1,1,1}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long makeSimilar(int[] nums, int[] target) {
            //因为 + 2 奇偶不变，所以按奇偶分组，并排序
            //因为全为正数，所以将奇数改为负数，排序，那么所有奇数都会排在前面，偶数排在后面
            //数A 变为 数B ，则 两者差 除以2 就是步数了，但每次都要操作 两个数，所以结尾把变化量的和除以 4
            sort(nums);
            sort(target);
            
            long ans = 0;
            for(int i = 0; i < nums.length; i++){
                ans += Math.abs(nums[i] - target[i]);
            }
            return ans / 4;    
        }
        
        void sort(int[] nums){
            for(int i = 0; i < nums.length; i++){
                if(nums[i] % 2 != 0){
                    nums[i] = -nums[i];
                }
            }
            Arrays.sort(nums);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}