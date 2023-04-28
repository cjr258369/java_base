package Leetcode.editor.cn;

/**
 * 2023-03-20 11:12:48
 * [2376] - 统计特殊整数
 * CountSpecialIntegers.md
 */
 
//如果一个正整数每一个数位都是 互不相同 的，我们称它是 特殊整数 。 给你一个 正 整数 n ，请你返回区间 [1, n] 之间特殊整数的数目。 
//
// 示例 1： 
//输入：n = 20
//输出：19
//解释：1 到 20 之间所有整数除了 11 以外都是特殊整数。所以总共有 19 个特殊整数。
//
// 示例 2： 
//输入：n = 5
//输出：5
//解释：1 到 5 所有整数都是特殊整数。
//
// 示例 3： 
//输入：n = 135
//输出：110
//解释：从 1 到 135 总共有 110 个整数是特殊整数。
//不特殊的部分数字为：22 ，114 和 131 。 
//
// 提示： 
// 1 <= n <= 2 * 10⁹ 

public class CountSpecialIntegers{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CountSpecialIntegers().new Solution();
        System.out.println("预期结果：19 , 运行结果：" + solution.countSpecialNumbers(20));
        System.out.println("预期结果：5 , 运行结果：" + solution.countSpecialNumbers(5));
        System.out.println("预期结果：110 , 运行结果：" + solution.countSpecialNumbers(135));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //数位dp复杂度分析
        //时间复杂度：O(mD2^D)，其中 m 为 s 的长度，即 O(logn)；D=10。由于每个状态只会计算一次，因此动态规划的时间复杂度 = 状态个数 × 单个状态的计算时间。本题状态个数为 
        // O(m2^D)，单个状态的计算时间为 O(D)，因此时间复杂度为 O(mD2^D)。
        // 空间复杂度：O(m2^D)。
        
        char[] arr;
        public int countSpecialNumbers(int n) {
            arr = ("" + n).toCharArray();
            return dfs(0, 0, true, false);
        }

        /**
         * 返回从 i 开始填数字，i 前面填的数字集合是 mask，此时能构造出的特殊整数的数目
         * @param isLimit 表示前面填的数字是否是 n 对应位上的，如果为 true 那么当前位最多只能为 s[i]，否则可以填 0 ~ 9
         * @param isNum 表示前面是否填过数字了，是否跳过了。true 表示前面填过数字了，那么当前位可以从 0 开始，否则，当前位可以选择：1. 继续跳过；2. 填入1 ~ s[i]
         * @return
         */
        private int dfs(int i, int mask, boolean isLimit, boolean isNum){
            if(i == arr.length){
                return isNum ? 1 : 0;
            }
            int res = 0;
            //跳过
            if(!isNum){
                res = dfs(i + 1, mask, false, false);
            }
            int up = isLimit ? arr[i] - '0' : 9;
            for(int x = isNum ? 0 : 1; x <= up; x++){
                if((mask >> x & 1) == 0){
                    res += dfs(i + 1, mask | (1 << x), isLimit && x == up, true);
                }                
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}