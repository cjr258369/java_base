package Leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 2022-12-29 09:11:00
 * [2032] - 至少在两个数组中出现的值
 * TwoOutOfThree.md
 */
public class TwoOutOfThree{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new TwoOutOfThree().new Solution();
        System.out.println("预期结果：[3,2] , 运行结果：" + solution.twoOutOfThree3(new int[]{1,1,3,2}, new int[]{2, 3}, new int[]{3}));
        System.out.println("预期结果：[3,2,1] , 运行结果：" + solution.twoOutOfThree3(new int[]{3, 1}, new int[]{2, 3}, new int[]{1, 2}));
        System.out.println("预期结果：[] , 运行结果：" + solution.twoOutOfThree3(new int[]{1, 2, 2}, new int[]{4, 3, 3}, new int[]{5}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
            ArrayList<Integer> res = new ArrayList<>();
            int[] cnt1 = new int[101], cnt2 = new int[101], cnt3 = new int[101];
            for(int num : nums1) cnt1[num]++;
            for(int num : nums2) cnt2[num]++;
            for(int num : nums3) cnt3[num]++;
            for(int i = 0; i < 101; i++){
                if((cnt1[i] > 0 && cnt2[i] > 0) || (cnt1[i] > 0 && cnt3[i] > 0) || (cnt2[i] > 0 && cnt3[i] > 0)){
                    res.add(i);
                }
            }
            return res;            
        }
        
        public List<Integer> twoOutOfThree2(int[] nums1, int[] nums2, int[] nums3) {
            ArrayList<Integer> res = new ArrayList<>();
            int[] cnt1 = new int[101], cnt2 = new int[101], cnt3 = new int[101];
            for(int num : nums1) cnt1[num] = 1;
            for(int num : nums2) cnt2[num] = 1;
            for(int num : nums3) cnt3[num] = 1;
            for(int i = 0; i < 101; i++){
                if(cnt1[i] + cnt2[i] + cnt3[i] >= 2){
                    res.add(i);
                }
            }
            return res;            
        }
        
        //只有三个数组，直接用一个数的二进制位来表示当前数字存在于哪个数组
        public List<Integer> twoOutOfThree3(int[] nums1, int[] nums2, int[] nums3) {
            ArrayList<Integer> res = new ArrayList<>();
            int[] cnt = new int[101];
            for(int num : nums1) cnt[num] |= 1;
            for(int num : nums2) cnt[num] |= 2;
            for(int num : nums3) cnt[num] |= 4;
            for(int i = 0; i < 101; i++){
                //判断每一个数对应的标记数字中二进制位个数是否大于 1
                if((cnt[i] & (cnt[i] - 1)) != 0){
                    res.add(i);
                }
            }
            return res;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}