package Leetcode.editor.cn;

/**
 * 2022-11-24 21:29:26
 * [795] - 区间子数组个数
 * NumberOfSubarraysWithBoundedMaximum.md
 */
public class NumberOfSubarraysWithBoundedMaximum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NumberOfSubarraysWithBoundedMaximum().new Solution();
        System.out.println("预期结果：3 , 运行结果：" + solution.numSubarrayBoundedMax(new int[]{2,1,4,3}, 2, 3));
        System.out.println("预期结果：7 , 运行结果：" + solution.numSubarrayBoundedMax(new int[]{2,9,2,5,6}, 2, 8));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        // 对数组的每一个元素进行检查、如果大于right，令i=j，那么temp = i - j=0；
        // 如果大于left但小于right，i因为++但是j仍未变，导致temp= i - j = 1，计数器+temp; 
        // 如果小于left，那么不进行任何操作，temp不变仍为1，计数器+temp；
        //该思路的核心仍是：因为题目要求“连续”，所以子数组中满足要求的条件是至少有一个数在left和right之间，
        // 可以允许小于left但绝不允许大于right，因此只要在检查数组时，前方的元素a满足条件，那么后方的元素b仍满足条件则判断temp+1，
        // 小于left则跟随前面的temp不变，大于right，temp清零。
        
        int ans = 0;
        for(int i = 0, j = -1, cnt = 0; i < nums.length; i++){
            if(nums[i] > right){
                j = i;
            }
            if(nums[i] >= left){
                cnt = i - j;
            }
            ans += cnt;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}