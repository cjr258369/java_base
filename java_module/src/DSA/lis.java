package DSA;

import java.util.ArrayList;
import java.util.List;

/**
 * LIS：求数组的最长递增子序列（Long Increasing Subsequence）
 * 相关名词
 * 子串：要求连续
 * 子序列：不要求连续
 *
 * 对应LC题号：300
 *
 * 可以用于：
 * A、求数组内各个起点开始的递增子序列的长度
 * B、当一个问题为：变更数组的多少个数字，可以使得数据变成全递增时：那么就可以转换为求最长的递增子序列的长度，它与数组的长度的差，剩就是都需要变更的数字的数量
 * C、...
 */
public class lis {

    /**
     * 一些自测案例:
     * nums = [10,9,2,5,3,7,101,18]
     * 4
     *
     * nums = [0,1,0,3,2,3]
     * 4
     *
     * [7,7,7,7,7,7,7]
     * 1
     */
    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int[] nums2 = new int[]{0,1,0,3,2,3};
        System.out.println(dp(nums2));
        System.out.println(brinarySearchLis(nums2));
        List<Integer> test = new ArrayList();
        test.add(0); test.add(1); test.add(0); test.add(3); test.add(2); test.add(3);
        System.out.println(brinarySearchLis(test));
    }

    /**
     * 常规dp解法
     * 时间复杂度：n平方
     */
    public static int dp(int[] nums){
        int len = nums.length;
        int[] dp = new int[len];
        int result = 0;
        for(int i=0;i<len;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]) dp[i] = Math.max(dp[i] , dp[j]+1);
            }
            result = Math.max(result , dp[i]);
        }
        return result;
    }

    /**
     * 二分法做的LIS
     * 时间复杂度：nlogn
     * @param nums
     * @return
     */
    public static int brinarySearchLis(int[] nums){
        int len = nums.length;
        if(len == 0) return 0;

        int[] tail = new int[len];
        int end = 0;
        for(int i=0;i<len;i++){
            int left = 0;
            int right = end;
            while(left<right){
                int mid = (left + right)/2;
                if(tail[mid]<=nums[i]){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }
            tail[left] = nums[i];
            if(left == end) end++;
        }
        return end;
    }

    /**
     * List版本的二分
     * @param nums
     * @return
     */
    public static int brinarySearchLis(List<Integer> nums){
        int size = nums.size();
        if(size == 0) return 0;

        int[] tail = new int[size];
        int end = 0;
        for(int x : nums){
            int left = 0;
            int right = end;
            while(left<right){
                int mid = (left + right)/2;
                if(tail[mid]<=x){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }
            tail[left] = x;
            if(left == end) end++;
        }
        return end;
    }
}
