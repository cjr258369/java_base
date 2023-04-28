package Leetcode.editor.cn;

/**
 * 2023-02-13 16:36:34
 * [2562] - 找出数组的串联值
 * FindTheArrayConcatenationValue.md
 */
 
//给你一个下标从 0 开始的整数数组 nums 。 
// 现定义两个数字的 串联 是由这两个数值串联起来形成的新数字。 
// 例如，15 和 49 的串联是 1549 。 
//
// nums 的 串联值 最初等于 0 。执行下述操作直到 nums 变为空： 
// 如果 nums 中存在不止一个数字，分别选中 nums 中的第一个元素和最后一个元素，将二者串联得到的值加到 nums 的 串联值 上，然后从 nums 中删除第一个和最后一个元素。 
// 如果仅存在一个元素，则将该元素的值加到 nums 的串联值上，然后删除这个元素。 
//
// 返回执行完所有操作后 nums 的串联值。 
//
// 示例 1： 
//输入：nums = [7,52,2,4]
//输出：596
//解释：在执行任一步操作前，nums 为 [7,52,2,4] ，串联值为 0 。
// - 在第一步操作中：我们选中第一个元素 7 和最后一个元素 4 。二者的串联是 74 ，将其加到串联值上，所以串联值等于 74 。接着我们从 nums 中移除这两个元素，所以 nums 变为 [52,2] 。
// - 在第二步操作中：我们选中第一个元素 52 和最后一个元素 2 。二者的串联是 522 ，将其加到串联值上，所以串联值等于 596 。接着我们从 nums 中移除这两个元素，所以 nums 变为空。
//由于串联值等于 596 ，所以答案就是 596 。
//
// 示例 2： 
//输入：nums = [5,14,13,8,12]
//输出：673
//解释：在执行任一步操作前，nums 为 [5,14,13,8,12] ，串联值为 0 。 
//- 在第一步操作中：我们选中第一个元素 5 和最后一个元素 12 。二者的串联是 512 ，将其加到串联值上，所以串联值等于 512 。接着我们从 nums 中移除这两个元素，所以 nums 变为 [14,13,8] 。
//- 在第二步操作中：我们选中第一个元素 14 和最后一个元素 8 。二者的串联是 148 ，将其加到串联值上，所以串联值等于 660 。接着我们从 nums 中移除这两个元素，所以 nums 变为 [13] 。 
//- 在第三步操作中：nums 只有一个元素，所以我们选中 13 并将其加到串联值上，所以串联值等于 673 。接着我们从 nums 中移除这个元素，所以 nums 变为空。 
//由于串联值等于 673 ，所以答案就是 673 。
//
// 提示： 
// 1 <= nums.length <= 1000 
// 1 <= nums[i] <= 10⁴ 

public class FindTheArrayConcatenationValue{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FindTheArrayConcatenationValue().new Solution();
        System.out.println("预期结果：596 , 运行结果：" + solution.findTheArrayConcVal(new int[]{7,52,2,4}));
        System.out.println("预期结果：673 , 运行结果：" + solution.findTheArrayConcVal(new int[]{5,14,13,8,12}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long findTheArrayConcVal(int[] nums) {
            long ans = 0;
            for(int i = 0, j = nums.length - 1; i <= j; i++, j--){
                ans += i == j ? nums[i] : Integer.parseInt("" + nums[i] + nums[j]);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}