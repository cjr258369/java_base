package Leetcode.editor.cn;

import java.math.BigInteger;

import static Leetcode.util.constVal.MOD;

/**
 * 2022-11-22 15:33:27
 * [878] - 第 N 个神奇数字
 * NthMagicalNumber.md
 */
public class NthMagicalNumber{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new NthMagicalNumber().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.nthMagicalNumber2(1,2,3));
        System.out.println("预期结果：6 , 运行结果：" + solution.nthMagicalNumber2(4,2,3));
        System.out.println("预期结果：5 , 运行结果：" + solution.nthMagicalNumber2(1,100,5));
        System.out.println("预期结果：10 , 运行结果：" + solution.nthMagicalNumber2(2,100,5));
        System.out.println("预期结果：999720007 , 运行结果：" + solution.nthMagicalNumber(1000000000,40000,40000));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //直接暴力，会卡时间，并且逻辑也存在问题，比如上方最后一个案例
        public int nthMagicalNumber(int n, int a, int b) {
            if(a > b){
                return nthMagicalNumber(n, b, a);
            }
            int ans = a, cnt = 0;
            while(cnt < n){
                if(a % ans == 0 || ans % a == 0 || b % ans == 0 || ans % b == 0){
                    cnt++;
                }
                if(cnt == n){
                    return ans;
                }
                ans++;
            }
            return ans;
        }
        
        //用数学 + 二分优化：
        // f(x) = x/a + x/b - x/gcd(a,b)
        public int nthMagicalNumber2(int n, int a, int b) {
            if(a > b){
                return nthMagicalNumber(n, b, a);
            }
            long l = a, r = 1L * n * a;
            //lcm 是 a，b 的最小公倍数，即同时包含 a 和 b 因子的，也即能同时被 a，b 整除的。
            int lcm = a * b / (BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue());
            while(l <= r){
                long mid = (l + r) / 2;
                //整除a的数量 + 整除b的数量 - 重复的（能同时整除a和b的）
                long cnt = mid / a + mid / b - mid / lcm;
                if(cnt >= n){
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }
            return (int)((r + 1) % MOD);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}