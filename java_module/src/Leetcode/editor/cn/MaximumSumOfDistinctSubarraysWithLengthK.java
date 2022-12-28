package Leetcode.editor.cn;

import java.util.HashMap;

/**
 * 2022-11-08 10:53:56
 * [2461] - 长度为 K 子数组中的最大和
 * MaximumSumOfDistinctSubarraysWithLengthK.md
 */
public class MaximumSumOfDistinctSubarraysWithLengthK{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumSumOfDistinctSubarraysWithLengthK().new Solution();
        System.out.println("预期结果：15 , 运行结果：" + solution.maximumSubarraySum(new int[]{1,5,4,2,9,9,9}, 3));
        System.out.println("预期结果：0 , 运行结果：" + solution.maximumSubarraySum(new int[]{4,4,4}, 3));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long maximumSubarraySum(int[] nums, int k) {
            long max = 0, sum = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < nums.length; i++){
                if(i >= k){
                    sum -= nums[i - k];
                    map.put(nums[i - k], map.get(nums[i - k]) - 1);
                    if(map.get(nums[i - k]) == 0){
                        map.remove(nums[i - k]);
                    }                    
                }
                sum += nums[i];
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
                max = Math.max(max, map.size() == k ? sum : 0);
            }
            return max;
        }
        
        //直接用左右滑窗，以及用Boolean数组代替map，效率更高
        public long maximumSubarraySum2(int[] nums, int k) {
            boolean[] flag = new boolean[100001];
            long max = 0, sum = 0;
            for(int left = 0, right = 0; right < nums.length; right++){
                sum += nums[right];
                if(!flag[nums[right]]){
                    flag[nums[right]] = true;
                }else{
                    while(nums[left] != nums[right]){
                        sum -= nums[left];
                        flag[nums[left++]] = false;
                    }
                    sum -= nums[left++];
                }
                if(right - left + 1 == k){
                    max = Math.max(max, sum);
                    sum -= nums[left];
                    flag[nums[left]] = false;
                    left++;
                }
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}