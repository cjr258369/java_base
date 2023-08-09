package Leetcode.editor.cn;

/**
 * 2023-06-26 09:30:22
 * [2485] - 找出中枢整数
 * FindThePivotInteger.md
 */
 
//给你一个正整数 n ，找出满足下述条件的 中枢整数 x ： 1 和 x 之间的所有元素之和等于 x 和 n 之间所有元素之和。
// 返回中枢整数 x 。如果不存在中枢整数，则返回 -1 。题目保证对于给定的输入，至多存在一个中枢整数。 

//
// 示例 1：
//输入：n = 8
//输出：6
//解释：6 是中枢整数，因为 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21 。
//
// 示例 2： 
//输入：n = 1
//输出：1
//解释：1 是中枢整数，因为 1 = 1 。
//
// 示例 3： 
//输入：n = 4
//输出：-1
//解释：可以证明不存在满足题目要求的整数。 
//
// 提示： 
// 1 <= n <= 1000 

public class FindThePivotInteger{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new FindThePivotInteger().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.pivotInteger2(8));
        System.out.println("预期结果：1 , 运行结果：" + solution.pivotInteger2(1));
        System.out.println("预期结果：-1 , 运行结果：" + solution.pivotInteger2(4));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前缀和  时间 和 空间都是 O(n)
        public int pivotInteger(int n) {
            int sum = (1 + n) * n / 2;
            for(int i = 1, target = 0; i <= n; i++){
                sum -= i;
                if(target == sum){
                    return i;
                }
                target += i;
            }
            return -1;    
        }
        
        //数学 O(1)
        //推理：题目要求找一个 x 使得 sum(1, x) == sum(x, n)，那么根据等差数列求和，转换会得到求x的公式。

        public int pivotInteger2(int n) {
            int t = (n * n + n)/2;
            int x = (int)Math.sqrt(t);
            return x * x == t ? x : -1;
        }
        
    }
    //leetcode submit region end(Prohibit modification and deletion)

}