package Leetcode.editor.cn;

/**
 * 2022-12-09 09:27:54
 * [1780] - 判断一个数字是否可以表示成三的幂的和
 * CheckIfNumberIsASumOfPowersOfThree.md
 */
public class CheckIfNumberIsASumOfPowersOfThree{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new CheckIfNumberIsASumOfPowersOfThree().new Solution();
        System.out.println("预期结果：true , 运行结果：" + solution.checkPowersOfThree(12));
        System.out.println("预期结果：true , 运行结果：" + solution.checkPowersOfThree(91));
        System.out.println("预期结果：false , 运行结果：" + solution.checkPowersOfThree(21));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //进制转换：
        // 我们可以将 n 转换成 3 进制。如果 n 的 3 进制表示中每一位均不为 2，那么答案为 True，否则为 False。
        //例如当 n=12 时，12 = (110)_3 ，满足要求；当 n=21 时，21 = (210)_3 ，不满足要求。
        public boolean checkPowersOfThree(int n) {
            while(n != 0){
                if(n % 3 == 2){
                    return false;
                }
                n /= 3;
            }
            return true;            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}