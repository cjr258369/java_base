package Leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import static Leetcode.util.constVal.MOD;

/**
 * 2022-10-28 09:08:50
 * [907] - 子数组的最小值之和
 * SumOfSubarrayMinimums.md
 */
public class SumOfSubarrayMinimums{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new SumOfSubarrayMinimums().new Solution();
        System.out.println("运行结果：" + solution.sumSubarrayMins(new int[]{3,1,2,4}));
        System.out.println("运行结果：" + solution.sumSubarrayMins(new int[]{11,81,94,43,3}));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int sumSubarrayMins(int[] arr) {
            int n = arr.length;
            int[] l = new int[n], r = new int[n];
            Arrays.fill(l, -1);
            Arrays.fill(r, n);
    
            Deque<Integer> queue = new LinkedList<>();
            //r[i] = b 代表下标 b 为arr[i]能够作为子数组最小值时的最远右边界，即为 arr[i] 右边第一个比其小的元素（若不存在，则 b=n）
            for(int i = 0; i < n ; i++){
                while(!queue.isEmpty() && arr[queue.peekLast()] > arr[i]){
                    r[queue.pollLast()] = i;
                }
                queue.offerLast(i);
            }
            queue.clear();
            //l[i]=a代表下标 a 为arr[i]能够作为子数组最小值时的最远左边界，即为 arr[i] 左边第一个比其小的元素（若不存在，则 a=-1）
            for(int i = n - 1; i >= 0; i--){
                while(!queue.isEmpty() && arr[queue.peekLast()] > arr[i]){
                    l[queue.pollLast()] = i;
                }
                queue.offerLast(i);
            }
    
             //System.out.println("l = " + Arrays.toString(l));
             //System.out.println("r = " + Arrays.toString(r));
            //计算贡献值
            int ans = 0;
            for(int i = 0; i < n; i++){
                ans += (1L * (i - l[i]) * (r[i] - i)) % MOD * arr[i] % MOD;
                ans %= MOD;
            }
            
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}