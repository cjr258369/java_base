package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-10-24 09:05:37
 * [915] - 分割数组
 * PartitionArrayIntoDisjointIntervals.md
 */
public class PartitionArrayIntoDisjointIntervals{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new PartitionArrayIntoDisjointIntervals().new Solution();
        System.out.println("运行结果：" + solution.partitionDisjoint3(new int[]{5,0,3,8,6}));
        System.out.println("运行结果：" + solution.partitionDisjoint3(new int[]{1,1,1,0,6,12}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //开两个数组，两次遍历
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n], rightMin = new int[n];
        leftMax[0] = nums[0];
        rightMin[n - 1] = nums[n - 1];
        for(int i = 1, j = n - 2; i < n; i++, j--){
            leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
            rightMin[j] = Math.min(rightMin[j + 1], nums[j]);
        }
        //System.out.println("leftMax  = " + Arrays.toString(leftMax));
        //System.out.println("rightMin = " + Arrays.toString(rightMin));
        for(int i = 0; i < n - 1; i++){
            if(leftMax[i] <= rightMin[i + 1]){
                return i + 1;
            }
        }
        return n - 1;
    }
    
    //开一个数组，两次遍历
    public int partitionDisjoint2(int[] nums) {
        int n = nums.length, leftMax = 0;
        int[]rightMin = new int[n];
        rightMin[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; i-- ){
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }
        for(int i = 0; i < n - 1; i++){
            leftMax = Math.max(leftMax, nums[i]);
            if(leftMax <= rightMin[i + 1]){
                return i + 1;
            }
        }
        return n - 1;
    }
    
    //常数空间，一次遍历
    public int partitionDisjoint3(int[] nums) {
        int leftMax = nums[0], leftPos = 0, currMax = nums[0];
        for(int i = 1; i < nums.length - 1; i++){
            currMax = Math.max(currMax, nums[i]);
            if(nums[i] < leftMax){
                leftMax = currMax;
                leftPos = i;
            }
        }
        return leftPos + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}