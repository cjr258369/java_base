package Leetcode.editor.cn;

/**
 * 2022-11-16 23:34:03
 * [775] - 全局倒置与局部倒置
 * GlobalAndLocalInversions.md
 */
public class GlobalAndLocalInversions{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new GlobalAndLocalInversions().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.isIdealPermutation(new int[]{1,0,2}));
        System.out.println("预期结果：false , 运行结果：" + solution.isIdealPermutation(new int[]{1,2,0}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isIdealPermutation(int[] nums) {
            int minSuff = nums[nums.length - 1];
            for(int i = nums.length - 3; i >= 0; i--){
                if(nums[i] > minSuff){
                    return false;
                }
                minSuff = Math.min(nums[i + 1], minSuff);
            }
            return true;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}