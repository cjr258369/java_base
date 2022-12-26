package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2022-12-05 11:58:42
 * [1687] - 从仓库到码头运输箱子
 * DeliveringBoxesFromStorageToPorts.md
 */
public class DeliveringBoxesFromStorageToPorts{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new DeliveringBoxesFromStorageToPorts().new Solution();
        System.out.println("预期结果：4 , 运行结果：" + solution.boxDelivering(new int[][]{{1,1},{2,1},{1,1}}, 2, 3, 3));
        System.out.println("预期结果：6 , 运行结果：" + solution.boxDelivering(new int[][]{{1,2},{3,3},{3,1},{3,1},{2,4}}, 3, 3, 6));
        System.out.println("预期结果：6 , 运行结果：" + solution.boxDelivering(new int[][]{{1,4},{1,2},{2,1},{2,1},{3,2},{3,4}}, 3, 6, 7));
        System.out.println("预期结果：14 , 运行结果：" + solution.boxDelivering(new int[][]{{2,4},{2,5},{3,1},{3,2},{3,7},{3,1},{4,4},{1,3},{5,2}}, 5, 5, 7));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
            int n = boxes.length;
            int[] p = new int[n + 1];
            int[] w = new int[n + 1];
            int[] neg = new int[n + 1];
            long[] W = new long[n + 1];
            for (int i = 1; i <= n; ++i) {
                p[i] = boxes[i - 1][0];
                w[i] = boxes[i - 1][1];
                if (i > 1) {
                    neg[i] = neg[i - 1] + (p[i - 1] != p[i] ? 1 : 0);
                }
                W[i] = W[i - 1] + w[i];
            }

            Deque<Integer> opt = new ArrayDeque<Integer>();
            opt.offerLast(0);
            int[] f = new int[n + 1];
            int[] g = new int[n + 1];

            for (int i = 1; i <= n; ++i) {
                while (i - opt.peekFirst() > maxBoxes || W[i] - W[opt.peekFirst()] > maxWeight) {
                    opt.pollFirst();
                }

                f[i] = g[opt.peekFirst()] + neg[i] + 2;

                if (i != n) {
                    g[i] = f[i] - neg[i + 1];
                    while (!opt.isEmpty() && g[i] <= g[opt.peekLast()]) {
                        opt.pollLast();
                    }
                    opt.offerLast(i);
                }
            }

            return f[n];    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}