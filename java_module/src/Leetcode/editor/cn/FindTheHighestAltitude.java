package Leetcode.editor.cn;

/**
 * 2022-11-19 22:05:26
 * [1732] - 找到最高海拔
 * FindTheHighestAltitude.md
 */
public class FindTheHighestAltitude{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FindTheHighestAltitude().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.largestAltitude(new int[]{-5,1,5,0,-7}));
        System.out.println("预期结果：0 , 运行结果：" + solution.largestAltitude(new int[]{-4,-3,-2,-1,4,3,2}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestAltitude(int[] gain) {
            int ans = 0;
            for(int i = 0, curr = 0; i < gain.length; i++){
                curr += gain[i];
                ans = Math.max(curr, ans);
            }
            return ans;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}