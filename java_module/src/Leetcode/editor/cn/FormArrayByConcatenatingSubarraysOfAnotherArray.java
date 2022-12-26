package Leetcode.editor.cn;

/**
 * 2022-12-17 16:49:27
 * [1764] - 通过连接另一个数组的子数组得到一个数组
 * FormArrayByConcatenatingSubarraysOfAnotherArray.md
 */
public class FormArrayByConcatenatingSubarraysOfAnotherArray{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FormArrayByConcatenatingSubarraysOfAnotherArray().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.canChoose(new int[][]{{1,-1,-1},{3,-2,0}}, new int[]{1,-1,0,1,-1,-1,3,-2,0}));
        System.out.println("预期结果：false , 运行结果：" + solution.canChoose(new int[][]{{10,-2},{1,2,3,4}}, new int[]{1,2,3,4,10,-2}));
        System.out.println("预期结果：false , 运行结果：" + solution.canChoose(new int[][]{{1,2,3},{3,4}}, new int[]{7,7,1,2,3,4,7,7}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接暴力，因为写不出 KMP
        public boolean canChoose(int[][] groups, int[] nums) {
            int i = 0;
            for(int k = 0; k < nums.length && i < groups.length;){
                if(check(groups[i], nums, k)){
                    k += groups[i].length;
                    i++;
                }else{
                    k++;
                }
            }
            return i == groups.length;
        }
        
        boolean check(int[] group, int[] nums, int startIdx){
            if(startIdx + group.length > nums.length){
                return false;
            }
            for(int i = 0; i < group.length; i++){
                if(group[i] != nums[startIdx + i]){
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}