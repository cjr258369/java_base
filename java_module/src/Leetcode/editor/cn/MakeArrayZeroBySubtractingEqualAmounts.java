package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * 2023-02-24 09:05:55
 * [2357] - 使数组中所有元素都等于零
 * MakeArrayZeroBySubtractingEqualAmounts.md
 */
 
//给你一个非负整数数组 nums 。在一步操作中，你必须：
// 选出一个正整数 x ，x 需要小于或等于 nums 中 最小 的 非零 元素。 
// nums 中的每个正整数都减去 x。
// 返回使 nums 中所有元素都等于 0 需要的 最少 操作数。
//
// 示例 1： 
//输入：nums = [1,5,0,3,5]
//输出：3
//解释：
//第一步操作：选出 x = 1 ，之后 nums = [0,4,0,2,4] 。
//第二步操作：选出 x = 2 ，之后 nums = [0,2,0,0,2] 。
//第三步操作：选出 x = 2 ，之后 nums = [0,0,0,0,0] 。 
//
// 示例 2： 
//输入：nums = [0]
//输出：0
//解释：nums 中的每个元素都已经是 0 ，所以不需要执行任何操作。
//
// 提示： 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 

public class MakeArrayZeroBySubtractingEqualAmounts{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MakeArrayZeroBySubtractingEqualAmounts().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.minimumOperations(new int[]{1,5,0,3,5}));
        System.out.println("预期结果：0 , 运行结果：" + solution.minimumOperations(new int[]{0}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //本质就是找不同正整数的数量
        //复杂度分析
        // 时间复杂度：O(nlogn)，其中 n 是数组 nums 的长度。nlogn 是排序的时间复杂度，排序后，仍旧需要遍历数组一次，遍历的时间复杂度为 O(n)，因此整体时间复杂度为O(nlogn)。
        // 空间复杂度：O(1)，仅需要1个常量存储之前的变量。
        public int minimumOperations(int[] nums) {
            Arrays.sort(nums);
            int cnt = 0;
            for(int i = 0, lastNum = 0; i < nums.length; i++){
                if(nums[i] > lastNum){
                    cnt++;
                    lastNum = nums[i];
                }
            }
            return cnt;    
        }
        
        //用 Set ，则可以省去排序的时间消耗
        // 复杂度分析
        // 时间复杂度：O(n)，其中 n 是数组 nums 的长度。需要遍历数组一次，每个非零元素加入哈希集合的时间是 O(1)。
        // 空间复杂度：O(n)，其中 n 是数组 nums 的长度。哈希集合需要 O(n) 的空间。
        public int minimumOperations2(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for(int i = 0; i < nums.length; i++){
                if(nums[i] != 0) set.add(nums[i]);
            }
            return set.size();    
        }
        //如果觉得 HashSet 慢，可以直接用数组代替
        public int minimumOperations3(int[] nums) {
            int[] cnts = new int[101];
            for(int i = 0; i < nums.length; i++){
                //if(nums[i] > 0) cnts[nums[i]] = 1;
                cnts[nums[i]] = nums[i] > 0 ? 1 : 0;
            }
            return Arrays.stream(cnts).sum();
            //return IntStream.of(cnts).sum();    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}