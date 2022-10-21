package Leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2022-10-21 14:58:03
 * [901] - 股票价格跨度
 * OnlineStockSpan.md
 */
public class OnlineStockSpan{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        StockSpanner solution = new OnlineStockSpan().new StockSpanner();
        System.out.println("运行结果：" + solution.next(100));
        System.out.println("运行结果：" + solution.next(80));
        System.out.println("运行结果：" + solution.next(60));
        System.out.println("运行结果：" + solution.next(70));
        System.out.println("运行结果：" + solution.next(60));
        System.out.println("运行结果：" + solution.next(75));
        System.out.println("运行结果：" + solution.next(85));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class StockSpanner {
        Deque<int[]> stack;
        public StockSpanner() {
            stack = new ArrayDeque<>();
        }
        public int next(int price) {
            int days = 1;
            while(!stack.isEmpty() && stack.peekLast()[1] <= price){
                days += stack.pollLast()[0];                
            }
            stack.offerLast(new int[]{days, price});
            return days;    
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}