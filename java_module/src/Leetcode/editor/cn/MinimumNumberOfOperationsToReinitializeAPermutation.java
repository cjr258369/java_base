package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2023-01-09 09:18:31
 * [1806] - 还原排列的最少操作步数
 * MinimumNumberOfOperationsToReinitializeAPermutation.md
 */
 
//给你一个偶数 n ，已知存在一个长度为 n 的排列 perm ，其中 perm[i] == i（下标 从 0 开始 计数）。 
//
// 一步操作中，你将创建一个新数组 arr ，对于每个 i ： 
// 1. 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2] 
// 2. 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2] 
//
// 然后将 arr 赋值给 perm 。 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。 
//
// 示例 1： 
//输入：n = 2
//输出：1
//解释：最初，perm = [0,1]
//第 1 步操作后，perm = [0,1]
//所以，仅需执行 1 步操作 
//
// 示例 2： 
//输入：n = 4
//输出：2
//解释：最初，perm = [0,1,2,3]
//第 1 步操作后，perm = [0,2,1,3]
//第 2 步操作后，perm = [0,1,2,3]
//所以，仅需执行 2 步操作 
//
// 示例 3： 
//输入：n = 6
//输出：4
//
// 提示： 
// 2 <= n <= 1000 
// n 是一个偶数

public class MinimumNumberOfOperationsToReinitializeAPermutation{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumNumberOfOperationsToReinitializeAPermutation().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.reinitializePermutation2(2));
        System.out.println("预期结果：2 , 运行结果：" + solution.reinitializePermutation2(4));
        System.out.println("预期结果：4 , 运行结果：" + solution.reinitializePermutation2(6));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        //方案一：暴力模拟
        //复杂度分析
        //时间复杂度：O(n^2)，其中 n 表示给定的元素。根据方法二的推论可以知道最多需要经过 n 次变换即可回到初始状态，每次变换需要的时间复杂度为 O(n)，因此总的时间复杂度为 O(n^2)。
        //空间复杂度：O(n)，其中 n 表示给定的元素。我们需要存储每次变换中的过程变量，需要的空间为 O(n)。
        public int reinitializePermutation(int n) {
            int[] perm = new int[n], targer = new int[n];
            for(int i = 0; i < n; i++){
                perm[i] = i;
                targer[i] = i;
            }
            int step = 0;
            while(true){
                int[] temp = new int[n];
                for(int i = 1; i < n; i++){
                    if(i % 2 == 0){
                        temp[i] = perm[i / 2];
                    }else{
                        temp[i] = perm[n / 2 + (i - 1) / 2];
                    }
                }
                perm = temp;
                step++;
                if(Arrays.equals(perm, targer)){
                    break;
                }
            }
            return step;
        }
        
        
        //方案二：直接模拟最长环
        //每个数字都有固定的移动轨迹，所以当它回到原来位置时，所有元素都回到了原来的位置上，例如当 n = 6的时候,
        //[0, 1, 2, 3, 4, 5]
        //[0, 3, 1, 4, 2, 5]
        //[0, 4, 3, 2, 1, 5]
        //[0, 2, 4, 1, 3, 5]
        //[0, 1, 2, 3, 4, 5]
        //除了开头结尾以外，对应数字都是从这个2->3->5->4->2->3->5->4路径中对应数字开始循环
        // 0: 1->1->1->1->1
        // 1: 2->3->5->4->2
        // 2: 3->5->4->2->3
        // 3: 4->2->3->5->4
        // 4: 5->4->2->3->5
        // 5: 6->6->6->6->6
        // 因为一个数字如果从2位置开始， 它会跑到3，之后它就会按位置3开始的元素相同的运动轨迹开始跑。因为在同一位置处到达下一位置的方法是唯一的，也就是说，除了开头的数字和结尾的数字以外，所有元素的路径都是重叠的。
        // 所以只用判断元素1是否回到了自己的位置，并且根据观察可以发现，1或者n-2必然在最长的环中，因此我们模拟1或者n-2进行交换的次数就是最终的答案。
        //
        //复杂度分析
        //时间复杂度：O(n)，其中 n 表示给定的元素。根据推论可以知道最多需要进行计算的次数不超过 n，因此时间复杂度为 O(n)。
        //空间复杂度：O(1)。
        public int reinitializePermutation2(int n) {
            int step = 1, pos = 1;
            while(true){
                pos = (pos % 2 == 0) ? pos / 2 : (n + pos - 1) / 2;
                if(pos == 1){
                    break;
                }
                step++;
            }            
            return step;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}