package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 2022-11-08 10:45:52
 * [2460] - 对数组执行操作
 * ApplyOperationsToAnArray.md
 */
public class ApplyOperationsToAnArray{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new ApplyOperationsToAnArray().new Solution();
        System.out.println("预期结果：[1,4,2,0,0,0] , 运行结果：" + Arrays.toString(solution.applyOperations(new int[]{1,2,2,1,1,0})));
        System.out.println("预期结果：[1,0] , 运行结果：" + Arrays.toString(solution.applyOperations(new int[]{0, 1})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] applyOperations(int[] nums) {
            for(int i = 0; i < nums.length - 1; i++){
                if(nums[i] == nums[i + 1]){
                    nums[i] *= 2;
                    nums[i + 1] = 0;
                }            
            }
            
            return IntStream.of(nums).boxed().sorted((a, b) -> b == 0 ? -1 : (a == 0 ? 1 : 0)).mapToInt(v -> v).toArray();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}