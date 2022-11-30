package Leetcode.editor.cn;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 2022-11-23 09:03:34
 * [1742] - 盒子中小球的最大数量
 * MaximumNumberOfBallsInABox.md
 */
public class MaximumNumberOfBallsInABox{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MaximumNumberOfBallsInABox().new Solution();
        System.out.println("预期结果：2 , 运行结果：" + solution.countBalls(1, 10));
        System.out.println("预期结果：2 , 运行结果：" + solution.countBalls(5, 15));
        System.out.println("预期结果：2 , 运行结果：" + solution.countBalls(19, 28));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countBalls(int lowLimit, int highLimit) {
            int max = -1, cnts[] = new int[55];
            while(lowLimit <= highLimit){
                int num = lowLimit, sum = 0;
                while(num > 0){
                    sum += num % 10;
                    num /= 10;
                }
                max = ++cnts[sum] > max ? cnts[sum] : max;
                lowLimit++;
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}