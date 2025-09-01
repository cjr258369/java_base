package Leetcode.editor.cn;

/**
 * 2023-01-23 00:57:50
 * [2303] - 计算应缴税款总额
 * CalculateAmountPaidInTaxes.md
 */

//给你一个下标从 0 开始的二维整数数组 brackets ，其中 brackets[i] = [upper_i, percent_i] ，表示第 i 个税级的上限是 upper_i ，征收的税率为 percent_i 。税级按上限 从低到高排序（在满足 0 < i < brackets.length 的前提下，upper_i-1 < upper_i）。 
//
// 税款计算方式如下： 
// 1. 不超过 upper0 的收入按税率 percent0 缴纳 
// 2. 接着 upper1 - upper0 的部分按税率 percent1 缴纳 
// 3. 然后 upper2 - upper1 的部分按税率 percent2 缴纳 
// 以此类推 
//
// 给你一个整数 income 表示你的总收入。返回你需要缴纳的税款总额。与标准答案误差不超 10⁻⁵ 的结果将被视作正确答案。 
//
// 示例 1： 
// 输入：brackets = [[3,50],[7,10],[12,25]], income = 10
//输出：2.65000
//解释：
//前 $3 的税率为 50% 。需要支付税款 $3 * 50% = $1.50 。
//接下来 $7 - $3 = $4 的税率为 10% 。需要支付税款 $4 * 10% = $0.40 。
//最后 $10 - $7 = $3 的税率为 25% 。需要支付税款 $3 * 25% = $0.75 。
//需要支付的税款总计 $1.50 + $0.40 + $0.75 = $2.65 。
//
// 示例 2： 
// 输入：brackets = [[1,0],[4,25],[5,50]], income = 2
//输出：0.25000
//解释：
//前 $1 的税率为 0% 。需要支付税款 $1 * 0% = $0 。
//剩下 $1 的税率为 25% 。需要支付税款 $1 * 25% = $0.25 。
//需要支付的税款总计 $0 + $0.25 = $0.25 。
// 
// 示例 3： 
// 输入：brackets = [[2,50]], income = 0
//输出：0.00000
//解释：
//没有收入，无需纳税，需要支付的税款总计 $0 。
//
// 提示： 
// 1 <= brackets.length <= 100 
// 1 <= upperi <= 1000 
// 0 <= percenti <= 100 
// 0 <= income <= 1000 
// upperi 按递增顺序排列 
// upperi 中的所有值 互不相同 
// 最后一个税级的上限大于等于 income 
 
public class CalculateAmountPaidInTaxes{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CalculateAmountPaidInTaxes().new Solution();
        System.out.println("预期结果：2.65000 , 运行结果：" + solution.calculateTax(new int[][]{{3,50},{7,10},{12,25}}, 10));
        System.out.println("预期结果：0.25000 , 运行结果：" + solution.calculateTax(new int[][]{{1,0},{4,25},{5,50}}, 2));
        System.out.println("预期结果：0.00000 , 运行结果：" + solution.calculateTax(new int[][]{{2,50}}, 0));
        System.out.println("预期结果：0.50000 , 运行结果：" + solution.calculateTax(new int[][]{{10,10}}, 5));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double calculateTax(int[][] brackets, int income) {
            double ans = 0.00;
            if(income <= brackets[0][0]){
                return income * (brackets[0][1] / 100.00);
            }
            for(int i = 0; i < brackets.length && income > 0; i++){
                int val = i > 0 ? brackets[i][0] - brackets[i - 1][0] : brackets[i][0];
                val = income >= val ? val : income;
                ans += val * (brackets[i][1] / 100.00);
                income -= val;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}