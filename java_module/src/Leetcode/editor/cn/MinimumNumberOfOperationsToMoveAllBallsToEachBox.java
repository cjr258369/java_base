package Leetcode.editor.cn;

import java.util.Arrays;

/**
 * 2022-12-02 09:31:30
 * [1769] - 移动所有球到每个盒子所需的最小操作数
 * MinimumNumberOfOperationsToMoveAllBallsToEachBox.md
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new MinimumNumberOfOperationsToMoveAllBallsToEachBox().new Solution();
        System.out.println("预期结果：[1,1,3] , 运行结果：" + Arrays.toString(solution.minOperations2("110")));
        System.out.println("预期结果：[11,8,5,4,3,4] , 运行结果：" + Arrays.toString(solution.minOperations2("001011")));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //因为 1 <= n <= 2000，尝试直接 n ^ 2 暴力，能通过【95/95】
        public int[] minOperations(String boxes) {
            int[] res = new int[boxes.length()];
            for(int i = 0; i < boxes.length(); i++){
                if('1' == boxes.charAt(i)){
                    for(int j = 0; j < boxes.length(); j++){
                        if(j == i) continue;
                        res[j] += Math.abs(i - j);
                    }
                }
            }
            return res;
        }
        
        //优化至O(n)，统计每个位置左边 1 的个数 和 右边 1 的个数，先从最左开始统计
        //之后计算时就可以通过：result[i - 1] + leftCount - rightCount 快速计算，因为 原来的左侧每个球，都需要增加1步，右侧的每个球，都要减少一步
        public int[] minOperations2(String boxes) {
            int leftCnt = boxes.charAt(0) - '0', rightCnt = 0, res[] = new int[boxes.length()];
            for(int i = 1; i < boxes.length(); i++){
                if('1' == boxes.charAt(i)){
                    rightCnt++;
                    res[0] += i;
                }
            }
            
            for(int i = 1; i < boxes.length(); i++){
                res[i] = res[i - 1] + leftCnt - rightCnt;
                if('1' == boxes.charAt(i)){
                    leftCnt++;
                    rightCnt--;
                } 
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}