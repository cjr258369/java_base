package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-04-03 09:18:54
 * [1053] - 交换一次的先前排列
 * PreviousPermutationWithOneSwap.md
 */
 
//给你一个正整数数组 arr（可能存在重复的元素），请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于arr 的最大排列。
// 如果无法这么操作，就请返回原数组。 
//
// 示例 1： 
//输入：arr = [3,2,1]
//输出：[3,1,2]
//解释：交换 2 和 1
//
// 示例 2： 
//输入：arr = [1,1,5]
//输出：[1,1,5]
//解释：已经是最小排列
//
// 示例 3： 
//输入：arr = [1,9,4,6,7]
//输出：[1,7,4,6,9]
//解释：交换 9 和 7
//
// 提示： 
// 1 <= arr.length <= 10⁴ 
// 1 <= arr[i] <= 10⁴ 

public class PreviousPermutationWithOneSwap{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new PreviousPermutationWithOneSwap().new Solution();
        System.out.println("预期结果：[3, 1, 2] , 运行结果：" + Arrays.toString(solution.prevPermOpt1(new int[]{3, 2, 1})));
        System.out.println("预期结果：[1, 1, 5]  , 运行结果：" + Arrays.toString(solution.prevPermOpt1(new int[]{1, 1, 5})));
        System.out.println("预期结果：[1,7,4,6,9]  , 运行结果：" + Arrays.toString(solution.prevPermOpt1(new int[]{1, 9, 4, 6, 7})));
        System.out.println("预期结果：[1, 3, 1, 3]  , 运行结果：" + Arrays.toString(solution.prevPermOpt1(new int[]{3, 1, 1, 3})));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //贪心：
        // 第一步：从当前序列的后往前找，找到第一个降序的位置（A[i]>A[i+1]），则必存在能构造比当前小的序列。
        // 第二步：把A[i]后面的数字中，小于A[i]且最接近A[i]的值的数字找出来，和A[i]交换。为什么第一步不再往前找，因为往前找更换，会让小的值出现在高位，导致不是最大字典序。
        // 同时，第二步中找到的最接近的那个数字，如果有很多个的话，还要找最左边的那个
        // 复杂度分析
        // 时间复杂度：O(n)，其中 n 是数组 arr 的长度。查找 i 需要 O(n) 的时间复杂度，查找 j 需要 O(n) 的时间复杂度。
        // 空间复杂度：O(1)。返回值不计入空间复杂度。
        public int[] prevPermOpt1(int[] arr) {
            for(int i = arr.length - 2, j = arr.length - 1; i >= 0; i--) {
                if (arr[i] > arr[i + 1]) {
                    while (arr[j] >= arr[i] || arr[j] == arr[j - 1]) {
                        j--;
                    }
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    break;
                }
            }
            return arr;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}