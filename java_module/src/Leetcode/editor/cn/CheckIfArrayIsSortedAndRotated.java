package Leetcode.editor.cn;

/**
 * 2022-11-27 14:43:11
 * [1752] - 检查数组是否经排序和轮转得到
 * CheckIfArrayIsSortedAndRotated.md
 */
public class CheckIfArrayIsSortedAndRotated{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CheckIfArrayIsSortedAndRotated().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.check(new int[]{3,4,5,1,2}));
        System.out.println("预期结果：false , 运行结果：" + solution.check(new int[]{2,1,3,4}));
        System.out.println("预期结果：true , 运行结果：" + solution.check(new int[]{1,2,3}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean check(int[] nums) {
            int cnt = 0;
            for(int i = 0; i < nums.length; i++){
                cnt += nums[i] > nums[(i + 1) % nums.length] ? 1 : 0;
            }
            return cnt <= 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}