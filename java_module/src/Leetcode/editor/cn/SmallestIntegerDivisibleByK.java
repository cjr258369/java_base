package Leetcode.editor.cn;

/**
 * 2023-05-10 10:17:34
 * [1015] - 可被 K 整除的最小整数
 * SmallestIntegerDivisibleByK.md
 */
 
//给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。
// 返回 n 的长度。如果不存在这样的 n ，就返回-1。
// 注意： n 不符合 64 位带符号整数。 
//
// 示例 1： 
//输入：k = 1
//输出：1
//解释：最小的答案是 n = 1，其长度为 1。 
//
// 示例 2： 
//输入：k = 2
//输出：-1
//解释：不存在可被 2 整除的正整数 n 。 
//
// 示例 3： 
//输入：k = 3
//输出：3
//解释：最小的答案是 n = 111，其长度为 3。 
//
// 提示： 
// 1 <= k <= 10⁵ 

public class SmallestIntegerDivisibleByK{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SmallestIntegerDivisibleByK().new Solution();
        System.out.println("预期结果：1 , 运行结果：" + solution.smallestRepunitDivByK(1));
        System.out.println("预期结果：-1 , 运行结果：" + solution.smallestRepunitDivByK(2));
        System.out.println("预期结果：3 , 运行结果：" + solution.smallestRepunitDivByK(3));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //从除法余数的角度来说，在做竖式的时候，余数种类最多k种，因此枚举到k位没找到的话，说明找不到了
        public int smallestRepunitDivByK(int k) {
            for(int i = 1, n = 0; i <= k; i++){
                n = (n * 10 + 1) % k;
                if(n == 0){
                    return i;
                }                
            }
            return -1; 
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}