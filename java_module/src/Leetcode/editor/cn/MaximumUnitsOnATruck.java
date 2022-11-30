package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-11-15 15:37:20
 * [1710] - 卡车上的最大单元数
 * MaximumUnitsOnATruck.md
 */
public class MaximumUnitsOnATruck{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumUnitsOnATruck().new Solution();
        System.out.println("预期结果：8 , 运行结果：" + solution.maximumUnits2(new int[][]{{1,3},{2,2},{3,1}}, 4));
        System.out.println("预期结果：91 , 运行结果：" + solution.maximumUnits2(new int[][]{{5,10},{2,5},{4,7},{3,9}}, 10));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximumUnits(int[][] boxTypes, int truckSize) {
            Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
            int ans = 0;
            for(int i = 0; truckSize > 0 && i < boxTypes.length; i++){
                if(boxTypes[i][0] < truckSize){
                    truckSize -= boxTypes[i][0];
                    ans += boxTypes[i][1] * boxTypes[i][0];
                }else{
                    ans += truckSize * boxTypes[i][1];
                    break;
                }
            }
            return ans;
        }
        
        //因为题目的量级小，所以计数排序的效率更高。
        public int maximumUnits2(int[][] boxTypes, int truckSize) {
            int[] cnt = new int[1001];
            for(int[] box : boxTypes){
                cnt[box[1]] += box[0];
            }
            int ans = 0;
            for(int i = 1000; i >= 0; i--){
                if(cnt[i] > 0){
                    if(cnt[i] < truckSize){
                        ans += i * cnt[i];
                        truckSize -= cnt[i];                        
                    }else{
                        ans += truckSize * i;
                        return ans;
                    }
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}