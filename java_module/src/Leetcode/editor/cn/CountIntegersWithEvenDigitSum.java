package Leetcode.editor.cn;

/**
 * 2023-01-06 09:09:06
 * [2180] - 统计各位数字之和为偶数的整数个数
 * CountIntegersWithEvenDigitSum.md
 */
 
//给你一个正整数 num ，请你统计并返回 小于或等于 num 且各位数字之和为 偶数 的正整数的数目。 
//
// 正整数的 各位数字之和 是其所有位上的对应数字相加的结果。 
//
// 示例 1： 
//输入：num = 4
//输出：2
//解释：
//只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。    
//
// 示例 2： 
//输入：num = 30
//输出：14
//解释：
//只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是： 
//2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
//
// 提示： 
// 1 <= num <= 1000 

public class CountIntegersWithEvenDigitSum{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountIntegersWithEvenDigitSum().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.countEven2(4));
        System.out.println("预期结果：14 , 运行结果：" + solution.countEven2(30));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //方案一：暴力枚举：
        //时间复杂度：O(num×lognum)。我们总共需要枚举 num 个正整数，而判断每个正整数的各位数字之和是否为偶数需要 O(lognum) 的时间复杂度。
        //空间复杂度：O(1)。
        public int countEven(int num) {
            int ans = 0;
            for(int i = 1; i <= num; i++){
                int temp = i, sum = 0;
                while(temp != 0){
                    sum += temp % 10;
                    temp /= 10;
                }
                ans += (sum % 2 == 0) ? 1 : 0;
            }
            return ans;  
        }
        
        //方案二：数学
        //首先我们从0到num,对于每个数i的所有位之和从偶奇..偶奇偶奇..偶奇这样不断循环,当出现10个数后，顺序进行翻转变为奇偶奇偶奇偶奇偶，再过10个数继续翻转。 
        // 令 sum = num 所有数位之和，通过分析可以发现，若 sum 为偶数，答案为nums/2, 若sum为奇数，答案为(num−1)/2
        //时间复杂度：O(logn)
        //空间复杂度：O(1)
        public int countEven2(int num) {
            int sum = 0;
            for(int i = num; i > 0; i /= 10){
                sum += i % 10;
            }
            return sum % 2 == 1 ? --num/2 : num / 2;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}