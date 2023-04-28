package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-04-24 10:10:40
 * [2653] - 滑动子数组的美丽值
 * SlidingSubarrayBeauty.md
 */
 
//给你一个长度为 n 的整数数组 nums ，请你求出每个长度为 k 的子数组的 美丽值 。
// 一个子数组的 美丽值 定义为：如果子数组中第 x 小整数 是 负数 ，那么美丽值为第 x 小的数，否则美丽值为 0 。
// 请你返回一个包含 n - k + 1 个整数的数组，依次 表示数组中从第一个下标开始，每个长度为 k 的子数组的 美丽值 。
// 子数组指的是数组中一段连续 非空 的元素序列。 
//
// 示例 1： 
// 输入：nums = [1,-1,-3,-2,3], k = 3, x = 2
//输出：[-1,-2,-2]
//解释：总共有 3 个 k = 3 的子数组。
//第一个子数组是 [1, -1, -3] ，第二小的数是负数 -1 。
//第二个子数组是 [-1, -3, -2] ，第二小的数是负数 -2 。
//第三个子数组是 [-3, -2, 3] ，第二小的数是负数 -2 。 
//
// 示例 2： 
// 输入：nums = [-1,-2,-3,-4,-5], k = 2, x = 2
//输出：[-1,-2,-3,-4]
//解释：总共有 4 个 k = 2 的子数组。
//[-1, -2] 中第二小的数是负数 -1 。
//[-2, -3] 中第二小的数是负数 -2 。
//[-3, -4] 中第二小的数是负数 -3 。
//[-4, -5] 中第二小的数是负数 -4 。 
//
// 示例 3： 
// 输入：nums = [-3,1,2,-3,0,-3], k = 2, x = 1
//输出：[-3,0,-3,-3,-3]
//解释：总共有 5 个 k = 2 的子数组。
//[-3, 1] 中最小的数是负数 -3 。
//[1, 2] 中最小的数不是负数，所以美丽值为 0 。
//[2, -3] 中最小的数是负数 -3 。
//[-3, 0] 中最小的数是负数 -3 。
//[0, -3] 中最小的数是负数 -3 。 
//
// 提示： 
// n == nums.length 
// 1 <= n <= 10⁵ 
// 1 <= k <= n 
// 1 <= x <= k 
// -50 <= nums[i] <= 50 

public class SlidingSubarrayBeauty{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SlidingSubarrayBeauty().new Solution();
        System.out.println("预期结果：[-1,-2,-2] , 运行结果：" + Arrays.toString(solution.getSubarrayBeauty(new int[]{1, -1, -3, -2, 3}, 3, 2)));
        System.out.println("预期结果：[-1,-2,-3,-4] , 运行结果：" + Arrays.toString(solution.getSubarrayBeauty(new int[]{-1, -2, -3, -4, -5}, 2, 2)));
        System.out.println("预期结果：[-3,0,-3,-3,-3] , 运行结果：" + Arrays.toString(solution.getSubarrayBeauty(new int[]{-3, 1, 2, -3, 0, -3}, 2, 1)));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] getSubarrayBeauty(int[] nums, int k, int x) {
            int n = nums.length, ans[] = new int[n - k + 1], cnt[] = new int[101];
            for(int i = 0; i < n; i++){
                cnt[nums[i] + 50]++;
                if(i >= k - 1){
                    //j < 50 避免了正数
                    for(int j = 0, count = 0; j < 50 && count < x; j++){
                        if((count += cnt[j]) >= x){
                            ans[i - k + 1] = j - 50;
                        }
                    }
                    cnt[nums[i - k + 1] + 50]--;
                }                
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}