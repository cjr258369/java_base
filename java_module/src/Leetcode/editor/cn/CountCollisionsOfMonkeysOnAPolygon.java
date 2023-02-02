package Leetcode.editor.cn;

import java.math.BigInteger;

/**
 * 2023-01-30 17:07:58
 * [2550] - 猴子碰撞的方法数
 * CountCollisionsOfMonkeysOnAPolygon.md
 */
 
//现在有一个正凸多边形，其上共有 n 个顶点。顶点按顺时针方向从 0 到 n - 1 依次编号。每个顶点上 正好有一只猴子 。下图中是一个 6 个顶点的凸多边形。 
//
// 每个猴子同时移动到相邻的顶点。顶点 i 的相邻顶点可以是： 
// 顺时针方向的顶点 (i + 1) % n ，或逆时针方向的顶点 (i - 1 + n) % n 。 
//
// 如果移动后至少有两个猴子位于同一顶点，则会发生 碰撞 。 
// 返回猴子至少发生 一次碰撞 的移动方法数。由于答案可能非常大，请返回对 10⁹+7 取余后的结果。 
// 注意，每只猴子只能移动一次。 
//
// 示例 1： 
// 输入：n = 3
//输出：6
//解释：共计 8 种移动方式。
//下面列出两种会发生碰撞的方式：
//- 猴子 1 顺时针移动；猴子 2 逆时针移动；猴子 3 顺时针移动。猴子 1 和猴子 2 碰撞。
//- 猴子 1 逆时针移动；猴子 2 逆时针移动；猴子 3 顺时针移动。猴子 1 和猴子 3 碰撞。
//可以证明，有 6 种让猴子碰撞的方法。
//
// 示例 2： 
// 输入：n = 4
//输出：14
//解释：可以证明，有 14 种让猴子碰撞的方法。 
//
// 提示： 
// 3 <= n <= 10⁹ 

public class CountCollisionsOfMonkeysOnAPolygon{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountCollisionsOfMonkeysOnAPolygon().new Solution();
        System.out.println("预期结果：6 , 运行结果：" + solution.monkeyMove(3));
        System.out.println("预期结果：14 , 运行结果：" + solution.monkeyMove(4));
        System.out.println("预期结果：1000000006 , 运行结果：" + solution.monkeyMove(500000003));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //只有全部顺时针和全部逆时针才不会碰撞，因此答案为：因此答案为 2^n-2（2的n次方 - 2）
        //正常写法需要手动实现快速幂，java的 BigInteger 也提供了快速幂的pow
        public int monkeyMove(int n) {
            //这样写在案例81，输入为 500000003 时会溢出
            //return BigInteger.valueOf(2).modPow(BigInteger.valueOf(n), BigInteger.valueOf(1000000007)).intValue() - 2;
            //因此为了防止负数，直接 + 1000000005
            return (BigInteger.valueOf(2).modPow(BigInteger.valueOf(n), BigInteger.valueOf(1000000007)).intValue() + 1000000005) % 1000000007;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}