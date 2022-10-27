package Leetcode.editor.cn;

/**
 * 2022-10-27 09:15:09
 * [1822] - 数组元素积的符号
 * SignOfTheProductOfAnArray.md
 */
public class SignOfTheProductOfAnArray{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SignOfTheProductOfAnArray().new Solution();
        System.out.println("运行结果：" + solution.arraySign(new int[]{-1,-2,-3,-4,3,2,1}));
        System.out.println("运行结果：" + solution.arraySign(new int[]{1,5,0,2,-3}));
        System.out.println("运行结果：" + solution.arraySign(new int[]{-1,1,-1,1,-1}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int arraySign(int[] nums) {
            int sign = 1;
            for(int n : nums){
                if(n < 0){
                    sign = -sign;
                }else if(n == 0){
                    return 0;
                }
            }   
            return sign;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}